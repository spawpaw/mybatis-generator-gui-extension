package com.spawpaw.mybatis.generator.gui;

import com.google.common.base.CaseFormat;
import com.spawpaw.mybatis.generator.gui.annotations.*;
import com.spawpaw.mybatis.generator.gui.enums.DeclaredPlugins;
import com.spawpaw.mybatis.generator.gui.util.Constants.tabs;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static com.spawpaw.mybatis.generator.gui.util.Constants.tabs.*;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 默认的MBG配置
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ProjectConfig {
    public StringProperty selectedTable = new SimpleStringProperty("");
    /****基本配置******************************************************************************************************/
//    @ExportToTab(tabName = tabs.BASIC_SETTINGS, index = 1)
//    @Config(bundle = "project.savedName", helpText = "保存的名称")
//    public SimpleStringProperty savedName = new SimpleStringProperty("untitled");
    @ExportToTab(tabName = tabs.BASIC_SETTINGS, index = 1)
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @Config(bundle = "project.projectDir", type = ConfigType.DirChooser)
    public SimpleStringProperty projectDir = new SimpleStringProperty("");
    @Config(bundle = "project.basePackage")
    public SimpleStringProperty basePackage = new SimpleStringProperty("");

    @ExportToTab(tabName = tabs.BASIC_SETTINGS, index = 1)
    //生成代码文件的编码方式
    @Config(bundle = "project.javaFileEncoding", type = ConfigType.ChoiceBox, testRegex = "UTF-8|GBK|UTF-16BE|UTF-16LE|UTF-16|US-ASCII|ISO-8859-1")
    public SimpleStringProperty javaFileEncoding = new SimpleStringProperty("UTF-8");
    @Config(bundle = "project.reduceTablePrefix", testRegex = "(t|T)_\n(a-z|A-Z){0,2}_"/*这里用\n隔开*/, type = ConfigType.ComboBox)
    public SimpleStringProperty reduceTablePrefix = new SimpleStringProperty("t_");
    @AdvancedConfig
    @Config(bundle = "project.daoObjSuffix")
    public SimpleStringProperty daoObjSuffix = new SimpleStringProperty("Mapper");
    @AdvancedConfig
    @Config(bundle = "project.daoPackageSuffix")
    public SimpleStringProperty daoPackageSuffix = new SimpleStringProperty("dao");
    @AdvancedConfig
    @Config(bundle = "project.entityObjSuffix")
    public SimpleStringProperty entityObjSuffix = new SimpleStringProperty("");
    @AdvancedConfig
    @Config(bundle = "project.exampleObjSuffix")
    public SimpleStringProperty exampleObjSuffix = new SimpleStringProperty("Example");
    @AdvancedConfig
    @Config(bundle = "project.entityPackageSuffix")
    public SimpleStringProperty entityPackageSuffix = new SimpleStringProperty("entity");


    /****DAO层配置******************************************************************************************************/
    @ExportToTab(tabName = DATA_ACCESS_OBJECT, index = 1)
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @Config(bundle = "project.javaClientMapperType", type = ConfigType.ChoiceBox, testRegex = "ANNOTATEDMAPPER|MIXEDMAPPER|XMLMAPPER")
    public SimpleStringProperty javaClientMapperType = new SimpleStringProperty("XMLMAPPER");
    @ExportToTab(tabName = DATA_ACCESS_OBJECT, index = 1)
    @Config(bundle = "project.mapperDir", type = ConfigType.TextField)
    public SimpleStringProperty mapperDir = new SimpleStringProperty("src/main/resources");
    @Config(bundle = "project.mapperPackage", type = ConfigType.TextField)
    public SimpleStringProperty mapperPackage = new SimpleStringProperty("mapper");
    @Config(bundle = "project.daoDir", type = ConfigType.TextField)
    public SimpleStringProperty daoDir = new SimpleStringProperty("src/main/java");
    @Config(bundle = "project.daoPackage", type = ConfigType.TextField)
    public SimpleStringProperty daoPackage = new SimpleStringProperty("");
    @ExportToTab(tabName = DATA_ACCESS_OBJECT, index = 1)
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @Config(bundle = "project.daoObjName", type = ConfigType.TextField)
    public SimpleStringProperty daoObjName = new SimpleStringProperty("");
    @Config(bundle = "project.enablePagePlugin", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enablePagePlugin = new SimpleBooleanProperty(false);

    @ExportToTab(tabName = DATA_ACCESS_OBJECT, index = 1)
    @EnablePlugin(DeclaredPlugins.CaseInsensitiveLikePlugin)
    @Config(bundle = "project.enableCaseInsensitiveLikePlugin", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableCaseInsensitiveLikePlugin = new SimpleBooleanProperty(true);

    @AdvancedConfig
    @ExportToTab(tabName = DATA_ACCESS_OBJECT, index = 1)
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @EnablePlugin(DeclaredPlugins.MapperAnnotationPlugin)
    @Config(bundle = "project.enableMapperAnnotationPlugin", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableMapperAnnotationPlugin = new SimpleBooleanProperty(true);

    @AdvancedConfig
    @ExportToTab(tabName = DATA_ACCESS_OBJECT, index = 1)
    @Config(bundle = "project.enableInsert", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableInsert = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableSelectByPrimaryKey", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableSelectByPrimaryKey = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableSelectByExample", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableSelectByExample = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableUpdateByPrimaryKey", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableUpdateByPrimaryKey = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableDeleteByPrimaryKey", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableDeleteByPrimaryKey = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableDeleteByExample", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableDeleteByExample = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableCountByExample", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableCountByExample = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.enableUpdateByExample", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableUpdateByExample = new SimpleBooleanProperty(true);
    @AdvancedConfig
    @Config(bundle = "project.selectByPrimaryKeyQueryId", type = ConfigType.CheckableTextField)
    public SimpleStringProperty selectByPrimaryKeyQueryId = new SimpleStringProperty("");
    @AdvancedConfig
    @Config(bundle = "project.selectByExampleQueryId", type = ConfigType.CheckableTextField)
    public SimpleStringProperty selectByExampleQueryId = new SimpleStringProperty("");


    /****Entity层配置***************************************************************************************************/
    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @Config(bundle = "project.defaultModelType", type = ConfigType.ChoiceBox, testRegex = "conditional|flat|hierarchical")
    public StringProperty defaultModelType = new SimpleStringProperty("conditional");
    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)
    @Config(bundle = "project.entityDir", type = ConfigType.TextField)
    public SimpleStringProperty entityDir = new SimpleStringProperty("src/main/java");
    @Config(bundle = "project.entityPackage", type = ConfigType.TextField)
    public SimpleStringProperty entityPackage = new SimpleStringProperty("entity");
    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @Config(bundle = "project.entityObjName", type = ConfigType.TextField)
    public SimpleStringProperty entityObjName = new SimpleStringProperty("");
    @Config(bundle = "project.exampleObjName", type = ConfigType.TextField)
    public SimpleStringProperty exampleObjName = new SimpleStringProperty("");
    @Config(bundle = "project.primaryKey", type = ConfigType.CheckableTextField)
    public SimpleStringProperty primaryKey = new SimpleStringProperty("");

    @EnablePlugin(DeclaredPlugins.VirtualPrimaryKeyPlugin)
    @ExportToPlugin(plugin = DeclaredPlugins.VirtualPrimaryKeyPlugin, key = "virtualKeyColumns")
    @Config(bundle = "project.enableVirtualPrimaryKeyPlugin", type = ConfigType.CheckableTextField)
    public StringProperty enableVirtualPrimaryKeyPlugin = new SimpleStringProperty("");
    @ExportToTab(tabName = SHORTCUT, index = 1)
    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)
    @Config(bundle = "project.generateJPA", type = ConfigType.CheckBox)
    public SimpleBooleanProperty generateJPA = new SimpleBooleanProperty(false);
    @Config(bundle = "project.useActualColumnNames", type = ConfigType.CheckBox)
    public SimpleBooleanProperty useActualColumnNames = new SimpleBooleanProperty(true);

    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)
    @EnablePlugin(DeclaredPlugins.FluentBuilderMethodsPlugin)
    @Config(bundle = "project.enableFluentBuilderMethodsPlugin", type = ConfigType.CheckBox)
    public SimpleBooleanProperty enableFluentBuilderMethodsPlugin = new SimpleBooleanProperty(true);
    @EnablePlugin(DeclaredPlugins.ToStringPlugin)
    @Config(bundle = "project.generateToString", type = ConfigType.CheckBox)
    public SimpleBooleanProperty generateToString = new SimpleBooleanProperty(true);
    @ExportToPlugin(plugin = DeclaredPlugins.ToStringPlugin, key = "useToStringFromRoot")
    @AdvancedConfig
    @Config(bundle = "project.useToStringFromRoot", type = ConfigType.CheckBox)
    public SimpleBooleanProperty useToStringFromRoot = new SimpleBooleanProperty(false);


    @EnablePlugin(DeclaredPlugins.EqualsHashCodePlugin)
    @Config(bundle = "project.generateHashcodeEquals", type = ConfigType.CheckBox)
    public SimpleBooleanProperty generateHashcodeEquals = new SimpleBooleanProperty(true);

    @AdvancedConfig
    @ExportToPlugin(plugin = DeclaredPlugins.EqualsHashCodePlugin, key = "useEqualsHashCodeFromRoot")
    @Config(bundle = "project.useEqualsHashCodeFromRoot", type = ConfigType.CheckBox)
    public SimpleBooleanProperty useEqualsHashCodeFromRoot = new SimpleBooleanProperty(false);

    @EnablePlugin(DeclaredPlugins.SerializablePlugin)
    @Config(bundle = "project.implementsSerializable", type = ConfigType.CheckBox)
    public SimpleBooleanProperty implementsSerializable = new SimpleBooleanProperty(true);
    @ExportToPlugin(plugin = DeclaredPlugins.CommentPlugin)


    @ExportToTab(tabName = tabs.COMMENT)
    @Config(bundle = "project.enableComment", type = ConfigType.CheckBox)
    public BooleanProperty enableComment = new SimpleBooleanProperty(true);
    @ExportToPlugin(plugin = DeclaredPlugins.CommentPlugin)
    @Config(bundle = "project.fileHeader", type = ConfigType.TextArea)
    public SimpleStringProperty fileHeader = new SimpleStringProperty("/**\n" +
            " * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension\n" +
            " * Description:\n * ${tableComment}\n *\n * @author \n */");


    @ExportToTab(tabName = tabs.CACHE)
    @EnablePlugin(DeclaredPlugins.CachePlugin)
    @Config(bundle = "project.enableCachePlugin", type = ConfigType.CheckBox)
    public BooleanProperty enableCachePlugin = new SimpleBooleanProperty(false);
    @ExportToPlugin(plugin = DeclaredPlugins.CachePlugin)
    @Config(bundle = "project.cache_eviction", type = ConfigType.TextField)
    public SimpleStringProperty cache_eviction = new SimpleStringProperty("");
    @ExportToPlugin(plugin = DeclaredPlugins.CachePlugin)
    @Config(bundle = "project.cache_flushInterval", type = ConfigType.TextField)
    public SimpleStringProperty cache_flushInterval = new SimpleStringProperty("");
    @ExportToPlugin(plugin = DeclaredPlugins.CachePlugin)
    @Config(bundle = "project.cache_readOnly", type = ConfigType.TextField)
    public SimpleStringProperty cache_readOnly = new SimpleStringProperty("");
    @ExportToPlugin(plugin = DeclaredPlugins.CachePlugin)
    @Config(bundle = "project.cache_size", type = ConfigType.TextField)
    public SimpleStringProperty cache_size = new SimpleStringProperty("");
    @ExportToPlugin(plugin = DeclaredPlugins.CachePlugin)
    @Config(bundle = "project.cache_type", type = ConfigType.TextField)
    public SimpleStringProperty cache_type = new SimpleStringProperty("");


//    /****Service层配置**************************************************************************************************/
//    @ExportToTab(tabName = tabs.SERVICE)
//    @EnablePlugin(plugins.SerializablePlugin)
//    public String enableRestServicePlugin ="";
//
//    public String serviceDir ="";
//    public String servicePackage ="";
//
//    @ExportToPlugin(plugin = plugins.DemoServicePlugin, key = "restServiceDir")
//    public String getServiceDir() {
//        return projectDir.getValue() + serviceDir.getValue();
//    }


    /*===方法s========================================================================================================*/

    /**
     * 初始化
     * 绑定Property，在初始化控件完成之后，为控件的值添加监听
     */
    public void initialize() {
        reduceTablePrefix.addListener(((observable, oldValue, newValue) -> updateClassName()));
        daoObjSuffix.addListener(((observable, oldValue, newValue) -> updateClassName()));
        entityObjSuffix.addListener(((observable, oldValue, newValue) -> updateClassName()));

        basePackage.addListener(((observable, oldValue, newValue) -> updatePackageName()));
        daoPackageSuffix.addListener(((observable, oldValue, newValue) -> updatePackageName()));
        entityPackageSuffix.addListener(((observable, oldValue, newValue) -> updatePackageName()));
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
        String rawTableName = selectedTable.getValue();
        return getUpperCamel(rawTableName.replaceAll(String.format("^(%s)", reduceTablePrefix.getValue()), ""));
    }

    /**
     * 获取字符串的大骆驼峰形式
     */
    private String getUpperCamel(String s) {
        //如果全大写，且包含下划线
        if (s.replaceAll("[A-Z]+", "").equals(s) && s.contains("_"))
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s);
        //如果不包含下划线
        if (!s.contains("_") && s.length() > 2)
            return s.toUpperCase().charAt(0) + s.substring(1);
        //如果不全为大写，且包含下划线
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s.toLowerCase());
    }
}
