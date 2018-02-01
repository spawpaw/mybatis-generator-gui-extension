package com.spawpaw.mybatis.generator.gui;

import com.spawpaw.mybatis.generator.gui.annotations.Config;
import com.spawpaw.mybatis.generator.gui.annotations.ConfigType;
import com.spawpaw.mybatis.generator.gui.controls.ControlsFactory;
import com.spawpaw.mybatis.generator.gui.entity.TableColumnMetaData;
import com.spawpaw.mybatis.generator.gui.enums.DatabaseType;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class DatabaseConfig implements Serializable {
    public transient Map<String, List<TableColumnMetaData>> tableConfigs;
    @Config(label = "保存名称")
    public SimpleStringProperty savedName = new SimpleStringProperty("untitled");

    @Config(
            label = "数据库类型",
            testRegex = "MySQL|Oracle|PostgreSQL|SQLServer",
            type = ConfigType.ChoiceBox
    )
    public SimpleStringProperty databaseType = new SimpleStringProperty("MySQL");
    @Config(label = "数据库名称")
    public SimpleStringProperty dbName = new SimpleStringProperty("hn");
    @Config(label = "host")
    public SimpleStringProperty host = new SimpleStringProperty("localhost");
    @Config(label = "port")
    public SimpleStringProperty port = new SimpleStringProperty("3306");
    @Config(label = "userName")
    public SimpleStringProperty userName = new SimpleStringProperty("root");
    @Config(label = "password")
    public SimpleStringProperty password = new SimpleStringProperty("123456");
    @Config(
            label = "encoding",
            testRegex = "utf8",
            type = ConfigType.ChoiceBox
    )
    public SimpleStringProperty encoding = new SimpleStringProperty("utf8");
    private transient TreeItem<String> rootItem;

    public String driver() {
        return DatabaseType.valueOf(databaseType.getValue()).driverClazz;
    }

    public String connectionUrl() {
        return DatabaseType.valueOf(databaseType.getValue()).getConnectStr(
                host.getValue(),
                port.getValue(),
                dbName.getValue(),
                encoding.getValue()
        );
    }

    private Connection getConnection() throws SQLException {
        //加载驱动
        try {
            Class.forName(driver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获得数据库连接
        System.out.println(userName);
        return DriverManager.getConnection(connectionUrl(), userName.getValue(), password.getValue());
    }

    public void test() throws SQLException {
        Connection connection = getConnection();
        connection.close();
    }

    public TreeItem<String> getRootTreeItem() {
        if (rootItem == null)
            rootItem = new TreeItem<>();
        rootItem.setValue(String.format(
                "%s(%s@%s:%s/%s)",
                savedName.getValue(),
                userName.getValue(),
                host.getValue(),
                port.getValue(),
                dbName.getValue())
        );
        return rootItem;
    }

    /**
     * 连接数据库，初始化表信息
     */
    public void connect() throws SQLException {
        if (tableConfigs != null && tableConfigs.size() != 0) return;
        tableConfigs = new HashMap<>();
        Connection connection = getConnection();
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet rs;

        String[] types = {"TABLE", "VIEW"};
        //获取表列表
        switch (DatabaseType.valueOf(databaseType.getValue())) {
            case MySQL:
                rs = meta.getTables(null, dbName.getValue(), null, types);
                break;
            case Oracle:
                rs = meta.getTables(null, userName.getValue().toUpperCase(), null, types);
                break;
            case SQLServer:
                rs = meta.getTables(null, null, "%", types);
                break;
            case PostgreSQL:
                rs = meta.getTables(null, "%", "%", types);
                break;
            case DB2:
                rs = meta.getTables(null, "jence_user", "%", types);
                break;
            case SYBASE:
                rs = meta.getTables(null, null, "%", types);
                break;
            case INFORMIX:
                rs = meta.getTables(null, null, "%", types);
                break;
            default:
                System.out.println("不支持的数据库");
                throw new RuntimeException("不支持的数据库");
        }
        while (rs.next()) {
            tableConfigs.put(rs.getString(3), new ArrayList<>());
        }

        //获取每个表中的字段信息
        for (String tableName : tableConfigs.keySet()) {
            //生成表的基本信息（每个字段的名称、类型）
            rs = meta.getColumns(null, null, tableName, null);
            while (rs.next()) {
                TableColumnMetaData columnMetaData = new TableColumnMetaData();
                columnMetaData.setColumnName(rs.getString("COLUMN_NAME"));
                columnMetaData.setJdbcType(rs.getString("TYPE_NAME"));
                tableConfigs.get(tableName).add(columnMetaData);
            }

            //生成TreeView
            TreeItem<String> item = new TreeItem<>(tableName);
            rootItem.getChildren().add(item);
            rootItem.setExpanded(true);
        }
    }

    public void close() {
        if (rootItem != null)
            rootItem.getChildren().clear();
    }


    public VBox getLayout() {
        VBox vBox = new VBox();
        try {
            for (Field field : DatabaseConfig.class.getFields()) {
                if (field.getAnnotation(Config.class) != null && field.get(this) instanceof Property) {
                    vBox.getChildren().addAll(ControlsFactory.getLayout(field.getAnnotation(Config.class), (Property) field.get(this)));
                } else {
                    System.out.println("数据库设置有非法域");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return vBox;
    }
}
