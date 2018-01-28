package com.spawpaw.mybatis.generator.gui.util;

import javafx.geometry.Insets;

import java.util.*;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 常量池
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Constants {
    //保存配置的目录
    public static final String CONFIG_SAVE_PATH = "data/config/";
    public static final String CONNECTION_SAVE_PATH = "data/connection/";

    //mapper的生成形式，详情参见http://www.mybatis.org/generator/configreference/javaClientGenerator.html
    public static final String[] javaClientGeneratorMapperType = {"ANNOTATEDMAPPER", "MIXEDMAPPER", "XMLMAPPER"};

    //当前系统的语言，为国际化准备（现在还没写i18n hhhh:)
    public static Locale CURRENT_LOCALE = Locale.CHINA;

    //数据库类型枚举
    public enum database_type {
        MySQL("MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s"),
        Oracle("Oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),
        PostgreSQL("PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s"),
        SQLServer("SQL Server", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s");

        public static List<String> dbs;
        public static Map<String, database_type> map;
        public String name;
        public String driverClazz;
        public String connectStr;

        database_type(String name, String driver, String connectStr) {
            add(name, this);
            this.driverClazz = driver;
            this.connectStr = connectStr;
        }

        public static database_type getDbType(String s) {
            return map.get(s);
        }

        private void add(String s, database_type context) {
            if (dbs == null)
                dbs = new ArrayList<>();
            if (map == null)
                map = new HashMap<>();
            map.put(s, this);
            database_type.dbs.add(s);
        }
    }

    //字符集，连接数据库时可能要用到
    public enum charsets {
        UTF8("UTF8", "utf8");
        public static List<String> charsets;
        String displayName;
        String realValue;

        charsets(String displayName, String realValue) {
            this.displayName = displayName;
            this.realValue = realValue;
            add(displayName);
        }

        private void add(String s) {
            if (charsets == null)
                charsets = new ArrayList<>();
            charsets.add(displayName);
        }
    }

    //选项卡，当配置过多时，将分文多个选项卡显示，在这里统一管理选项卡的名称
    public enum tabs {
        BASIC_SETTINGS("基本设置"),
        DATA_ACCESS_OBJECT("Mapper设置"),
        DOMAIN_OBJECT("实体域设置"),
        SERVICE("Service"),
        COMMENT("注释设置");

        public String title;

        tabs(String title) {
            this.title = title;
        }
    }

    //与UI有关的常量
    public static class ui {
        public static final String MAIN_WINDOW_TITLE = "GUI extension for mybatis-generator";
        public static final int MIN_TEXT_FIELD_WIDTH = 360;
        public static final Insets DEFAULT_CTL_INSETS = (new Insets(28, 0, 0, 0));
        public static final Insets DEFAULT_LAYER_INSETS = (new Insets(8, 8, 8, 8));
    }


    //插件列表，写在一起防止弄混
    public static class plugins {
        public static final String CachePlugin = "org.mybatis.generator.plugins.CachePlugin";
        public static final String CaseInsensitiveLikePlugin = "org.mybatis.generator.plugins.CaseInsensitiveLikePlugin";
        public static final String EqualsHashCodePlugin = "org.mybatis.generator.plugins.EqualsHashCodePlugin";
        public static final String FluentBuilderMethodsPlugin = "org.mybatis.generator.plugins.FluentBuilderMethodsPlugin";
        public static final String MapperConfigPlugin = "org.mybatis.generator.plugins.MapperConfigPlugin";
        public static final String RenameExampleClassPlugin = "org.mybatis.generator.plugins.RenameExampleClassPlugin";
        public static final String RowBoundsPlugin = "org.mybatis.generator.plugins.RowBoundsPlugin";
        public static final String SerializablePlugin = "org.mybatis.generator.plugins.SerializablePlugin";
        public static final String SqlMapConfigPlugin = "org.mybatis.generator.plugins.SqlMapConfigPlugin";
        public static final String ToStringPlugin = "org.mybatis.generator.plugins.ToStringPlugin";
        public static final String VirtualPrimaryKeyPlugin = "org.mybatis.generator.plugins.VirtualPrimaryKeyPlugin";


        public static final String CommentPlugin = "com.spawpaw.mybatis.generator.gui.plugin.CommentPlugin";
        public static final String DemoServicePlugin = "com.spawpaw.mybatis.generator.gui.plugin.DemoServicePlugin";
        public static final String PagePlugin = "com.spawpaw.mybatis.generator.gui.plugin.PagePlugin";
    }
}
