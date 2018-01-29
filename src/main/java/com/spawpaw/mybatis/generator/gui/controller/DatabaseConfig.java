package com.spawpaw.mybatis.generator.gui.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.spawpaw.mybatis.generator.gui.config.ChoiceBoxConfig;
import com.spawpaw.mybatis.generator.gui.config.Config;
import com.spawpaw.mybatis.generator.gui.config.TextFieldConfig;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class DatabaseConfig extends BaseController implements Serializable {
    public SimpleStringProperty selectedTable = new SimpleStringProperty("");

    public TextFieldConfig savedName = new TextFieldConfig("保存名称", "untitled");
    public ChoiceBoxConfig databaseType = new ChoiceBoxConfig("数据库类型", "MySQL", Constants.database_type.dbs);
    public TextFieldConfig host = new TextFieldConfig("主机名/IP", "localhost");
    public TextFieldConfig port = new TextFieldConfig("端口号", "3306");
    public TextFieldConfig username = new TextFieldConfig("用户名", "root");
    public TextFieldConfig password = new TextFieldConfig("密码", "123456");
    public TextFieldConfig dbName = new TextFieldConfig("数据库名", "please Input Your Database Name");
    public ChoiceBoxConfig encoding = new ChoiceBoxConfig("编码", "utf-8", Constants.charsets.charsets);
    public transient List<TableColumnMetaData> columns;
    transient TreeItem<String> rootItem;
    private transient JFXButton btn_test_connection = new JFXButton("测试连接");
    private transient JFXButton btn_save_connection = new JFXButton("保存");
    private transient JFXButton btn_cancel = new JFXButton("取消");
    private transient Connection connection;
    private transient Stage dialog;

    public DatabaseConfig() {
    }

    public String driver() {
        return Constants.database_type.getDbType(databaseType.getValue()).driverClazz;
    }

    public String connectionUrl() {
        // TODO: 2018/1/26 为不同的数据库生成不同的connectionUrl
        return String.format(Constants.database_type.getDbType(databaseType.getValue()).connectStr, host.getValue(), port.getValue(), dbName.getValue(), encoding.getValue());
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public TreeItem<String> getRootItem() {
        initialize();
        if (rootItem == null)
            rootItem = new TreeItem<>(String.format("%s(%s:%s/%s)", savedName.getValue(), host.getValue(), port.getValue(), dbName.getValue()));
        return rootItem;
    }

    public void openConnection() {
        try {
            if (connection == null && rootItem.getChildren().size() == 0)
                createConnection();
            else return;
            for (String t : fetchTables()) {
                TreeItem<String> item = new TreeItem<>(t);
                rootItem.getChildren().add(item);
            }
            rootItem.setExpanded(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        rootItem.getChildren().clear();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection = null;
    }

    private void createConnection() throws SQLException, ClassNotFoundException {
        //加载驱动
        Class.forName(Constants.database_type.getDbType(databaseType.getValue()).driverClazz);
        //获得数据库连接
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + port.getValue() + "/" + dbName.getValue(), username.getValue(), password.getValue());
    }

    public void testConnection() {
        try {
            createConnection();
            showMessage("提示", "连接成功");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showMessage("提示", "连接失败，错误信息:\n" + e.getMessage() + "\n\n详情请查看控制台打印的错误堆栈");
        }
    }

    // TODO: 2018/1/24 测试各个数据库的连接方式
    public List<String> fetchTables() throws Exception {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs;
        if (databaseType.getValue().equals(Constants.database_type.SQLServer.name)) {
            String sql = "select name from sysobjects  where xtype='u' or xtype='v' ";
            rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                tables.add(rs.getString("name"));
            }
        } else if (databaseType.getValue().equals(Constants.database_type.Oracle.name)) {
            rs = md.getTables(null, username.getValue().toUpperCase(), null, new String[]{"TABLE", "VIEW"});
        } else {//postgresql
            // rs = md.fetchTables(null, config.getUsername().toUpperCase(), null, null);
            rs = md.getTables(null, "%", "%", new String[]{"TABLE", "VIEW"});
        }
        while (rs.next()) {
            tables.add(rs.getString(3));
        }
        return tables;
    }

    public void showCustomColumnConfigView() {
        if (selectedTable == null || selectedTable.getValue().isEmpty()) {
            showMessage("请选择一个表");
            return;
        }
        generateColumnMetadata();
        SelectTableColumnController columnController = showWindow("自定义列", "customize_columns_editor.fxml");
        columnController.setColumnList(columns);
    }

    public void generateColumnMetadata() {
        if (columns != null) return;
        DatabaseMetaData md = null;
        try {
            md = connection.getMetaData();
            ResultSet rs = md.getColumns(null, null, selectedTable.getValue(), null);
            columns = new ArrayList<>();
            while (rs.next()) {
                TableColumnMetaData columnMetaData = new TableColumnMetaData();
                String columnName = rs.getString("COLUMN_NAME");
                columnMetaData.setColumnName(columnName);
                columnMetaData.setJdbcType(rs.getString("TYPE_NAME"));
                columns.add(columnMetaData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Parent generateLayout() {
        initialize();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                savedName.generateLayout(),
                databaseType.generateLayout(),
                host.generateLayout(),
                port.generateLayout(),
                username.generateLayout(),
                password.generateLayout(),
                dbName.generateLayout(),
                encoding.generateLayout()
        );

        HBox hBox = new HBox();
        HBox spacer = new HBox();
        hBox.getStylesheets().add("style/jfoenix-components.css");
        hBox.getStylesheets().add("style/jfoenix-main-demo.css");
        hBox.getStylesheets().add("style/style.css");
        spacer.setPrefWidth(120);
        btn_test_connection.setMinWidth(120);
        btn_save_connection.setMinWidth(80);
        btn_save_connection.setStyle("    -fx-background-color: rgb(77, 102, 204);-fx-text-fill: WHITE;");
        btn_cancel.setMinWidth(80);
        hBox.setPadding(new Insets(16, 0, 0, 0));
        btn_save_connection.setOnMouseClicked(event -> {
            save();
            if (mainWindowController != null)
                mainWindowController.refreshConnectionsList();
        });
        btn_cancel.setOnMouseClicked(event -> dialog.close());
        btn_test_connection.setOnMouseClicked(event -> testConnection());
        hBox.getChildren().add(btn_test_connection);
        hBox.getChildren().add(spacer);
        hBox.getChildren().add(btn_save_connection);
        hBox.getChildren().add(btn_cancel);
        vBox.getChildren().add(hBox);
        vBox.setPadding(Constants.ui.DEFAULT_LAYER_INSETS);
        return vBox;
    }

    public void save() {
        Gson fxGson = FxGson.create();
        fxGson.serializeNulls();
        String s = null;
        try {
            s = fxGson.toJson(this);
            System.out.println("save database config:\n" + s);
            if (Files.exists(Paths.get(String.format("data/connection/%s.json", savedName.getValue())))) {
                FileUtil.writeStringToFile(String.format("data/connection/%s.json", savedName.getValue()), s);
                showMessage("保存成功（已覆盖同名文件）");
            } else {
                FileUtil.writeStringToFile(String.format("data/connection/%s.json", savedName.getValue()), s);
                showMessage("保存成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.close();
    }

    public void initialize() {
        for (Field field : DatabaseConfig.class.getFields()) {
            try {
                if (field.get(this) instanceof Config)
                    ((Config) field.get(this)).initialize();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        selectedTable.addListener((observable, oldValue, newValue) -> {
            this.columns = null;
        });
    }


}
