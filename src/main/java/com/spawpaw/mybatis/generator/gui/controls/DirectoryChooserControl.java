package com.spawpaw.mybatis.generator.gui.controls;

import com.spawpaw.mybatis.generator.gui.controller.BaseController;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class DirectoryChooserControl extends IControl<String> {
    Label label = new Label();
    TextField textField = new TextField();
    Button button = new Button();

    @Override
    protected void initView() {
        layout.getChildren().addAll(label, textField, button);
        label.setTooltip(tooltip);
        textField.setTooltip(tooltip);
        button.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        label.setMinWidth(MIN_WIDTH_LEFT);
        textField.textProperty().bindBidirectional(value);
        label.textProperty().bindBidirectional(this.labelTextProperty);

        button.setText(Constants.getI18nStr("controls.chooseDir"));
        button.setOnMouseClicked((event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedFolder = directoryChooser.showDialog(BaseController.primaryStage);
            if (selectedFolder != null) {
                value.setValue(selectedFolder.getAbsolutePath());
            }
        }));
    }
}
