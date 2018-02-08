package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckableTextFieldControl extends TextFieldControl {
    CheckBox checkBox;

    @Override
    public void initView() {
        checkBox = new CheckBox();
        layout.getChildren().addAll(checkBox);
        super.initView();
        label.setTooltip(tooltip);
        textField.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        super.bindProperties();
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                value.setValue("");
            textField.setDisable(!newValue);
        });
        textField.setDisable(!checkBox.selectedProperty().getValue());
    }

    @Override
    public boolean isValid() {
        checkBox.setSelected(this.value.toString().isEmpty());
        return checkBox.selectedProperty().getValue() && super.isValid();
    }


}
