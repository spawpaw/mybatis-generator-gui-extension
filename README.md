# mybatis-generator-gui-extension


<!-- Badges section here. -->
[![Crates.io](https://img.shields.io/crates/l/rustc-serialize.svg)](https://github.com/spawpaw/mybatis-generator-gui-extension/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/spawpaw/mybatis-generator-gui-extension.svg?branch=master)](https://travis-ci.org/spawpaw/mybatis-generator-gui-extension)
[![Github Releases](https://img.shields.io/github/downloads/atom/atom/latest/total.svg)](https://github.com/spawpaw/mybatis-generator-gui-extension/releases)

[![GitHub forks](https://img.shields.io/github/forks/spawpaw/mybatis-generator-gui-extension.svg?style=social&label=Fork)](https://github.com/spawpaw/mybatis-generator-gui-extension/fork)
[![GitHub stars](https://img.shields.io/github/stars/spawpaw/mybatis-generator-gui-extension.svg?style=social&label=Star)](https://github.com/spawpaw/mybatis-generator-gui-extension/star)

## 简介

**mybatis-generator-gui-extension**是一个为MybatisGenerator编写的图形化界面，集成了几乎所有`mybatis generator`的默认配置。

#### there's also a README written in English [English README](./README-en.md)

### [查看更多预览图片](./wiki/PREVIEW.md)


![示例图片](./wiki/images/main_window.png)

## 特性  ([进入帮助页面](https://github.com/spawpaw/mybatis-generator-gui-extension/wiki))

- 配置全面，包含几乎所有mybatis-generator的配置
- 可方便的与您自己的插件进行集成（参见[四步将Plugin的配置暴露到图形化界面中](./wiki/IntegrationOfYourPlugin.md)）
- 可开启简洁模式，隐藏不常用的配置(开关在右上角)
- 鼠标悬停即可显示帮助信息，方便快捷
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
    - `CaseInsensitiveLike插件`：为Example类生成CaseInsensitiveLike方法
    - `MapperAnnotationPlugin`：为java接口添加@Mapper注解

## 如何使用

> 运行前请确保您的JDK版本为1.8u40以上  
> 推荐使用git克隆仓库到本地，这样当本项目更新时直接pull即可

### 方法一：下载源代码

1. 点击右上角**Clone or download**或用`git`拷贝代码仓库：`git clone https://github.com/spawpaw/mybatis-generator-gui-extension.git`

2. 用IDE将源代码导入为Maven项目，然后直接运行`com.spawpaw.mybatis.generator.gui.GeneratorGuiRunner`即可


### 方法二：下载jar包

可以执行`mvn: package` 自助构建  
或者[选择版本进行下载](https://github.com/spawpaw/mybatis-generator-gui-extension/releases)  


## 贡献&&交流
项目地址 https://github.com/spawpaw/mybatis-generator-gui-extension  

如果您在使用过程中遇到了BUG，或者想让软件添加某些功能，请挂issue或者联系作者：<spawpaw@hotmail.com>

QQ交流群：171209016

## 其他
如果您觉得本软件对您有帮助，请别忘记给这个项目一个`star`   (ﾉ*･ω･)ﾉ

[捐赠](./wiki/donate.md) （[捐助者列表](https://github.com/spawpaw/mybatis-generator-gui-extension/wiki/sponsors)）