package com.spawpaw.mybatis.generator.gui.controller;

import com.spawpaw.mybatis.generator.gui.DatabaseConfig;
import com.spawpaw.mybatis.generator.gui.ProjectConfig;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class BaseController extends Application {

    //stages
    public transient static Stage primaryStage;
    public transient static MainController primaryStageController;

    public transient static Stage databaseEditorStage;
    public transient static DatabaseConfigEditorController databaseEditorStageController;

    public transient static Stage tableColumnEditorStage;
    public transient static TableColumnEditorController tableColumnEditorStageController;

    public transient static Stage generationProgressStage;
    public transient static GenerationProgressController generationProgressController;

    public transient static Stage aboutStage;

    public static transient ProjectConfig selectedProjectConfig;
    public static transient DatabaseConfig selectedDatabaseConfig = new DatabaseConfig();


    public static void showMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Constants.getI18nStr("msg.title"));
        alert.setHeaderText(Constants.getI18nStr(title));
        alert.setContentText(Constants.getI18nStr(content));
        alert.initOwner(primaryStage);
        alert.show();
    }

    public static void showMessage(String content) {
        showMessage("msg.msg", content);
    }

    public static void launchWindow(String[] args) {
        launch(args);
    }

    protected static FXMLLoader getFxmlLoader(String fxmlFileName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Thread.currentThread().getContextClassLoader().getResource("layout/" + fxmlFileName));
        fxmlLoader.setResources(Constants.getResourcesBundle());
        return fxmlLoader;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        BaseController.primaryStage = primaryStage;
        initializeStages();
        primaryStage.setTitle(Constants.ui.MAIN_WINDOW_TITLE);
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    private void initializeStages() {
        try {
            //get fxml loader
            FXMLLoader primaryStageLoader = getFxmlLoader("main.fxml");
            FXMLLoader customize_columns_editor_loader = getFxmlLoader("table_columns_editor.fxml");
            FXMLLoader database_config_editor_loader = getFxmlLoader("database_config_editor.fxml");
            FXMLLoader generation_progress_loader = getFxmlLoader("generation_progress.fxml");
            FXMLLoader about_loader = getFxmlLoader("About.fxml");

            //load fxml
            Parent primary_stage_scene = primaryStageLoader.load();
            Parent customize_columns_editor_scene = customize_columns_editor_loader.load();
            Parent database_config_editor_scene = database_config_editor_loader.load();
            Parent generation_progress_scene = generation_progress_loader.load();
            Parent about = about_loader.load();

            //set controller
            primaryStageController = primaryStageLoader.getController();
            databaseEditorStageController = database_config_editor_loader.getController();
            tableColumnEditorStageController = customize_columns_editor_loader.getController();
            generationProgressController = generation_progress_loader.getController();

            //set scene
            primaryStage.setScene(new Scene(primary_stage_scene));

            databaseEditorStage = new Stage();
            databaseEditorStage.setScene(new Scene(database_config_editor_scene));
            databaseEditorStage.initOwner(primaryStage);
            databaseEditorStage.initModality(Modality.APPLICATION_MODAL);

            tableColumnEditorStage = new Stage();
            tableColumnEditorStage.setScene(new Scene(customize_columns_editor_scene));
            tableColumnEditorStage.initOwner(primaryStage);
            tableColumnEditorStage.initModality(Modality.APPLICATION_MODAL);

            generationProgressStage = new Stage();
            generationProgressStage.setScene(new Scene(generation_progress_scene));
            generationProgressStage.initOwner(primaryStage);
            generationProgressStage.initModality(Modality.APPLICATION_MODAL);

            aboutStage = new Stage();
            aboutStage.setScene(new Scene(about));
            aboutStage.initOwner(primaryStage);
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setMinHeight(349);
            aboutStage.setMinWidth(515);
            aboutStage.setMaxHeight(349);
            aboutStage.setMaxWidth(515);
            aboutStage.setMaximized(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
