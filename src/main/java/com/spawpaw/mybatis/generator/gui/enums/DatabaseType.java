package com.spawpaw.mybatis.generator.gui.enums;

import com.spawpaw.mybatis.generator.gui.util.Constants;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;

/**
 * Created By spawpaw@hotmail.com  2018-01-31
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public enum DatabaseType {
    //数据库类型枚举
    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s", ""),


    //如遇错误，尝试在$ORACLE_HOME\NETWORK\ADMIN\sqlnet.ora中配置：
    //SQLNET.ALLOWED_LOGON_VERSION=8
    //    //格式二：jdbc:oracle:thin:@<host>:<port>:<SID>
    Oracle_SID("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s", Constants.ORACLE_DRIVER_NAME),
    //格式一：jdbc:oracle:thin:@//<host>:<port>/<service_name>
    Oracle_ServiceName("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//%s:%s/%s", Constants.ORACLE_DRIVER_NAME),
    //格式三：jdbc:oracle:thin:@<TNSName>
    Oracle_TNSName("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s", Constants.ORACLE_DRIVER_NAME),
    //格式三： give a tnsnames.ora entry-like in the string (here for SSL/TCPS):
    // jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=%s)(port=%s))(connect_data=(service_name=%s)))
    Oracle_TNSEntryString("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=%s)(port=%s))(connect_data=(service_name=%s)))", Constants.ORACLE_DRIVER_NAME),

    Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s", Constants.ORACLE_DRIVER_NAME),

    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s", "postgresql-9.4.1209.jar"),
    SQLServer("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s", ""),

    //TODO 验证数据库连接串
    DB2("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:db2://%s:%s/%s", ""),
    INFORMIX("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:informix-sqli://%s:%s/%s", ""),
    SYBASE("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sybase:Tds://%s:%s/%s", "");


    private String driverClazz;
    private String connectStrFormat;
    private String driverJarFile;

    /**
     * @param driver           Driver的类名
     * @param connectStrFormat connect string的格式化字符串
     * @param driverJarFile    如果在Maven中添加了依赖，可为空 (经测试Oracle即使添加了Maven依赖也不行，Mysql正常)
     */
    DatabaseType(String driver, String connectStrFormat, String driverJarFile) {
        this.driverClazz = driver;
        this.connectStrFormat = connectStrFormat;
        this.driverJarFile = driverJarFile;
    }

    public String getDriverClazzName() {
        return driverClazz;
    }

    public String getConnectStr(String host, String port, String dbName, String encoding) {
        switch (this) {
            case MySQL:
                return String.format(connectStrFormat, host, port, dbName, encoding);
            case Oracle_SID:
                return String.format(connectStrFormat, host, port, dbName);
            case Oracle_ServiceName:
                return String.format(connectStrFormat, host, port, dbName);
            case Oracle_TNSName:
                return String.format(connectStrFormat, dbName);
            case Oracle_TNSEntryString:
                return String.format(connectStrFormat, host, port, dbName);
            case Oracle:
            case PostgreSQL:
            case SQLServer:
            case INFORMIX:
            case SYBASE:
            case DB2:
            default:
                return String.format(connectStrFormat, host, port, dbName);
        }
    }

    public String getDriverJarFilePath() {
        if (driverJarFile.isEmpty())
            return "";
        File file = new File("lib/" + driverJarFile);
        return file.getAbsolutePath();
    }

    public Driver getDriver() {
        try {
            //尝试直接加载Driver
            return (Driver) (ClassLoader.getSystemClassLoader().loadClass(driverClazz).newInstance());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            //尝试从lib目录加载driver
            if (driverJarFile.isEmpty()) {
                System.out.println("既没有在classpath中添加Driver，也没有为程序指定Driver所在的jar包，\n请联系作者解决该问题，或向lib目录下添加您所使用数据库的jar包，然后在DatabaseType中将该数据库配置的第三个参数改成您所使用的jar包的文件名");
            } else {
                try {
                    System.out.println(new File("lib/" + driverJarFile).getAbsoluteFile());
                    URLClassLoader loader = new URLClassLoader(new URL[]{new File("lib/" + driverJarFile).toURI().toURL()});
                    Class clazz = loader.loadClass(driverClazz);
                    return (Driver) clazz.newInstance();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }
}