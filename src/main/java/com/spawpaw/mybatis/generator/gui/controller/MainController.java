package com.spawpaw.mybatis.generator.gui.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.*;
import com.spawpaw.mybatis.generator.gui.ProjectConfig;
import com.spawpaw.mybatis.generator.gui.annotations.AdvancedConfig;
import com.spawpaw.mybatis.generator.gui.annotations.ExportToTab;
import com.spawpaw.mybatis.generator.gui.config.Config;
import com.spawpaw.mybatis.generator.gui.config.ConfigWrapper;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import com.spawpaw.mybatis.generator.gui.util.MBGRunner;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class MainController extends BaseController implements Initializable {
    public transient JFXButton btn_new_connection;
    public transient JFXButton btn_save_config;
    public transient JFXComboBox<String> cb_load_saved_project_config;
    public transient VBox main_container;
    public transient JFXToggleButton btn_show_advanced_settings;
    @FXML
    transient JFXTreeView<String> lv_connections = new JFXTreeView<>();
    transient TreeItem<String> rootItem;
    Map<TreeItem<String>, DatabaseConfig> databaseConfigHashMap = new HashMap<>();
    private Map<String, List<ConfigWrapper>> configs;
    private BooleanProperty showAdvancedOptions = new SimpleBooleanProperty(false);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainWindowController = this;

        //load connection list
        refreshConnectionsList();
        //load project config list
        refreshProjectConfigList();
        //initialize project config
        setProjectConfig(new ProjectConfig());


        //bind listeners
        btn_new_connection.setOnMouseClicked((event -> showDatabaseConfigEditorDialog("新建连接", new DatabaseConfig())));
        btn_show_advanced_settings.selectedProperty().addListener((observable, oldValue, newValue) -> showAdvancedOptions.setValue(!newValue));
        btn_save_config.setOnMouseClicked(event -> showMessage("提示", projectConfig.save(this)));

    }

    public void runMybatisGenerator() {
        if (isProjectConfigValidated())
            new MBGRunner(projectConfig).generate();
    }

    public void refreshProjectConfigList() {
        File projectConfigDir = new File(Constants.CONFIG_SAVE_PATH);
        Gson fxGson = FxGson.create();
        cb_load_saved_project_config.getItems().clear();
        if (projectConfigDir.listFiles() != null)
            for (File file : projectConfigDir.listFiles()) {
                cb_load_saved_project_config.getItems().add(file.getName());
            }
        cb_load_saved_project_config.valueProperty().addListener((observable, oldValue, newValue) -> {
            setProjectConfig(fxGson.fromJson(FileUtil.readFileByStr(new File(Constants.CONFIG_SAVE_PATH + newValue)), ProjectConfig.class));
        });
    }

    public void refreshConnectionsList() {
        try {
            rootItem = new TreeItem<>();
            rootItem.setExpanded(true);
            Gson fxGson = FxGson.create();
            File connectionDir = new File(Constants.CONNECTION_SAVE_PATH);


            //load connection list
            if (connectionDir.listFiles() != null)
                for (File file : connectionDir.listFiles()) {
                    DatabaseConfig dbConfig = fxGson.fromJson(FileUtil.readFileByStr(file), DatabaseConfig.class);
                    TreeItem<String> dbConfigRootItem = dbConfig.getRootItem();
                    rootItem.getChildren().add(dbConfigRootItem);
                    databaseConfigHashMap.put(dbConfigRootItem, dbConfig);
                }
            //add on selection change listener
            lv_connections.setOnMouseClicked(event -> {
                TreeItem<String> selectedItem = lv_connections.getSelectionModel().getSelectedItem();
                if (selectedItem == null)
                    return;
                if (databaseConfigHashMap.get(selectedItem) != null) {
                    if (event.getClickCount() == 2) {
                        databaseConfigHashMap.get(selectedItem).openConnection();
                        projectConfig.selectedDatabaseConfig = databaseConfigHashMap.get(selectedItem);
                        projectConfig.selectedDatabaseConfig.selectedTable.setValue("");
                    }
                } else {
                    projectConfig.selectedDatabaseConfig = databaseConfigHashMap.get(lv_connections.getSelectionModel().getSelectedItem().getParent());
                    projectConfig.selectedDatabaseConfig.selectedTable.setValue(lv_connections.getSelectionModel().getSelectedItem().getValue());
                    projectConfig.updateClassName();
                }
            });

            //add context menu
            ContextMenu contextMenu = new ContextMenu();
            MenuItem miCloseConnection = new MenuItem("关闭连接");
            miCloseConnection.setOnAction(event -> {
                TreeItem<String> selectedItem = lv_connections.getSelectionModel().getSelectedItem();
                if (databaseConfigHashMap.get(selectedItem) == null)
                    selectedItem = selectedItem.getParent();
                databaseConfigHashMap.get(selectedItem).closeConnection();
            });
            MenuItem miDeleteConnection = new MenuItem("删除连接");
            miDeleteConnection.setOnAction(event -> {
                TreeItem<String> selectedItem = lv_connections.getSelectionModel().getSelectedItem();
                if (lv_connections == null) return;
                if (databaseConfigHashMap.get(selectedItem) == null)
                    selectedItem = selectedItem.getParent();
                System.out.println(new File(Constants.CONNECTION_SAVE_PATH + databaseConfigHashMap.get(selectedItem).savedName.getValue() + ".json").delete());
                refreshConnectionsList();
            });
            MenuItem miEditConnection = new MenuItem("编辑连接");
            miEditConnection.setOnAction(event -> {
                TreeItem<String> selectedItem = lv_connections.getSelectionModel().getSelectedItem();
                if (lv_connections == null) return;
                if (databaseConfigHashMap.get(selectedItem) == null)
                    selectedItem = selectedItem.getParent();
                showDatabaseConfigEditorDialog("编辑连接", databaseConfigHashMap.get(selectedItem));
            });
            contextMenu.getItems().addAll(miCloseConnection, miDeleteConnection, miEditConnection);
            lv_connections.setContextMenu(contextMenu);
            lv_connections.setRoot(rootItem);
            lv_connections.setShowRoot(false);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void bindIsShowAdvancedOption() {
        try {
            for (Field field : projectConfig.getClass().getFields())
                if (field.isAnnotationPresent(AdvancedConfig.class)) {
                    if (field.get(projectConfig) instanceof Config)
                        ((Config) field.get(projectConfig)).bindVisibleProperty(showAdvancedOptions);
                    if (field.get(projectConfig) instanceof Node) {
                        ((Node) field.get(projectConfig)).visibleProperty().bindBidirectional(showAdvancedOptions);
                        ((Node) field.get(projectConfig)).managedProperty().bindBidirectional(showAdvancedOptions);
                    }
                }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private void generateTabPane() {
        loadTabs();

        JFXTabPane tabPane = new JFXTabPane();
        for (String key : configs.keySet()) {
            Tab tab = new Tab(key);
            VBox vBox = new VBox();
            List<ConfigWrapper> configs = this.configs.get(key);
            configs.sort(Comparator.comparingInt(a -> a.index));
            for (ConfigWrapper cw : configs)
                vBox.getChildren().add(cw.config.generateLayout());

            vBox.setPadding(Constants.ui.DEFAULT_LAYER_INSETS);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);//禁止水平滚动
            tab.setContent(scrollPane);
            tabPane.getTabs().add(tab);
        }
        tabPane.setPrefHeight(10000);
        tabPane.setPrefWidth(10000);
        //load tab pane
        main_container.getChildren().clear();
        main_container.getChildren().add(tabPane);
    }

    private void loadTabs() {
        configs = new HashMap<>();
        ExportToTab[] defaultTargetLayer = ProjectConfig.class.getAnnotationsByType(ExportToTab.class);

        for (Field field : ProjectConfig.class.getFields()) {
            try {
                ExportToTab[] targetLayers = field.getAnnotationsByType(ExportToTab.class);
                if (targetLayers == null || targetLayers.length == 0)
                    targetLayers = defaultTargetLayer;
                if (targetLayers == null) {
                    continue;
                } else {
                    defaultTargetLayer = targetLayers;
                }
                if (field.get(projectConfig) instanceof Config) {
                    Config config = (Config) field.get(projectConfig);
                    for (ExportToTab l : targetLayers) {
                        if (!configs.containsKey(l.layer().title))
                            configs.put(l.layer().title, new ArrayList<>());
                        configs.get(l.layer().title).add(new ConfigWrapper(config, l.index()));
                        System.out.println(String.format("将%-36s加入tab: %s", field.getName(), l.layer().title));
                    }
                } else {
                    System.out.println("发现非配置项：" + field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
        projectConfig.selectedDatabaseConfig = new DatabaseConfig();
        projectConfig.initialize();
        bindIsShowAdvancedOption();
        generateTabPane();
    }

    private void showDatabaseConfigEditorDialog(String title, DatabaseConfig databaseConfig) {
        Stage dialog = new Stage(StageStyle.DECORATED);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        dialog.titleProperty().setValue(title);
        databaseConfig.setDialog(dialog);
        Scene scene = new Scene(databaseConfig.generateLayout());
        dialog.setScene(scene);
        dialog.show();
    }


    public void openCustomColumnDialog(MouseEvent event) {
        projectConfig.selectedDatabaseConfig.showCustomColumnConfigView();
    }

}
