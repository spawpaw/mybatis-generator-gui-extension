# 四步将自己的Plugin的配置暴露到图形化界面中
> 所有的配置都声明在`com.spawpaw.mybatis.generator.gui.ProjectConfig`类中，
> 你只需要声明一个变量，添加上几个注解，你的配置便可自动在GUI中显示，在生成代码时，GUI中的值会自动传递给你的插件

例如：
```java
    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)//指定配置显示在哪个选项卡中
    @EnablePlugin(DeclaredPlugins.EqualsHashCodePlugin)//如果该变量的值不为false或空，则启用指定的插件
    @ExportToPlugin(plugin = DeclaredPlugins.ToStringPlugin)//将变量的值传递给你的plugin
    @AdvancedConfig//如果加上该注解，在简洁模式时将隐藏该配置（配置仍然有效）
    @Config(bundle = "project.generateHashcodeEquals", type = ConfigType.CheckBox)//指定UI的类型
    public SimpleBooleanProperty generateHashcodeEquals = new SimpleBooleanProperty(true);
```



## 第一步，在ProjectConfig中添加配置项
本项目提供了6种配置的显示方式：

1. CheckBox
2. ChoiceBox
3. TextField
4. CheckableTextField
5. TextArea
6. FileChooser
只需定义一个`StringProperty`或者`BooleanProperty`，再加上一个注解，即可将配置暴露到UI中
```java
@Config(label = "Mapper目录", type = ConfigType.TextField)
public SimpleStringProperty mapperDir = new SimpleStringProperty("src/main/resources");
```


## 第二步，为配置项添加`@ExportToTab`注解
该注解将把对应的配置添加到指定选项卡中
```java
    @ExportToTab(tabName = DOMAIN_OBJECT, index = 1)//指定配置显示在哪个选项卡中 
```
## 第三步，为配置项添加`@ExportToPlugin`注解

该注解将把对应的配置传递给指定的Plugin
```java
    @ExportToPlugin(plugin = DeclaredPlugins.ToStringPlugin)//将变量的值传递给你的plugin
```

## 第四步，为配置项添加`@EablePlugin`注解
如果该注解标记的配置的值不为 `null`/`""`/`"false"`中的任何一种，将启用指定的插件
```java
    @EnablePlugin(DeclaredPlugins.ToStringPlugin)//如果值为真，则启用toString插件
```