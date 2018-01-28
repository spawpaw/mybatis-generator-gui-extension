package com.spawpaw.mybatis.generator.gui.config;

import com.jfoenix.controls.JFXTextArea;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created By spawpaw@hotmail.com  2018-01-28
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TextAreaConfig extends Config<String> {
    protected transient JFXTextArea textField;

    public TextAreaConfig(String name, String value) {
        super(name, new SimpleStringProperty(value));
    }


    @Override
    protected void initView() {
        textField = new JFXTextArea();
        textField.setMinWidth(Constants.ui.MIN_TEXT_FIELD_WIDTH);
        textField.setLabelFloat(true);
        layout.getChildren().add(textField);
    }

    @Override
    protected void bindProperties() {
        this.textField.textProperty().bindBidirectional(valueProperty());
        this.textField.promptTextProperty().bindBidirectional(nameProperty());
    }
}
