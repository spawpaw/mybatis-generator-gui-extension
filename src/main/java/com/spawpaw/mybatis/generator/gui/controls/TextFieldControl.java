package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TextFieldControl extends IControl<String> {
    protected TextField textField;
    protected Label label;


    @Override
    public void initView() {
        textField = new TextField();
        label = new Label();
        layout.getChildren().addAll(label, textField);
        textField.setTooltip(tooltip);
        label.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        label.setMinWidth(MIN_WIDTH_LEFT);
        textField.setMinWidth(MIN_WIDTH_RIGHT);
        this.textField.promptTextProperty().bindBidirectional(this.promptTextProperty);
        this.textField.textProperty().bindBidirectional(value);
        this.label.textProperty().bindBidirectional(this.labelTextProperty);
    }
}
