package com.spawpaw.mybatis.generator.gui.plugin;

import com.spawpaw.mybatis.generator.gui.entity.ConfigWrapper;
import com.spawpaw.mybatis.generator.gui.entity.Table;
import com.spawpaw.mybatis.generator.gui.entity.TemplateConfig;
import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import com.spawpaw.mybatis.generator.gui.util.Utils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created By spawpaw@hotmail.com  2018-03-22
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class SCVXGeneratorPlugin extends PluginAdapter {
    private String projectDir = "";
    private String basePackage = "";
    private String scvxConfigYml = "";
    Logger log = LoggerFactory.getLogger(SCVXGeneratorPlugin.class);

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        Utils.injectFieldsFromProperties(this, properties);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        log.info(">>>> generating extra files...");
        // : 2018/3/22 将 introspectedTable 转化为对象
        Map<String, String> map = new HashMap<>();
        for (Object o : properties.keySet()) {
            map.put(o.toString(), properties.getProperty(o.toString()));
        }
        Table table = new Table(context, introspectedTable, map);
        // : 2018/3/22 初始化context
        VelocityContext templateContext = new VelocityContext();
        templateContext.put("table", table);
        templateContext.put("projectDir.ss", projectDir);

        //输出测试数据
        String content;
//        content = renderTemplateAsString("test.vm", templateContext);
//        log.info("hierarchical table structure: {}", content);

        // : 2018/3/22 保存到指定目录
        List<TemplateConfig> configs = new Yaml().loadAs(scvxConfigYml, ConfigWrapper.class).getTemplateConfig();
        for (TemplateConfig config : configs) {
            String template = config.getTemplate();
            String destDir = config.getDestDir().replaceAll("\\.", "/");
            String destPackage = config.getDestPackage();
            String destFileName = config.getDestFileName();
            String absPath = (projectDir == null || projectDir.isEmpty() ? "" : projectDir + (projectDir.endsWith("/") || projectDir.endsWith("\\") ? "" : "/"))
                    + destDir
                    + "/"
                    + destPackage.replace(".", "/")
                    + "/"
                    + destFileName;
            absPath = absPath.replace("//", "/");
            absPath = absPath.replace("${entityName}", table.getEntityName());
            absPath = absPath.replace("${entityLowerCamel}", table.getEntityLowerCamel());
            absPath = absPath.replace("${basePackage}", basePackage.replace(".", "/"));
            log.info("generate file `{}` from template `{}`", absPath, template);

            content = renderTemplateAsString(config.getTemplate(), templateContext);
            //写入文件
            try {
                FileUtil.writeStringToFile(absPath, content);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
//            log.info("content: {}", content);
        }
        log.info("<<< generated extra files.");
        return null;
    }


    public String renderTemplateAsString(String templateFile, VelocityContext ctx) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template t = ve.getTemplate("template/" + templateFile, "UTF-8");
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        return sw.toString();
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}
