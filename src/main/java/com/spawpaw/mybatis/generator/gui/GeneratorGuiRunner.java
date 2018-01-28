package com.spawpaw.mybatis.generator.gui;

import com.spawpaw.mybatis.generator.gui.controller.BaseController;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 整个程序的入口
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class GeneratorGuiRunner extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GeneratorGuiRunner.primaryStage = primaryStage;
        BaseController.setPrimaryStage(primaryStage);
        primaryStage.setTitle(Constants.ui.MAIN_WINDOW_TITLE);
        primaryStage.setScene(new Scene(BaseController.loadView(this, "main.fxml", Locale.CHINA), 976, 720));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

}
