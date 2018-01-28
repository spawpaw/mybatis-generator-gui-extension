# 四步将自己的Plugin的配置暴露到图形化界面中
> 所有的配置都声明在`com.spawpaw.mybatis.generator.gui.ProjectConfig`类中，
> 你只需要声明一个变量，添加上几个注解，你的配置便可自动在GUI中显示，在生成代码时，GUI中的值会自动传递给你的插件

## 第一步，在ProjectConfig中添加配置项
本项目提供了6种配置的显示方式：

1. CheckBox
2. ChoiceBox
3. TextField
4. CheckableTextField
5. TextArea
6. FileChooser

你可以选择一种合适的方式在图形界面中呈现你的配置
## 第二步，为配置项添加`@ExportToTab`注解
该注解将把对应的配置添加到指定选项卡中

## 第三步，为配置项添加`@ExportToPlugin`注解

该注解将把对应的配置传递给指定的Plugin（通过Property）

## 第四步，为配置项添加`@EablePlugin`注解
如果该注解标记的配置的值不为 `null`/`""`/`"false"`中的任何一种，将启用指定的插件
