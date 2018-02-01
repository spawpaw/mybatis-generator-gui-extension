package com.spawpaw.mybatis.generator.gui.enums;

/**
 * Created By spawpaw@hotmail.com  2018-01-31
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public enum DatabaseType {
    //数据库类型枚举
    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s"),
    Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),
    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s"),
    SQLServer("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s"),

    //TODO 验证数据库连接串
    DB2("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s"),
    INFORMIX("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s"),
    SYBASE("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s");


    public String driverClazz;
    public String connectStrFormat;

    DatabaseType(String driver, String connectStrFormat) {
        this.driverClazz = driver;
        this.connectStrFormat = connectStrFormat;
    }

    public String getDriverClazz() {
        return driverClazz;
    }

    public String getConnectStr(String host, String port, String dbName, String encoding) {
        return String.format(connectStrFormat, host, port, dbName, encoding);
    }
}