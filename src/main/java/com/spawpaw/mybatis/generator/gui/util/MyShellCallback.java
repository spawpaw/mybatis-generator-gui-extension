package com.spawpaw.mybatis.generator.gui.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * Created By spawpaw@hotmail.com  2018-04-15
 *
 * @author BenBenShang spawpaw@hotmail.com 版权所有，盗用必究
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
            return mergeCompilationUnit(existingCompilationUnit, newCompilationUnit).toString();
        } catch (FileNotFoundException e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 合并可编译单元（即合并两个Java文件）
     */
    private CompilationUnit mergeCompilationUnit(CompilationUnit oldCompilationUnit, CompilationUnit newCompilationUnit) {
        CompilationUnit finalCompilationUnit = new CompilationUnit();

        //修改包名为新类的包名
        if (newCompilationUnit.getPackageDeclaration().isPresent())
            finalCompilationUnit.setPackageDeclaration(newCompilationUnit.getPackageDeclaration().get());

        //合并import
        Set<ImportDeclaration> importSet = new HashSet<>();
        importSet.addAll(oldCompilationUnit.getImports());
        importSet.addAll(newCompilationUnit.getImports());

        NodeList<ImportDeclaration> imports = new NodeList<>();
        imports.addAll(importSet);
        finalCompilationUnit.setImports(imports);

        //合并topLevelClass
        finalCompilationUnit.setTypes(mergeTypes(oldCompilationUnit.getTypes(), newCompilationUnit.getTypes()));


        return finalCompilationUnit;
    }

    /**
     * 合并Java类（一个Java文件可能有多个类）
     */
    private NodeList<TypeDeclaration<?>> mergeTypes(NodeList<TypeDeclaration<?>> oldTypes, NodeList<TypeDeclaration<?>> newTypes) {
        Map<String, TypeDeclaration<?>> finalTypes = new Hashtable<>();
        for (TypeDeclaration<?> newType : newTypes) {
            finalTypes.put(newType.getNameAsString(), newType);
        }

        for (TypeDeclaration<?> oldType : oldTypes) {//对于旧CompilationUnit中的每一个TopLevelClass
            if (finalTypes.containsKey(oldType.getNameAsString())) {//如果存在同名类则合并
                finalTypes.put(oldType.getNameAsString(), mergeType(oldType, finalTypes.get(oldType.getNameAsString())));
            } else if (!isGeneratedNode(oldType)) {//如果不存在同名类且不是生成的类
                finalTypes.put(oldType.getNameAsString(), mergeType(oldType, finalTypes.get(oldType.getNameAsString())));
            }
        }

        return new NodeList<>(finalTypes.values());
    }

    /**
     * 合并两个同名类
     */
    private TypeDeclaration<?> mergeType(TypeDeclaration<?> oldType, TypeDeclaration<?> newType) {
        TypeDeclaration<?> finalTypeDeclaration;
        if (newType.isClassOrInterfaceDeclaration() && oldType.isClassOrInterfaceDeclaration()) {
            finalTypeDeclaration = new ClassOrInterfaceDeclaration();
            ClassOrInterfaceDeclaration oldClass = oldType.asClassOrInterfaceDeclaration();
            ClassOrInterfaceDeclaration newClass = newType.asClassOrInterfaceDeclaration();

            //设置修饰符及类名
            finalTypeDeclaration.setModifiers(newClass.getModifiers());//修饰符
            finalTypeDeclaration.asClassOrInterfaceDeclaration().setInterface(//是否为接口
                    newClass.isInterface()
            );
            finalTypeDeclaration.setName(newClass.getName());//类名
            finalTypeDeclaration.asClassOrInterfaceDeclaration().setExtendedTypes(newClass.getExtendedTypes());//继承的类
            finalTypeDeclaration.asClassOrInterfaceDeclaration().setImplementedTypes(newClass.getImplementedTypes());//继承的接口
            finalTypeDeclaration.asClassOrInterfaceDeclaration().setAnnotations(newClass.getAnnotations());//注解
            if (newClass.getComment().isPresent())//注释
                finalTypeDeclaration.asClassOrInterfaceDeclaration().setComment(newClass.getComment().get());


            //合并initializer(possibly static)
            //保留所有旧类中的initializer（MBG并不会生成initializer，不考虑保留旧initializer会出现的问题）
            for (BodyDeclaration<?> bodyDeclaration : oldClass.getMembers())
                if (bodyDeclaration.isInitializerDeclaration())
                    finalTypeDeclaration.addMember(bodyDeclaration);

            //合并构造函数
            for (ConstructorDeclaration constructorDeclaration : mergeConstructors(oldClass.getConstructors(), newClass.getConstructors())) {
                finalTypeDeclaration.addMember(constructorDeclaration);
            }

            //合并Field
            for (FieldDeclaration fieldDeclaration : mergeFields(oldClass.getFields(), newClass.getFields())) {
                finalTypeDeclaration.addMember(fieldDeclaration);
            }

            //合并Method
            for (MethodDeclaration methodDeclaration : mergeMethods(oldClass.getMethods(), newClass.getMethods())) {
                finalTypeDeclaration.addMember(methodDeclaration);
            }

            //合并内部类（class/enum/interface）
            NodeList<TypeDeclaration<?>> oldTypes = new NodeList<>();
            NodeList<TypeDeclaration<?>> newTypes = new NodeList<>();
            for (BodyDeclaration<?> bodyDeclaration : oldClass.getMembers()) {
                if (bodyDeclaration.isClassOrInterfaceDeclaration() ||
                        bodyDeclaration.isEnumDeclaration() ||
                        bodyDeclaration.isAnnotationDeclaration()) {
                    oldTypes.add(bodyDeclaration.asTypeDeclaration());
//                    System.out.println("旧内部类：" + bodyDeclaration.asTypeDeclaration().getNameAsString());
                }
            }
            for (BodyDeclaration<?> bodyDeclaration : newClass.getMembers()) {
                if (bodyDeclaration.isClassOrInterfaceDeclaration() ||
                        bodyDeclaration.isEnumDeclaration() ||
                        bodyDeclaration.isAnnotationDeclaration()) {
                    newTypes.add(bodyDeclaration.asTypeDeclaration());
//                    System.out.println("新内部类：" + bodyDeclaration.asTypeDeclaration().getNameAsString());
//                    System.out.println("新内部类修饰符：" + bodyDeclaration.asTypeDeclaration().getModifiers());
                }
            }
            for (TypeDeclaration<?> typeDeclaration : mergeTypes(oldTypes, newTypes)) {
                finalTypeDeclaration.addMember(typeDeclaration);
            }

            return finalTypeDeclaration;
        } else if (newType.isEnumDeclaration() && oldType.isEnumDeclaration()) {
            finalTypeDeclaration = new EnumDeclaration();
            return newType;
        } else if (newType.isAnnotationDeclaration() && oldType.isAnnotationDeclaration()) {
            finalTypeDeclaration = new AnnotationDeclaration();
            return newType;
        } else {
            throw new RuntimeException(String.format("新类和旧类的类型不一样，无法判断该以何种方式合并，请删除旧文件或者将旧文件更改为正确的类型 (类名：%s)", newType.getNameAsString()));
//            return newType;
        }
    }

    /**
     * 合并构造函数
     */
    private List<ConstructorDeclaration> mergeConstructors(List<ConstructorDeclaration> oldConstructors, List<ConstructorDeclaration> newConstructors) {

        Map<String, ConstructorDeclaration> constructorDeclarationMap = new Hashtable<>();//
        for (ConstructorDeclaration newConstructor : newConstructors) {
            if (!constructorDeclarationMap.containsKey(newConstructor.getDeclarationAsString(false, false, false))
                    && !isGeneratedNode(newConstructor)) {//如果新生成的类中不包含该构造函数且该构造函数不是自动生成的
                constructorDeclarationMap.put(newConstructor.getDeclarationAsString(false, false, false), newConstructor);
            }
        }
        for (ConstructorDeclaration oldConstructor : oldConstructors) {
            constructorDeclarationMap.put(oldConstructor.getDeclarationAsString(false, false, false), oldConstructor);
//            System.out.println("构造函数：" + oldConstructor.getDeclarationAsString(true, true, false));
        }
        return new ArrayList<>(constructorDeclarationMap.values());
    }

    /**
     * 合并字段
     */
    private List<FieldDeclaration> mergeFields(List<FieldDeclaration> oldFields, List<FieldDeclaration> newFields) {
        Map<String, FieldDeclaration> fieldDeclarationMap = new Hashtable<>();//
        for (FieldDeclaration newField : newFields) {
            //mbg生成的一个变量声明不会包含多个变量
            String key = "";
            for (VariableDeclarator variableDeclarator : newField.getVariables()) {
                key += variableDeclarator.getNameAsString() + ",";
            }
            fieldDeclarationMap.put(key, newField);
        }
        for (FieldDeclaration oldField : oldFields) {
            String key = "";
            for (VariableDeclarator variableDeclarator : oldField.getVariables()) {
                key += variableDeclarator.getNameAsString() + ",";
            }
            if (!fieldDeclarationMap.containsKey(key) && !isGeneratedNode(oldField)) {
                fieldDeclarationMap.put(key, oldField);
            }
        }
        return new ArrayList<>(fieldDeclarationMap.values());
    }

    /**
     * 合并方法
     */
    private List<MethodDeclaration> mergeMethods(List<MethodDeclaration> oldMethods, List<MethodDeclaration> newMethods) {
        Map<String, MethodDeclaration> methodDeclarationMap = new Hashtable<>();
        for (MethodDeclaration newMethod : newMethods) {
            methodDeclarationMap.put(newMethod.getDeclarationAsString(false, false, false), newMethod);
        }
        for (MethodDeclaration oldMethod : oldMethods) {
            if (!methodDeclarationMap.containsKey(oldMethod.getDeclarationAsString(false, false, false)) && !isGeneratedNode(oldMethod)) {
                methodDeclarationMap.put(oldMethod.getDeclarationAsString(false, false, false), oldMethod);
            }
        }
        return new ArrayList<>(methodDeclarationMap.values());
    }


    /**
     * 判断是否为自动生成的节点（即注释中包含指定tag）
     */
    private boolean isGeneratedNode(Node node) {
        for (String tag : MergeConstants.OLD_ELEMENT_TAGS) {
            if (node.getComment().toString().contains(tag)) {
                return true;
            }
        }
        return false;
    }
}

