package com.spawpaw.mybatis.generator.gui.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class RootController {
    public transient static Stage primaryStage;


    public transient static Map<RootController, Stage> controllers = new HashMap<>();

    //    public transient static MainController mainWindowController;
    public static URL getFxmlURL(String fxmlFileName) {
        return Thread.currentThread().getContextClassLoader().getResource("layout/" + fxmlFileName);
    }

    public Parent loadFxml(String fxmlFileName) {
        try {
            Parent parent = FXMLLoader.load(getFxmlURL(fxmlFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
