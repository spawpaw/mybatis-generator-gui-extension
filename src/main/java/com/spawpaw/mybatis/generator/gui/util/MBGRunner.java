package com.spawpaw.mybatis.generator.gui.util;

import com.spawpaw.mybatis.generator.gui.ProjectConfig;
import com.spawpaw.mybatis.generator.gui.annotations.EnablePlugin;
import com.spawpaw.mybatis.generator.gui.annotations.ExportToPlugin;
import com.spawpaw.mybatis.generator.gui.config.Config;
import com.spawpaw.mybatis.generator.gui.controller.TableColumnMetaData;
import javafx.beans.property.Property;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class MBGRunner {
    private static boolean overwrite = true;
    private ProjectConfig projectConfig;
    private Set<String> enabledPlugins = new HashSet<>();
    private HashMap<String, HashMap<String, String>> pluginConfigs = new HashMap<>();
    private Configuration config;
    private Context context;

    public MBGRunner(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }


    public void generate() {
        config = new Configuration();
        //default model type
        if (projectConfig.generateSeparateEntityForBlob.getValue())
            context = new Context(ModelType.CONDITIONAL);
        else context = new Context(ModelType.FLAT);


        context.setId("mybatis generator gui extension");//id
        context.setTargetRuntime("MyBatis3");//targetRuntime


        //=====================================================================================================加载插件
        //initialize plugin data
        initPluginConfigs();
        addPlugins();

        //====================================================================================================注释生成器
        if (projectConfig.enableComment.getValue()) {
            CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
            commentGeneratorConfiguration.setConfigurationType(Constants.plugins.CommentPlugin);
//            commentGeneratorConfiguration.addProperty("suppressDate", "false");
//            commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
            if (pluginConfigs.containsKey(Constants.plugins.CommentPlugin)) {
                HashMap<String, String> pluginProperties = pluginConfigs.get(Constants.plugins.CommentPlugin);
                for (String key : pluginProperties.keySet())
                    commentGeneratorConfiguration.addProperty(key, pluginProperties.get(key));
            }
            context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
        }

        //==============================================================================================jdbc connection
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass(projectConfig.selectedDatabaseConfig.driver());
        jdbcConnectionConfiguration.setConnectionURL(projectConfig.selectedDatabaseConfig.connectionUrl());
        jdbcConnectionConfiguration.setUserId(projectConfig.selectedDatabaseConfig.username.getValue());
        jdbcConnectionConfiguration.setPassword(projectConfig.selectedDatabaseConfig.password.getValue());
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        //=============================================================================================javaTypeResolver
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        //========================================================================================================model
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage(projectConfig.entityPackage.getValue());
        javaModelGeneratorConfiguration.setTargetProject(projectDir() + projectConfig.entityDir.getValue());
        javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
        javaModelGeneratorConfiguration.addProperty("useActualColumnNames", projectConfig.useActualColumnNames.getValue().toString());
        javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        //=======================================================================================================mapper
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(projectDir() + projectConfig.mapperDir.getValue());
        sqlMapGeneratorConfiguration.setTargetPackage(projectConfig.mapperPackage.getValue());
        sqlMapGeneratorConfiguration.addProperty("useActualColumnNames", projectConfig.useActualColumnNames.getValue().toString());
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        //==========================================================================================================dao
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setConfigurationType(projectConfig.javaClientMapperType.getValue());
        javaClientGeneratorConfiguration.setTargetProject(projectDir() + projectConfig.daoDir.getValue());
        javaClientGeneratorConfiguration.setTargetPackage(projectConfig.daoPackage.getValue());
        sqlMapGeneratorConfiguration.addProperty("useActualColumnNames", projectConfig.useActualColumnNames.getValue().toString());
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        //========================================================================================================table
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(projectConfig.selectedDatabaseConfig.selectedTable.getValue());
        tableConfiguration.setDomainObjectName(projectConfig.entityObjName.getValue());
        tableConfiguration.setMapperName(projectConfig.daoObjName.getValue());

        tableConfiguration.setInsertStatementEnabled(projectConfig.enableInsert.getValue());
        tableConfiguration.setSelectByPrimaryKeyStatementEnabled(projectConfig.enableSelectByPrimaryKey.getValue());
        tableConfiguration.setSelectByExampleStatementEnabled(projectConfig.enableSelectByExample.getValue());
        if (projectConfig.selectByPrimaryKeyQueryId.isChecked())
            tableConfiguration.setSelectByPrimaryKeyQueryId(projectConfig.selectByPrimaryKeyQueryId.getValue());
        if (projectConfig.selectByExampleQueryId.isChecked())
            tableConfiguration.setSelectByExampleQueryId(projectConfig.selectByExampleQueryId.getValue());
        tableConfiguration.setUpdateByPrimaryKeyStatementEnabled(projectConfig.enableUpdateByPrimaryKey.getValue());
        tableConfiguration.setUpdateByExampleStatementEnabled(projectConfig.enableUpdateByExample.getValue());
        tableConfiguration.setDeleteByPrimaryKeyStatementEnabled(projectConfig.enableDeleteByPrimaryKey.getValue());
        tableConfiguration.setDeleteByExampleStatementEnabled(projectConfig.enableDeleteByExample.getValue());
        tableConfiguration.setCountByExampleStatementEnabled(projectConfig.enableCountByExample.getValue());
        tableConfiguration.addProperty("useActualColumnNames", projectConfig.useActualColumnNames.getValue().toString());//使用小骆驼峰替代原列名

        //see http://www.mybatis.org/generator/configreference/generatedKey.html  ,JDBC is a database independent method of obtaining the value from identity columns,only for Mybatis3+
        if (!projectConfig.primaryKey.getValue().isEmpty())
            tableConfiguration.setGeneratedKey(new GeneratedKey(projectConfig.primaryKey.getValue(), "JDBC", true, null));

        //添加忽略列/列覆写
        if (projectConfig.selectedDatabaseConfig.columns != null)
            for (TableColumnMetaData column : projectConfig.selectedDatabaseConfig.columns) {
                if (!column.getChecked()) {
                    System.out.println("忽略的列：" + column.getColumnName());
                    tableConfiguration.addIgnoredColumn(new IgnoredColumn(column.getColumnName()));
                } else {
                    ColumnOverride columnOverride = new ColumnOverride(column.getColumnName());
                    columnOverride.setJavaProperty(column.getPropertyName());
                    columnOverride.setJavaType(column.getJavaType());
                    columnOverride.setJdbcType(column.getJdbcType());
                    columnOverride.setTypeHandler(column.getTypeHandle());
                    tableConfiguration.addColumnOverride(columnOverride);
                }
            }

        context.addTableConfiguration(tableConfiguration);

        config.addContext(context);
        List<String> warnings = new ArrayList<>();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (InvalidConfigurationException | InterruptedException | SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(warnings);
    }

    /**
     * 获取项目的绝对路径前缀
     */
    private String projectDir() {
        return projectConfig.projectDir.getValue().isEmpty() ? "" : projectConfig.projectDir.getValue() + "/";
    }

    /**
     * 初始化与Plugin相关的配置
     */
    private void initPluginConfigs() {
        try {
            for (Field field : ProjectConfig.class.getFields()) {
                String valueOfField;
                //获取配置项的值
                if (field.get(projectConfig) instanceof Config)
                    valueOfField = ((Config) field.get(projectConfig)).getValue().toString();
                else if (field.get(projectConfig) instanceof Property)
                    valueOfField = ((Property) field.get(projectConfig)).getValue().toString();
                else {
                    System.out.println(String.format("initPluginConfigs:不支持的配置:%s,将调用该配置的toString方法", field.getName()));
                    valueOfField = field.get(projectConfig).toString();
//                    continue;
                }
                //如果该配置项设置了启动某个Plugin的Trigger，将启用指定的plugin
                for (EnablePlugin enablePlugin : field.getAnnotationsByType(EnablePlugin.class)) {
                    if (!valueOfField.isEmpty() && !valueOfField.equals("false")) {
                        enabledPlugins.add(enablePlugin.value());
                    }
                }
                //将配置项的值加入到plugin的properties中
                for (ExportToPlugin exportToPlugin : field.getAnnotationsByType(ExportToPlugin.class)) {
                    System.out.printf("配置:%s,值：%s,,plugin:%s   key:%s  \n", field.getName(), valueOfField, exportToPlugin.plugin(), exportToPlugin.key());
                    pluginConfigs.putIfAbsent(exportToPlugin.plugin(), new HashMap<>());
                    pluginConfigs.get(exportToPlugin.plugin()).put(exportToPlugin.key().isEmpty() ? field.getName() : exportToPlugin.key(), valueOfField);
                }
            }
            //如果方法上包含ExportToPlugin注释，则使用返回值的toString方法
            for (Method method : ProjectConfig.class.getMethods()) {
                for (ExportToPlugin exportToPlugin : method.getAnnotationsByType(ExportToPlugin.class)) {
                    pluginConfigs.putIfAbsent(exportToPlugin.plugin(), new HashMap<>());
                    pluginConfigs.get(exportToPlugin.plugin()).put(exportToPlugin.key().isEmpty() ? method.getName() : exportToPlugin.key(), method.invoke(projectConfig).toString());
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void addPlugins() {
        for (String enabledPluginType : enabledPlugins) {
            System.out.println("MBGRunner.addPlugins:启用插件：" + enabledPluginType);
            HashMap<String, String> pluginProperties = pluginConfigs.get(enabledPluginType);
            PluginConfiguration pluginConfiguration = new PluginConfiguration();
            pluginConfiguration.setConfigurationType(enabledPluginType);
            if (pluginProperties != null)
                for (String key : pluginProperties.keySet()) {
                    pluginConfiguration.addProperty(key, pluginProperties.get(key));
                }
            context.addPluginConfiguration(pluginConfiguration);
        }
    }
}
