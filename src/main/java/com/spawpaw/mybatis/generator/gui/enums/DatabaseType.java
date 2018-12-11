package com.spawpaw.mybatis.generator.gui.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Driver;

/**
 * Created By spawpaw@hotmail.com  2018-01-31
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public enum DatabaseType {
    //数据库类型枚举
    MySQL("com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s&serverTimezone=UTC&allowPublicKeyRetrieval=true", "MySql"),


    //如遇错误，尝试在$ORACLE_HOME\NETWORK\ADMIN\sqlnet.ora中配置：
    //SQLNET.ALLOWED_LOGON_VERSION=8
    //    //格式二：jdbc:oracle:thin:@<host>:<port>:<SID>
    Oracle_SID("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s", "JDBC"),
    //格式一：jdbc:oracle:thin:@//<host>:<port>/<service_name>
    Oracle_ServiceName("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//%s:%s/%s", "JDBC"),
    //格式三：jdbc:oracle:thin:@<TNSName>
    Oracle_TNSName("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s", "JDBC"),
    //格式三： give a tnsnames.ora entry-like in the string (here for SSL/TCPS):
    // jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=%s)(port=%s))(connect_data=(service_name=%s)))
    Oracle_TNSEntryString("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=%s)(port=%s))(connect_data=(service_name=%s)))", "JDBC"),

    Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s", "JDBC"),

    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s", "JDBC"),
    SQLServer("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s", "SqlServer"),
    SQLServer_InstanceBased("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s;instanceName=%s;DatabaseName=%s", "SqlServer"),

    //TODO 验证数据库连接串
    DB2("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:db2://%s:%s/%s", "DB2"),
    DB2MF("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:db2://%s:%s/%s", "DB2MF"),
    INFORMIX("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:informix-sqli://%s:%s/%s", "Informix"),
    SYBASE("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sybase:Tds://%s:%s/%s", "SYBASE");

    Logger log = LoggerFactory.getLogger(DatabaseType.class);

    private String driverClazz;
    private String connectStrFormat;
    private String sqlStatement;

    /**
     * @param driver           Driver的类名
     * @param connectStrFormat connect string的格式化字符串
     * @param sqlStatement     获取自增主键的sqlStatement，如果不是MBG支持的数据库，则使用JDBC
     */
    DatabaseType(String driver, String connectStrFormat, String sqlStatement) {
        this.driverClazz = driver;
        this.connectStrFormat = connectStrFormat;
        if (sqlStatement == null || sqlStatement.isEmpty())
            this.sqlStatement = "JDBC";
        else this.sqlStatement = sqlStatement;
    }

    public String getDriverClazzName() {
        return driverClazz;
    }

    public String getSqlStatement() {
        return sqlStatement;
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
            case SQLServer:
            case SQLServer_InstanceBased:
            case Oracle:
            case PostgreSQL:
            case INFORMIX:
            case SYBASE:
            case DB2:
            case DB2MF:
            default:
                return String.format(connectStrFormat, host, port, dbName);
        }
    }

    public Driver getDriver() {
        try {
            //尝试直接加载Driver
            return (Driver) (ClassLoader.getSystemClassLoader().loadClass(driverClazz).newInstance());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("找不到数据库驱动，请联系开发人员");
        }
        return null;
    }
}