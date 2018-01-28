package com.spawpaw.mybatis.generator.gui.config;

import com.jfoenix.controls.JFXTextField;
import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TextFieldConfig extends Config<String> {
    protected transient JFXTextField textField;

    public TextFieldConfig(String name, String value) {
        super(name, new SimpleStringProperty(value));
    }


    @Override
    protected void initView() {
        textField = new JFXTextField();
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
