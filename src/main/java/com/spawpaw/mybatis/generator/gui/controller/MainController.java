package com.spawpaw.mybatis.generator.gui.controller;

import com.google.gson.Gson;
import com.spawpaw.mybatis.generator.gui.DatabaseConfig;
import com.spawpaw.mybatis.generator.gui.ProjectConfig;
import com.spawpaw.mybatis.generator.gui.annotations.AdvancedConfig;
import com.spawpaw.mybatis.generator.gui.annotations.Config;
import com.spawpaw.mybatis.generator.gui.annotations.ConfigWrapper;
import com.spawpaw.mybatis.generator.gui.annotations.ExportToTab;
import com.spawpaw.mybatis.generator.gui.controls.ControlsFactory;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import javafx.beans.property.Property;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class MainController extends BaseController implements Initializable {
    public VBox main_container;
    public ComboBox<String> cb_load_saved_project_config;
    public TreeView<String> tv_connections;
    public TreeItem<String> rootItem;
    public ChoiceBox<String> cb_select_language;
    public ToggleButton btn_show_advanced_settings;
    public Label btn_about;

    Map<TreeItem<String>, DatabaseConfig> databaseConfigHashMap;
    String projectConfigName = "";
    private Map<String, List<ConfigWrapper>> configs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshProjectConfigList();
        refreshDatabaseConfigList();
        setSelectedProjectConfig(new ProjectConfig(), "untitled");

        cb_select_language.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("中文")) {
                Constants.setLocale(Locale.CHINA);
            } else {
                Constants.setLocale(Locale.ENGLISH);
            }
        });
        cb_select_language.setTooltip(new Tooltip("you can change the launch language in `GeneratorGuiRunner.class`"));
        btn_about.setOnMouseClicked(event -> aboutStage.showAndWait());
    }

    public void setSelectedProjectConfig(ProjectConfig projectConfig, String projectConfigName) {
        selectedProjectConfig = projectConfig;
        this.projectConfigName = projectConfigName.replaceAll(".json$", "");

        projectConfig.initialize();
        generateTabPane();
        btn_show_advanced_settings.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btn_show_advanced_settings.setText(newValue ? Constants.getI18nStr("ui.simpleMode.off") : Constants.getI18nStr("ui.simpleMode.on"));
        });
    }

    /**
     * 生成TabPane
     */
    private void generateTabPane() {
        loadExposedConfigs();

        TabPane tabPane = new TabPane();
        for (String key : configs.keySet()) {
            Tab tab = new Tab(Constants.getI18nStr(key));
            VBox vBox = new VBox();
            List<ConfigWrapper> configs = this.configs.get(key);
            configs.sort(Comparator.comparingInt(a -> a.index));
            if (key.equalsIgnoreCase(Constants.tabs.SHORTCUT)) {
                vBox.getChildren().add(new Label(Constants.getI18nStr("msg.thisIsShortcutTab")));
            }
            for (ConfigWrapper cw : configs)
                vBox.getChildren().add(cw.layout);

            vBox.setPadding(Constants.ui.DEFAULT_LAYOUT_INSETS);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);//禁止水平滚动
            tab.setContent(scrollPane);
            tab.setClosable(false);
            tabPane.getTabs().add(tab);
        }
        tabPane.setPrefHeight(10000);
        tabPane.setPrefWidth(10000);
        //load tab pane
        main_container.getChildren().clear();
        main_container.getChildren().add(tabPane);
    }

    /**
     * 将所有的配置导出到GUI中
     */
    private void loadExposedConfigs() {
        configs = new TreeMap<>(Comparator.naturalOrder());
        ExportToTab[] defaultTargetLayer = ProjectConfig.class.getAnnotationsByType(ExportToTab.class);

        for (Field field : ProjectConfig.class.getFields()) {
            try {
                Config config = field.getAnnotation(Config.class);
                ExportToTab[] targetTabs = field.getAnnotationsByType(ExportToTab.class);
                if (targetTabs.length == 0)
                    targetTabs = defaultTargetLayer;
                if (targetTabs == null || targetTabs.length == 0) {
                    continue;
                } else {
                    defaultTargetLayer = targetTabs;
                }
                if (field.get(selectedProjectConfig) instanceof Property) {
                    for (ExportToTab l : targetTabs) {
                        HBox layout = ControlsFactory.getLayout(config, (Property) field.get(selectedProjectConfig));
                        if (field.getAnnotation(AdvancedConfig.class) != null) {
                            layout.visibleProperty().bindBidirectional(btn_show_advanced_settings.selectedProperty());
                            layout.managedProperty().bindBidirectional(btn_show_advanced_settings.selectedProperty());
                        }
                        if (!configs.containsKey(l.tabName()))
                            configs.put(l.tabName(), new ArrayList<>());
                        configs.get(l.tabName()).add(new ConfigWrapper(layout, l.index()));
//                        System.out.println(String.format("将%-36s加入tab: %s", field.getName(), l.tabName()));
                    }
                } else {
                    System.out.printf("配置项%-36s的类型不正确，配置项的类型必须继承自javafx.beans.property.Property\n", field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

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
            if (newValue != null && !newValue.isEmpty())
                setSelectedProjectConfig(fxGson.fromJson(FileUtil.readFileAsStr(new File(Constants.CONFIG_SAVE_PATH + newValue)), ProjectConfig.class), newValue);
        });
    }

    public void refreshDatabaseConfigList() {
        try {
            databaseConfigHashMap = new HashMap<>();
            rootItem = new TreeItem<>();
            rootItem.setExpanded(true);
            Gson fxGson = FxGson.create();
            File connectionDir = new File(Constants.CONNECTION_SAVE_PATH);


            //load connection list
            if (connectionDir.listFiles() != null)
                for (File file : connectionDir.listFiles()) {
                    DatabaseConfig dbConfig = fxGson.fromJson(FileUtil.readFileAsStr(file), DatabaseConfig.class);
                    TreeItem<String> dbConfigRootItem = dbConfig.getRootTreeItem();
                    rootItem.getChildren().add(dbConfigRootItem);
                    databaseConfigHashMap.put(dbConfigRootItem, dbConfig);
                }
            //add on selection change listener
            tv_connections.setOnMouseClicked(event -> {
                TreeItem<String> selectedItem = tv_connections.getSelectionModel().getSelectedItem();
                if (selectedItem == null)
                    return;
                if (databaseConfigHashMap.get(selectedItem) != null) {
                    if (event.getClickCount() == 2) {
                        try {
                            databaseConfigHashMap.get(selectedItem).connect();
                        } catch (SQLException e) {
                            showMessage(Constants.getI18nStr("ui.connectFailureMsg") + "\n" + e.getMessage());
                            e.printStackTrace();
                        }
                        selectedDatabaseConfig = databaseConfigHashMap.get(selectedItem);
                        selectedProjectConfig.selectedTable.setValue("");
                    }
                } else {
                    selectedDatabaseConfig = databaseConfigHashMap.get(tv_connections.getSelectionModel().getSelectedItem().getParent());
                    selectedProjectConfig.selectedTable.setValue(tv_connections.getSelectionModel().getSelectedItem().getValue());
                }
            });

            //add context menu
            ContextMenu contextMenu = new ContextMenu();
            MenuItem miCloseConnection = new MenuItem(Constants.getI18nStr("ui.closeConnection"));
            miCloseConnection.setOnAction(event -> {
                TreeItem<String> selectedItem = tv_connections.getSelectionModel().getSelectedItem();
                if (databaseConfigHashMap.get(selectedItem) == null)
                    selectedItem = selectedItem.getParent();
                databaseConfigHashMap.get(selectedItem).close();
            });
            MenuItem miDeleteConnection = new MenuItem(Constants.getI18nStr("ui.deleteConnection"));
            miDeleteConnection.setOnAction(event -> {
                TreeItem<String> selectedItem = tv_connections.getSelectionModel().getSelectedItem();
                if (tv_connections == null) return;
                if (databaseConfigHashMap.get(selectedItem) == null)
                    selectedItem = selectedItem.getParent();
                System.out.println(new File(Constants.CONNECTION_SAVE_PATH + databaseConfigHashMap.get(selectedItem).savedName.getValue() + ".json").delete());
                refreshDatabaseConfigList();
            });
            MenuItem miEditConnection = new MenuItem(Constants.getI18nStr("ui.editConnection"));
            miEditConnection.setOnAction(event -> {
                TreeItem<String> selectedItem = tv_connections.getSelectionModel().getSelectedItem();
                if (tv_connections == null) return;
                if (databaseConfigHashMap.get(selectedItem) == null)
                    selectedItem = selectedItem.getParent();
                showEditDatabaseConfigDialog(databaseConfigHashMap.get(selectedItem));
            });
            contextMenu.getItems().addAll(miCloseConnection, miDeleteConnection, miEditConnection);
            tv_connections.setContextMenu(contextMenu);
            tv_connections.setRoot(rootItem);
            tv_connections.setShowRoot(false);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void saveProjectConfig() {
        Gson gson = new FxGsonBuilder().create();
        String json = gson.toJson(selectedProjectConfig);
        try {
            TextInputDialog textInputDialog = new TextInputDialog(projectConfigName);
            textInputDialog.setContentText("Please input file name");
            textInputDialog.setHeaderText("");
            textInputDialog.setTitle("message");
            textInputDialog.setGraphic(null);
            Optional<String> opt = textInputDialog.showAndWait();
            if (opt.isPresent() && !opt.get().isEmpty()) {
                FileUtil.writeStringToFile(Constants.CONFIG_SAVE_PATH + opt.get() + ".json", json);
                projectConfigName = opt.get();
                refreshProjectConfigList();
                cb_load_saved_project_config.setValue(projectConfigName + ".json");
                showMessage("msg.error.SaveSuccess");
            }
        } catch (IOException e) {
            System.out.println("msg.error.SaveFailure");
            e.printStackTrace();
        }
    }

    public void showEditDatabaseConfigDialog(DatabaseConfig databaseConfig) {
        databaseEditorStageController.setDatabaseConfig(databaseConfig);
        databaseEditorStage.showAndWait();
    }

    public void showCreateDatabaseConfigDialog() {
        showEditDatabaseConfigDialog(new DatabaseConfig());
    }

    public void showEditTableColumnsDialog() {

        String msg = "";
        if (selectedDatabaseConfig == null) {
            msg += "\n" + Constants.getI18nStr("msg.error.NoDatabaseSelected");
        } else if (selectedProjectConfig.selectedTable == null || selectedProjectConfig.selectedTable.getValue().isEmpty()) {  //是否选中表
            msg += "\n" + Constants.getI18nStr("msg.error.NoTableSelected");
        }
        if (!msg.isEmpty()) {
            showMessage(Constants.getI18nStr("msg.error.configHasProblems"), msg);
            return;
        }
        tableColumnEditorStageController.setColumns(selectedDatabaseConfig.tableConfigs.get(selectedProjectConfig.selectedTable.getValue()));
        tableColumnEditorStage.showAndWait();
    }

    public void onGenerateCodeForCurrentTableClicked() {
        generationProgressStage.show();
        generationProgressController.beginGenerate(false);
    }

    public void onGenerateCodeForAllTableClicked() {
        generationProgressStage.show();
        generationProgressController.beginGenerate(true);
    }
}
