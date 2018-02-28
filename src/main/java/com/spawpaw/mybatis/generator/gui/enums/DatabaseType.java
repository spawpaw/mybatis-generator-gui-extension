package com.spawpaw.mybatis.generator.gui.enums;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
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

    //格式一：jdbc:oracle:thin:@//<host>:<port>/<service_name>
    //格式二：jdbc:oracle:thin:@<host>:<port>:<SID>
    //格式三：jdbc:oracle:thin:@<TNSName>

    //如遇错误，尝试在$ORACLE_HOME\NETWORK\ADMIN\sqlnet.ora中配置：
    //SQLNET.ALLOWED_LOGON_VERSION=8
    Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=%s)(port=%s))(connect_data=(service_name=%s)))", "ojdbc8.jar"),

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
        try {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("drivers/" + driverJarFile).toURI());
            return file.getAbsolutePath();
        } catch (URISyntaxException e) {
            System.out.println("没有找到数据库驱动jar");
        }
        return "";
    }

    public Driver getDriver() {
        try {
            if (driverJarFile.isEmpty()) {
                return (Driver) (ClassLoader.getSystemClassLoader().loadClass(driverClazz).newInstance());
            } else {
                File file = new File(Thread.currentThread().getContextClassLoader().getResource("drivers/" + driverJarFile).toURI());
                URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()});
                Class clazz = loader.loadClass(driverClazz);
                return (Driver) clazz.newInstance();
            }
        } catch (URISyntaxException | IllegalAccessException | MalformedURLException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}