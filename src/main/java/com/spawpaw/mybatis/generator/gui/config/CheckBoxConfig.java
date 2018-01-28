package com.spawpaw.mybatis.generator.gui.config;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckBoxConfig extends Config<Boolean> {
    protected transient JFXCheckBox checkBox;

    public CheckBoxConfig(String name, Boolean value) {
        super(name, new SimpleBooleanProperty(value));
    }

    @Override
    protected void initView() {
        checkBox = new JFXCheckBox();
        layout.getChildren().add(checkBox);
    }

    @Override
    protected void bindProperties() {
        checkBox.textProperty().bindBidirectional(nameProperty());
        checkBox.selectedProperty().bindBidirectional(valueProperty());
    }
}
