# mybatis-generator-gui-extension


<!-- Badges section here. -->
[![Crates.io](https://img.shields.io/crates/l/rustc-serialize.svg)](https://github.com/spawpaw/mybatis-generator-gui-extension/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/spawpaw/mybatis-generator-gui-extension.svg?branch=master)](https://travis-ci.org/spawpaw/mybatis-generator-gui-extension)
[![Github Releases](https://img.shields.io/github/downloads/atom/atom/latest/total.svg)](https://github.com/spawpaw/mybatis-generator-gui-extension/releases)

[![GitHub forks](https://img.shields.io/github/forks/spawpaw/mybatis-generator-gui-extension.svg?style=social&label=Fork)](https://github.com/spawpaw/mybatis-generator-gui-extension/fork)
[![GitHub stars](https://img.shields.io/github/stars/spawpaw/mybatis-generator-gui-extension.svg?style=social&label=Star)](https://github.com/spawpaw/mybatis-generator-gui-extension/star)

<!-- /Badges section end. -->

## 简介

**mybatis-generator-gui-extension**是一个为MybatisGenerator编写的图形化界面，为`实体`/`Example`/`Mapper`提供了丰富的扩展。

### [查看更多预览图片](./wiki/PREVIEW.md)      [English README](./wiki/README-en.md)


![示例图片](./wiki/images/main_window.png)

## 功能清单
- `文件合并`：在重新生成代码时，将保留没有添加`@mbg.generated`注释的字段/方法/SQL语句
    - 可合并的文件：`实体类`、`Example`、`Mapper.java`、`Mapper.xml`
- `自动包裹关键字`：对于表名/字段名为关键字的情况，可指定用 ` 或其他字符串包裹，防止冲突
- `实体类增强`
    - `rootClass`(可选)：可为实体对象指定RootClass
    - `trim`(可选)：为String类型的setter添加`trim`
    - `使用真实列名`(可选)：可指定是否使用真实列名
    - `流式构建`(可选)：直接使用`User user= new User().withUsername("uname").withPassword("123");`创建对象
    - `toString`(可选)：生成toString方法(可选择是否调用父类的`toString`方法)
    - `equals/hashCode` (可选)：生成`equals/hashCode` 方法（可选择是否调用父类的equals/hashCode 方法）
    - `Serializable`（可选）：继承`Serializable`接口
- `Example增强`
    - `CaseInsensitiveLike`：添加CaseInsensitiveLike查询方式
    - `ExampleCriteria增强插件`
    - `真实字段获取插件`
    - `分页插件`：提供基于limit/offset的分页。
- `Mapper增强`
    - `指定Mapper类型`：可选择`纯xml`/`java和xml混合`/`纯java`的方式生成mapper
    - `指定主键`：可指定表的主键，将会自动为该表生成获取自增主键的SQL
    - `虚拟主键`：
    - `逻辑删除`
    - `批量插入`
    - `添加@Mapper注解`
    - `各个方法的开关`
- `生成JPA注解`
- `指定类头注释`：自定义生成的文件的注释
- `缓存`：采用MBG官方的缓存插件，为生成的查询方法提供cache标签
- 指定BasPackage
- `对象重命名`：
    - `Example` 类的重命名
    - `实体` 类的重命名
    - `Mapper` 类的重命名
- `指定后缀`： 可修改如下项目的后缀：`dao包`、`实体包`、`Mapper`、`Example`、`实体`。
## 如何使用

> 运行前请确保您的JDK版本为1.8u40以上  
> 推荐使用git克隆仓库到本地，这样当本项目更新时直接pull即可

### 方法一：下载源代码

1. 点击右上角**Clone or download**，或用`git命令`拷贝代码仓库：`git clone https://github.com/spawpaw/mybatis-generator-gui-extension.git`

2. 用IDE将源代码导入为Maven项目，然后直接运行`com.spawpaw.mybatis.generator.gui.GeneratorGuiRunner`即可


### 方法二：下载jar包

可以执行`mvn: package` 自助构建  
或者[选择版本进行下载](https://github.com/spawpaw/mybatis-generator-gui-extension/releases)  

## 二次开发 && 贡献 && 交流 ([进入帮助页面](https://github.com/spawpaw/mybatis-generator-gui-extension/wiki))

- 如果您开发了自己的MBG插件，只需几行代码便可将其暴露到图形界面中，无需了解整个项目的构造。（参见[四步将Plugin的配置暴露到图形化界面中](./wiki/IntegrationOfYourPlugin.md)）


如果您在使用过程中遇到了BUG，或者想让软件添加某些功能，请挂issue或者联系作者：<spawpaw@hotmail.com>

项目地址 https://github.com/spawpaw/mybatis-generator-gui-extension  
QQ交流群：171209016

## 其他
如果您觉得本软件对您有帮助，请别忘记给这个项目一个`star`   ο(=•ω＜=)ρ⌒★

[捐赠](./wiki/donate.md) （[捐助者列表](https://github.com/spawpaw/mybatis-generator-gui-extension/wiki/sponsors)）
