package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckableTextAreaControl extends TextAreaControl {
    CheckBox checkBox;

    @Override
    public void initView() {
        checkBox = new CheckBox();
        layout.getChildren().addAll(checkBox);
        super.initView();
        checkBox.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        super.bindProperties();
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                value.setValue("");
            textArea.setDisable(!newValue);
        });
    }

    @Override
    public boolean isValid() {
        checkBox.setSelected(this.value.toString().isEmpty());
        return checkBox.selectedProperty().getValue() && super.isValid();
    }
}
