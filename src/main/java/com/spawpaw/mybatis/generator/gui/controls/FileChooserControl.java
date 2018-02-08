package com.spawpaw.mybatis.generator.gui.controls;

import com.spawpaw.mybatis.generator.gui.controller.BaseController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created By spawpaw@hotmail.com  2018-02-01
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class FileChooserControl extends IControl<String> {
    Label label = new Label();
    TextField textField = new TextField();
    Button button = new Button();

    @Override
    protected void initView() {
        layout.getChildren().addAll(textField, button);
        label.setTooltip(tooltip);
        textField.setTooltip(tooltip);
        button.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        label.setMinWidth(MIN_WIDTH_LEFT);
        textField.textProperty().bindBidirectional(value);
        label.textProperty().bindBidirectional(this.labelTextProperty);
        button.setOnMouseClicked((event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(BaseController.primaryStage);
            if (selectedFile != null) {
                value.setValue(selectedFile.getAbsolutePath());
            }
        }));
    }
}
