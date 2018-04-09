# mybatis-generator-gui-extension

## Description
**mybatis-generator-gui-extension** is a powerful GUI tool for MybatisGenerator, it has almost every config that MBG have.

#### there's also a README written in simplified chinese [中文版README](./README-zh_CN.md)

## [show more preview images](./wiki/PREVIEW-en.md)


![Preview Img](./wiki/images/main_window-en.png)

## Features
- A brief gui extension for MBG
- Comprehensive configs
- Easy to make integration with your own MBG plugin（goto [4 steps to make integration with yourPlugin](./wiki/IntegrationOfYourPlugin-en.md)）
- Can hide Unnecessary config(Simple Mode,at right top of the window)
- Show tooltips on mouse over
- Comprehensive plugins：
    - `ToString plugin`: generate `toString` method
    - `Page plugin`: generate queries based on limit/offset
    - `Comment plugin`: generate comment for fields and class(based on comments from database)
    - `FluentBuilder plugin`: you can configure the property values fluently with chained method calls. e.g    
            ```
            User user = new User().withUserName("uName"").withPassword("pwd"");
            ```  
    - `Cache plugin`: This plugin adds a <cache> element to generated SQL maps.
    - `VirtualPrimaryKey plugin`: specify columns that act as primary keys, even if they are not defined as primary key in the database.
    - `CaseInsensitiveLike plugin`:support case insensitive LIKE searches. 
    - `MapperAnnotation plugin`: This plugin adds the `@Mapper` annotation to generated mapper interfaces.
    
## Usage

> PLEASE make sure that you have a `1.8u40 plus` Java Runtime  
> git is recommended

### Run the code directly 

click **Clone or download** at top right of this page  
or use git to clone this repo: `git clone https://github.com/spawpaw/mybatis-generator-gui-extension.git`

Then open the project with any java IDEs that you like, then run `com.spawpaw.mybatis.generator.gui.GeneratorGuiRunner`


### Run .jar
You can run maven script: `mvn: package` to build a jar
or [select a version to download]<https://github.com/spawpaw/mybatis-generator-gui-extension/releases>  


## Contribution && Communicate
git repo: https://github.com/spawpaw/mybatis-generator-gui-extension  

If you have any problems while using this software, please push a issue or contact the author:<spawpaw@hotmail.com>

QQ group: 171209016

## Others:
If you feels that this project helpful to you ,please give it a `star`  (ﾉ*･ω･)ﾉ

[Donate](./wiki/donate.md)