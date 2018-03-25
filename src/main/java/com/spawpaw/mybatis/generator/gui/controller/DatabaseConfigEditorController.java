package com.spawpaw.mybatis.generator.gui.controller;

import com.spawpaw.mybatis.generator.gui.DatabaseConfig;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created By spawpaw@hotmail.com  2018-01-31
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class DatabaseConfigEditorController extends BaseController implements Initializable {
    public VBox main_container;
    public Label lb_msg;
    DatabaseConfig databaseConfig;

    public void setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        main_container.getChildren().clear();
        main_container.getChildren().addAll(databaseConfig.getLayout());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onTestConnectionClicked(MouseEvent event) {
        try {
            databaseConfig.test();
            lb_msg.setText(Constants.getI18nStr("ui.connectSuccess"));
        } catch (SQLException e) {
            lb_msg.setText("");
            showMessage(String.format("%s:\n%s", Constants.getI18nStr("ui.connectFailureMsg"), e.getMessage()));
            e.printStackTrace();
        }
    }

    public void onCancelClicked(MouseEvent event) {
        databaseEditorStage.close();
    }

    public void onSaveClicked(MouseEvent event) {
        // TODO: 2018/3/17 检查数据库配置是否有效（例如是否填写了数据库名称）
        try {
            FileUtil.writeJsonObjToFile(Constants.CONNECTION_SAVE_PATH + databaseConfig.savedName.getValue() + ".json", databaseConfig);
            primaryStageController.refreshDatabaseConfigList();
            databaseEditorStage.close();
            showMessage("msg.error.SaveSuccess");
        } catch (IOException e) {
            showMessage(Constants.getI18nStr("msg.error.SaveFailure") + "\n" + e.getMessage());
            e.printStackTrace();
        }

    }
}
