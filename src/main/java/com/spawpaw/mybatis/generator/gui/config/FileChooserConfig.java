package com.spawpaw.mybatis.generator.gui.config;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.spawpaw.mybatis.generator.gui.GeneratorGuiRunner;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 文件夹选择器，提供一个按钮和一个TextField，点击按钮可以选择文件夹，选择的值将呈现在TextField中
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class FileChooserConfig extends Config<String> {
    private transient JFXTextField textField;
    private transient JFXButton btnSelectDir;

    public FileChooserConfig(String name, String value) {
        super(name, new SimpleStringProperty(value));
    }


    @Override
    protected void initView() {
        textField = new JFXTextField();
        textField.setLabelFloat(true);
        textField.setMinWidth(Constants.ui.MIN_TEXT_FIELD_WIDTH);
        btnSelectDir = new JFXButton("选择目录");
        layout.getChildren().add(textField);
        layout.getChildren().add(btnSelectDir);
    }

    @Override
    protected void bindProperties() {
        textField.textProperty().bindBidirectional(valueProperty());
        textField.promptTextProperty().bindBidirectional(nameProperty());

        btnSelectDir.setOnMouseClicked((event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedFolder = directoryChooser.showDialog(GeneratorGuiRunner.primaryStage);
            if (selectedFolder != null) {
                setValue(selectedFolder.getAbsolutePath());
            }
        }));
    }
}
