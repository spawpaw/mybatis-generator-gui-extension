package com.spawpaw.mybatis.generator.gui.controller;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.spawpaw.mybatis.generator.gui.ProjectConfig;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class BaseController {
    public transient static String i18nPackage = "i18n.locale";
    public transient static Stage primaryStage;
    public transient static Map<BaseController, Stage> controllers = new HashMap<>();
    public transient static MainController mainWindowController;
    protected static transient ProjectConfig projectConfig;

    public static FXMLLoader getFxmlLoader(String fxmlFileName) {
        return new FXMLLoader(Thread.currentThread().getContextClassLoader().getResource("layout/" + fxmlFileName));

    }

    public static Parent loadView(String fxmlFileName, Locale locale) {
        try {
            FXMLLoader fxmlLoader = getFxmlLoader(fxmlFileName);
            fxmlLoader.setResources(ResourceBundle.getBundle(i18nPackage, locale));
            return fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void showMessage(String title, String content) {
        JFXAlert alert = new JFXAlert(primaryStage);
        VBox container = new VBox();
        container.setPadding(new Insets(12, 12, 12, 12));
        container.setMinWidth(400);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 20;-fx-font-weight: BOLD");
        Label contentLabel = new Label(content);
        contentLabel.setStyle("-fx-font-size: 14;");
        contentLabel.setMinHeight(120);


        JFXButton button = new JFXButton("确认");
        HBox hBox = new HBox();
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        hBox.getChildren().add(button);
        button.setStyle(" -fx-padding: 0.7em 0.57em; -fx-font-size: 14px;  -fx-pref-width: 120;");
        button.setOnMouseClicked(event -> alert.close());

        container.getChildren().add(titleLabel);
        container.getChildren().add(contentLabel);
        container.getChildren().add(hBox);

        alert.setContent(container);
        alert.show();
    }

    public static void showMessage(String content) {
        showMessage("提示", content);
    }

    public static void setPrimaryStage(Stage primaryStage) {
        BaseController.primaryStage = primaryStage;
    }

    public <T extends BaseController> T showWindow(String title, String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = getFxmlLoader(fxmlFileName);
            Parent root = fxmlLoader.load();
            T controller = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            if (primaryStage == null) primaryStage = stage;
            else stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            controllers.put(controller, stage);
            return controller;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    protected void closeMyself() {
        controllers.get(this).close();
        controllers.remove(this);
    }

    /**
     * 验证项目配置是否合法
     */

    protected boolean isProjectConfigValidated() {
        String msg = "";
        //是否选中数据库
        if (projectConfig.selectedDatabaseConfig == null) {
            msg += "\n没有选中数据库";
        } else if (projectConfig.selectedDatabaseConfig.selectedTable == null) {  //是否选中表
            msg += "\n没有选中表";
        } else {
            //是否填写 Mapper目录、包名，接口目录、包名，Mapper名称
            if (projectConfig.mapperDir.getValue().isEmpty())
                msg += "\n没有填写Mapper目录";
            if (projectConfig.mapperPackage.getValue().isEmpty())
                msg += "\n没有填写Mapper包名";
            if (projectConfig.daoObjName.getValue().isEmpty())
                msg += "\n没有填写Mapper名称";
            if (projectConfig.daoDir.getValue().isEmpty())
                msg += "\n没有填写接口目录";
            if (projectConfig.daoPackage.getValue().isEmpty())
                msg += "\n没有填写接口包名";
            // 是否填写Entity目录、包名、名称、Example名称
            if (projectConfig.entityDir.getValue().isEmpty())
                msg += "\n没有填写Entity目录";
            if (projectConfig.entityPackage.getValue().isEmpty())
                msg += "\n没有填写Entity包名";
            if (projectConfig.entityObjName.getValue().isEmpty())
                msg += "\n没有填写Entity名称";
        }
        if (!msg.isEmpty()) {
            showMessage("配置存在如下问题:\n\n" + msg);
        }
        return msg.isEmpty();
    }
}
