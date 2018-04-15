package com.spawpaw.mybatis.generator.gui.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.mybatis.generator.api.dom.OutputUtilities.newLine;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * Created By spawpaw@hotmail.com  2018-04-15
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class MyShellCallback implements ShellCallback {

    private boolean overwrite;

    public MyShellCallback(boolean overwrite) {
        super();
        this.overwrite = overwrite;
    }

    @Override
    public File getDirectory(String targetProject, String targetPackage)
            throws ShellException {
        // targetProject is interpreted as a directory that must exist
        //
        // targetPackage is interpreted as a sub directory, but in package
        // format (with dots instead of slashes). The sub directory will be
        // created
        // if it does not already exist

        File project = new File(targetProject);
        if (!project.isDirectory()) {
            throw new ShellException(getString("Warning.9", //$NON-NLS-1$
                    targetProject));
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new ShellException(getString("Warning.10", //$NON-NLS-1$
                        directory.getAbsolutePath()));
            }
        }

        return directory;
    }

    @Override
    public void refreshProject(String project) {
        // nothing to do in the default shell callback
    }

    @Override
    public boolean isMergeSupported() {
        return !overwrite;
    }

    @Override
    public boolean isOverwriteEnabled() {
        return overwrite;
    }

    @Override
    public String mergeJavaFile(String newFileSource,
                                File existingFile, String[] javadocTags, String fileEncoding)
            throws ShellException {
        try {
            CompilationUnit newCompilationUnit = JavaParser.parse(newFileSource);
            CompilationUnit existingCompilationUnit = JavaParser.parse(existingFile);
            return mergerFile(newCompilationUnit, existingCompilationUnit);
        } catch (FileNotFoundException e) {
            throw new UnsupportedOperationException();
        }
    }

    public String mergerFile(CompilationUnit newCompilationUnit, CompilationUnit existingCompilationUnit) {

        System.out.println("合并java代码...");
        StringBuilder sb = new StringBuilder(newCompilationUnit.getPackageDeclaration().get().toString());
        newCompilationUnit.removePackageDeclaration();

        //合并imports
        NodeList<ImportDeclaration> imports = newCompilationUnit.getImports();
        imports.addAll(existingCompilationUnit.getImports());
        Set<ImportDeclaration> importSet = new HashSet<>();
        importSet.addAll(imports);

        NodeList<ImportDeclaration> newImports = new NodeList<>();
        newImports.addAll(importSet);
        newCompilationUnit.setImports(newImports);
        for (ImportDeclaration i : newCompilationUnit.getImports()) {
            sb.append(i.toString());
        }
        newLine(sb);
        NodeList<TypeDeclaration<?>> types = newCompilationUnit.getTypes();
        NodeList<TypeDeclaration<?>> oldTypes = existingCompilationUnit.getTypes();

        for (int i = 0; i < types.size(); i++) {
            //截取Class
            String classNameInfo = types.get(i).toString().substring(0, types.get(i).toString().indexOf("{") + 1);
            sb.append(classNameInfo);
            newLine(sb);
            newLine(sb);

            //合并fields
            Map<String, FieldDeclaration> newFieldsToWrite = new Hashtable<>();//将要写入的

            for (FieldDeclaration fieldDeclaration : oldTypes.get(i).getFields()) {//之前存在的
                boolean flag = true;
                for (String tag : MergeConstants.OLD_ELEMENT_TAGS)
                    if (fieldDeclaration.toString().contains(tag)) {
                        flag = false;
                        break;
                    }
                if (flag) {//如果该字段不是自动生成的，则重新加入
                    newFieldsToWrite.put(fieldDeclaration.getVariables().toString(), fieldDeclaration);
                }
            }
            for (FieldDeclaration fieldDeclaration : types.get(i).getFields()) {//新生成的
                newFieldsToWrite.put(fieldDeclaration.getVariables().toString(), fieldDeclaration);
            }
            for (FieldDeclaration f : newFieldsToWrite.values()) {
                sb.append("\t" + f.toString());
                newLine(sb);
                newLine(sb);
            }

            //合并methods
            List<MethodDeclaration> methods = types.get(i).getMethods();
            List<MethodDeclaration> existingMethods = oldTypes.get(i).getMethods();
            List<String> newMethods = new ArrayList<String>();

            for (MethodDeclaration f : methods) {
                String res = f.toString().replaceAll("\r\n", "\r\n    ");
                sb.append("    " + res);
                newLine(sb);
                newLine(sb);
            }

            for (MethodDeclaration m : methods) {
                newMethods.add(m.getName().toString());
            }
            newMethods.add("toString");
            newMethods.add("hashCode");
            newMethods.add("equals");

            for (MethodDeclaration m : existingMethods) {
                if (newMethods.contains(m.getName().toString())) {
                    continue;
                }

                boolean flag = true;
                for (String tag : MergeConstants.OLD_ELEMENT_TAGS) {
                    if (m.toString().contains(tag)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    String res = m.toString().replaceAll("\r\n", "\r\n    ");
                    sb.append("    " + res);
                    newLine(sb);
                    newLine(sb);
                }
            }

            //判断是否有内部类
            types.get(i).getChildNodes();
            for (Node n : types.get(i).getChildNodes()) {
                if (n.toString().contains("static class")) {
                    String res = n.toString().replaceAll("\r\n", "\r\n    ");
                    sb.append("    " + res);
                }
            }

        }

        return sb.append(System.getProperty("line.separator") + "}").toString();
    }

}

