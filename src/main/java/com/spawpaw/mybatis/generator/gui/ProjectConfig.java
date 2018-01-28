package com.spawpaw.mybatis.generator.gui;

import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.spawpaw.mybatis.generator.gui.annotations.AdvancedConfig;
import com.spawpaw.mybatis.generator.gui.annotations.EnablePlugin;
import com.spawpaw.mybatis.generator.gui.annotations.ExportToPlugin;
import com.spawpaw.mybatis.generator.gui.annotations.ExportToTab;
import com.spawpaw.mybatis.generator.gui.config.*;
import com.spawpaw.mybatis.generator.gui.controller.BaseController;
import com.spawpaw.mybatis.generator.gui.controller.DatabaseConfig;
import com.spawpaw.mybatis.generator.gui.controller.MainController;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import com.spawpaw.mybatis.generator.gui.util.Constants.plugins;
import com.spawpaw.mybatis.generator.gui.util.Constants.tabs;
import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import org.hildan.fxgson.FxGson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.spawpaw.mybatis.generator.gui.util.Constants.tabs.DATA_ACCESS_OBJECT;
import static com.spawpaw.mybatis.generator.gui.util.Constants.tabs.DOMAIN_OBJECT;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */


public class ProjectConfig {
    public DatabaseConfig selectedDatabaseConfig;
    /****基本配置******************************************************************************************************/
    @ExportToTab(layer = tabs.BASIC_SETTINGS, index = 1)
    public TextFieldConfig savedName = new TextFieldConfig("保存名称", "untitled");
    public FileChooserConfig projectDir = new FileChooserConfig("项目所在目录", "");
    public TextFieldConfig basePackage = new TextFieldConfig("basePackage", "com.example.ssm");
    public ChoiceBoxConfig javaClientMapperType = new ChoiceBoxConfig("mapper类型", "XMLMAPPER", Constants.javaClientGeneratorMapperType);
    public TextFieldConfig reduceTablePrefix = new TextFieldConfig("表名去前缀", "");
    @AdvancedConfig
    public TextFieldConfig daoObjSuffix = new TextFieldConfig("Mapper后缀", "Mapper");
    @AdvancedConfig
    public TextFieldConfig daoPackageSuffix = new TextFieldConfig("dao层包后缀", "dao");
    @AdvancedConfig
    public TextFieldConfig entityObjSuffix = new TextFieldConfig("实体后缀", "");
    @AdvancedConfig
    public TextFieldConfig exampleObjSuffix = new TextFieldConfig("Example后缀", "Example");
    @AdvancedConfig
    public TextFieldConfig entityPackageSuffix = new TextFieldConfig("实体包后缀", "entity");

    @ExportToTab(layer = tabs.COMMENT)
    @ExportToPlugin(plugin = plugins.CommentPlugin, key = "fileHeader")
    public TextAreaConfig fileHeader = new TextAreaConfig("fileHeader", "/**\n * Created by MBG-gui-extension 2018.1.1\n * ${tableComment}\n *\n * @author \n **/");
    public CheckBoxConfig enableComment = new CheckBoxConfig("为实体域生成注释", true);


    /****DAO层配置******************************************************************************************************/
    @ExportToTab(layer = DATA_ACCESS_OBJECT, index = 1)
    public TextFieldConfig mapperDir = new TextFieldConfig("mapper所在目录", "src/main/resources");
    public TextFieldConfig mapperPackage = new TextFieldConfig("mapper包名", "mapper");
    public TextFieldConfig daoDir = new TextFieldConfig("接口所在目录", "src/main/java");
    public TextFieldConfig daoPackage = new TextFieldConfig("接口包名", "");
    public TextFieldConfig daoObjName = new TextFieldConfig("Mapper名称", "");
    @EnablePlugin(plugins.PagePlugin)
    public CheckBoxConfig enablePagePlugin = new CheckBoxConfig("启用分页插件", true);
    @AdvancedConfig
    public CheckBoxConfig enableInsert = new CheckBoxConfig("enableInsert", true);
    @AdvancedConfig
    public CheckBoxConfig enableSelectByPrimaryKey = new CheckBoxConfig("enableSelectByPrimaryKey", true);
    @AdvancedConfig
    public CheckBoxConfig enableSelectByExample = new CheckBoxConfig("enableSelectByExample", true);
    @AdvancedConfig
    public CheckBoxConfig enableUpdateByPrimaryKey = new CheckBoxConfig("enableUpdateByPrimaryKey", true);
    @AdvancedConfig
    public CheckBoxConfig enableDeleteByPrimaryKey = new CheckBoxConfig("enableDeleteByPrimaryKey", true);
    @AdvancedConfig
    public CheckBoxConfig enableDeleteByExample = new CheckBoxConfig("enableDeleteByExample", true);
    @AdvancedConfig
    public CheckBoxConfig enableCountByExample = new CheckBoxConfig("enableCountByExample", true);
    @AdvancedConfig
    public CheckBoxConfig enableUpdateByExample = new CheckBoxConfig("enableUpdateByExample", true);
    @AdvancedConfig
    public CheckableTextFieldConfig selectByPrimaryKeyQueryId = new CheckableTextFieldConfig("selectByPrimaryKeyQueryId", "", false);
    @AdvancedConfig
    public CheckableTextFieldConfig selectByExampleQueryId = new CheckableTextFieldConfig("selectByExampleQueryId", "", false);


    /****Entity层配置***************************************************************************************************/
    @ExportToTab(layer = DOMAIN_OBJECT, index = 1)
    public TextFieldConfig entityDir = new TextFieldConfig("entity所在目录", "src/main/java");
    public TextFieldConfig entityPackage = new TextFieldConfig("entity包名", "entity");
    public TextFieldConfig entityObjName = new TextFieldConfig("entity名称", "");
    public TextFieldConfig exampleObjName = new TextFieldConfig("Example名称", "");
    public TextFieldConfig primaryKey = new TextFieldConfig("主键", "");

    @EnablePlugin(plugins.ToStringPlugin)
    public CheckBoxConfig generateToString = new CheckBoxConfig("生成toString方法", true);
    @EnablePlugin(plugins.EqualsHashCodePlugin)
    public CheckBoxConfig generateHashcodeEquals = new CheckBoxConfig("生成hashCode/equals方法", true);
    @EnablePlugin(plugins.SerializablePlugin)
    public CheckBoxConfig implementsSerializable = new CheckBoxConfig("实体域继承Serializable接口", true);
    public CheckBoxConfig generateSeparateEntityForBlob = new CheckBoxConfig("当包含Blob类型时，生成单独实体", false);
    @ExportToPlugin(plugin = plugins.CommentPlugin, key = "generateJPA")
    public CheckBoxConfig generateJPA = new CheckBoxConfig("生成JPA注解", false);
    public CheckBoxConfig useActualColumnNames = new CheckBoxConfig("使用实际的列名（如不勾选，将使用骆驼峰形式）", false);

//    /****Service层配置**************************************************************************************************/
//    @ExportToTab(layer = tabs.SERVICE)
//    @EnablePlugin(plugins.SerializablePlugin)
//    public CheckBoxConfig enableRestServicePlugin = new CheckBoxConfig("启用示例RestfulService插件", false);
//
//    public TextFieldConfig serviceDir = new TextFieldConfig("service所在路径", "src/main/java");
//    public TextFieldConfig servicePackage = new TextFieldConfig("service所在包", "service");
//
//    @ExportToPlugin(plugin = plugins.DemoServicePlugin, key = "restServiceDir")
//    public String getServiceDir() {
//        return projectDir.getValue() + serviceDir.getValue();
//    }


    /*===方法s========================================================================================================*/

    /**
     * 初始化UI，由于Gson并不能反序列化各类UI控件，需手动初始化带控件的配置项
     */
    public void initialize() {
        for (Field field : ProjectConfig.class.getFields()) {
            try {
                if (field.get(this) instanceof Config)
                    ((Config) field.get(this)).initialize();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        bindProperties();
    }

    /**
     * 绑定Property，在初始化控件完成之后，为控件的值添加监听
     */
    private void bindProperties() {
        reduceTablePrefix.valueProperty().addListener(((observable, oldValue, newValue) -> updateClassName()));
        daoObjSuffix.valueProperty().addListener(((observable, oldValue, newValue) -> updateClassName()));
        entityObjSuffix.valueProperty().addListener(((observable, oldValue, newValue) -> updateClassName()));
        selectedDatabaseConfig.selectedTable.addListener(((observable, oldValue, newValue) -> updateClassName()));

        basePackage.valueProperty().addListener(((observable, oldValue, newValue) -> updatePackageName()));
        daoPackageSuffix.valueProperty().addListener(((observable, oldValue, newValue) -> updatePackageName()));
        entityPackageSuffix.valueProperty().addListener(((observable, oldValue, newValue) -> updatePackageName()));
    }

    /**
     * 当所选表名/表名过滤器的值发生变化时，更新Ui中的对象名
     */
    public void updateClassName() {
        daoObjName.setValue(String.format("%s%s", getUpperCamelTableName(), daoObjSuffix.getValue()));
        entityObjName.setValue(String.format("%s%s", getUpperCamelTableName(), entityObjSuffix.getValue()));
        exampleObjName.setValue(String.format("%s%s", getUpperCamelTableName(), exampleObjSuffix.getValue()));
    }

    /**
     * 当BasePackage的包名发生过变化时，更新各层的包名
     */
    private void updatePackageName() {
        daoPackage.setValue(String.format("%s.%s", basePackage.getValue(), daoPackageSuffix.getValue()));
        entityPackage.setValue(String.format("%s.%s", basePackage.getValue(), entityPackageSuffix.getValue()));
    }


    /**
     * @return 表名的大骆驼峰形式
     */
    private String getUpperCamelTableName() {
        String rawTableName = selectedDatabaseConfig.selectedTable.getValue();
        return getUpperCamel(rawTableName.replaceAll(String.format("^(%s)", reduceTablePrefix.getValue()), ""));
    }

    /**
     * 将lower_snake转换成大骆驼峰
     */
    private String getUpperCamel(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s);
    }


    /**
     * 保存当前配置
     */
    public String save(MainController context) {
        String msg = "保存成功";
        Gson fxGson = FxGson.create();
        String s = null;
        try {
            s = fxGson.toJson(this);
            System.out.println("save project config: \n" + s);
            if (Files.exists(Paths.get(String.format("data/config/%s.json", savedName.getValue()))))
                msg = "保存成功（已覆盖同名文件）";
            FileUtil.writeStringToFile(String.format("data/config/%s.json", savedName.getValue()), s);
        } catch (IOException e) {
            e.printStackTrace();
            msg = "保存失败";
        }
        BaseController.mainWindowController.refreshConnectionsList();
        return msg;
    }
}
