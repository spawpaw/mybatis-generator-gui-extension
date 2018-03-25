# 约定


## 哪些Model会被传入模板文件
默认只传入一个`table`，类型为`com.spawpaw.mybatis.generator.gui.entity.Table`,每个table包含多个column，具体可查看这些类的具体定义

## 可以从table中get到哪些值
- ProjectConfig中导出到本插件的值，例如  `projectDir`、`basePackage`
- 从表注释中导出的信息

## 可以从column中get到哪些值
- 所有所在表中的配置信息
- 从字段注释中导出的信息

## 表/字段注释中标签的约定
在表或字段注释中添加的类似`#label()`的标签，都会展开进模型类中map的键值对，
在模板文件中，可通过`get(key)`方法获取或者用`contains(key)`来判断是否存在，例如`table.contains("search.like")`

只需以`#`开头，后接任意个非`)`,最后再加上一对`()`，括号里放入所需的参数即可

- `#bundle(text)`:  如果使用i18n,该项为以下三个标签的公共前缀
- `#label(text)`:   标签，如在表格的表头，或表单中的label
- `#help(text)`:    帮助信息，如鼠标悬停时展示的信息
- `#prompt(text)`:  提示信息，如input框提示输入


- `#search(equal,like,likeIgnoreCase,between,larger,less)`: 搜索功能（参数可选），将展开为如下配置：
    - `search.like`:   相似搜索
    - `search.likeIgnoreCase`: 相似搜索（不区分大小写）
    - `search.between`:  区间搜索
    - `search.larger`:   大于
    - `search.less`:     小于