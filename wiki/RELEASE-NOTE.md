
### 核心功能：
- Multiple language: Automatically selecting language when you start this app.
- 支持主流数据库(目前已对`Mysql`和`Oracle`进行了测试，如果您在连接数据库时遇到了问题，请联系开发者解决)
- 可选择生成不同类型的Mapper
  - `Annotated Mapper`: 生成的对象为**基于注解**和**MyBatis 3.x SqlProviders**的Java接口，不会生成XML
  - `Mixed Mapper`:  生成的对象为**基于注解**和**MyBatis 3.x SqlProviders**的Java接口。但复杂的动态SQL语句将采用XML形式
  - `XML Mapper`:  生成的对象是依赖于XML mapper的Java接口
- 可选择生成实体的模式：
    - `hierarchical`:	层次结构。 如果表有主键，则生成一个主键类。如果表有BLOB列，则生成另一个类来存放这些字段。再生成一个类来存放表的其他字段
    - `conditional`:	根据条件自动选择。默认值。  
                        不会生成只包含一个字段的对象。即，如果一个表只有一个主键字段，这个字段将会被移入base类。其余均和`hierarchical`相同
    - `flat`:          扁平结构。只为表生成一个实体对象。这个对象将包含表中的所有字段
- 可方便的将自己的插件整合进本项目（几行代码）
- 可选择是否将列名转化为小骆驼峰形式
- 可自定义列属性，包括：
    - 可忽略表中的某些字段，这些字段将不会出现在Mapper和Entity中
    - 可设置列的`java type`、`java property`、`type handler`、`column override`
- 可选择DAO接口生成哪些方法（8种）
- 可自定义`selectByPrimaryKeyQueryId`、`selectByExampleQueryId` 
- 内置丰富插件：
    - `toString插件`。为实体生成toString方法
    - `分页插件`。生成基于limit/offset的分页查询
    - `注释插件`：
        - 为字段添加注释（来自表中字段的注释）
        - 为类头添加注释(File Header)（来自数据库中表的注释）
        - 为实体域添加JPA注解
    - `流式构建插件`：  方便的对实体进行链式调用。例如：    
            ```
            User user = new User().withUserName("uName"").withPassword("pwd"");
            ```  
    - `缓存插件`：为生成的XML添加 <cache> 标签
    - `虚拟主键插件`：指定某些列作为主键
    - `CaseInsensitiveLike插件`：为Example类生成CaseInsensitiveLike方法
    - `MapperAnnotationPlugin`：为java接口添加@Mapper注解
    
### 实用功能
- 可开启简洁模式，隐藏不常用的配置
- 可保存数据库连接，可保存基本配置，方便下次使用
- 可指定Mapper、DAO等文件的目录、包名
    - 可直接设置根包、entity包后缀、dao包后缀，软件将会自动更新entity、mapper的包名
- 可自定义生成的各种对象的名称，内置相关的各种增强功能：
    - 可使用如下过滤器，选择表后会自动生成大骆驼峰形式的对象名：
      - 表名去前缀：去除表的前缀（如`y_`）
      - 自定义Mapper、Entity(POJO)、Example后缀
      
### 发行包选择
本次Release有三个包，可按需进行选择
- 后缀为`exe`的为带运行时环境的windows可执行程序(72MB)，可直接运行其中的`gui-extension-1.0-beta.exe`，不需安装java
- 后缀为`jar`的为打包好的jar包(6.20MB)，使用`JDK1.8u40`以上环境即可运行
- 源代码，有`tar.gz`和`zip`两种压缩格式(约0.16MB)