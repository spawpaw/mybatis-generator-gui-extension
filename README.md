# mybatis-generator-gui-extension

## 简介
**mybatis-generator-gui-extension**是一个为MybatisGenerator编写的图形化界面，集成了几乎所有`mybatis generator`的默认配置。

[查看更多预览图片](./wiki/PREVIEW.md)


![示例图片](./wiki/images/main_window.png)
## 特性
- 省去繁琐的让人眼花缭乱的XML配置，在图形化界面中轻松生成代码
- 配置全面，包含几乎所有mybatis-generator的配置
- 可方便的与您自己的插件进行集成（参见[四步将Plugin的配置暴露到图形化界面中](./wiki/IntegrationOfYourPlugin.md)）
- 可开启简洁模式，隐藏不常用的配置(开关在右上角)
- 鼠标悬停即可显示帮助信息，方便快捷
    ![示例图片](./wiki/images/tooltip_example.png)
- 可以选择语言（目前支持中英两种语言）
- 内置丰富插件：
    - `toString插件`。为实体生成toString方法
    - `分页插件`。生成基于limit/offset的分页查询
    - `注释插件`：为字段和类生成注释（来自表和字段的注释）
    - `流式构建插件`：  方便的对实体进行链式调用。例如：    
            ```
            User user = new User().withUserName("uName"").withPassword("pwd"");
            ```  
    - `缓存插件`：为生成的XML添加 <cache> 标签
    - `虚拟主键插件`：指定某些列作为主键
    
## 如何使用
### 方法一：下载源代码
点击右上角**Clone or download**或用git拷贝代码仓库：`git clone https://github.com/spawpaw/mybatis-generator-gui-extension.git`

用IDE打开项目，然后直接运行`com.spawpaw.mybatis.generator.gui.GeneratorGuiRunner`即可


### 方法二：下载jar包
可以执行`mvn: jfx:jar` 自助构建  
或者[选择版本进行下载]<https://github.com/spawpaw/mybatis-generator-gui-extension/releases>  
> 请保证您的JDK版本为1.8u60以上


## 贡献&&交流
项目地址 https://github.com/spawpaw/mybatis-generator-gui-extension  

如果您在使用过程中遇到了BUG，或者想让软件添加某些功能，请挂issue或者联系作者：<spawpaw@hotmail.com>

QQ交流群：171209016

## 其他
如果您觉得本软件对您有帮助，请别忘记给这个项目一个`star`   (ﾉ*･ω･)ﾉ
