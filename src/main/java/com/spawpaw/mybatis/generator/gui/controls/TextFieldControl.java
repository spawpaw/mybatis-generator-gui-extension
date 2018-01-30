package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TextFieldControl extends IControl<String> {
    private TextField textField = new TextField();
    private Label label = new Label();


    @Override
    public void initView() {
        layout.getChildren().addAll(label, textField);
    }

    @Override
    protected void bindProperties() {
        this.textField.promptTextProperty().bindBidirectional(this.promptText);
        this.textField.textProperty().bindBidirectional(value);
        this.label.textProperty().bindBidirectional(this.labelText);
    }
}
