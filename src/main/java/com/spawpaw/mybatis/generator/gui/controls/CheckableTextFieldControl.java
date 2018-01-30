package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckableTextFieldControl extends TextFieldControl {
    CheckBox checkBox = new CheckBox();

    @Override
    public void initView() {
        layout.getChildren().addAll(checkBox);
        super.initView();
    }

    @Override
    protected void bindProperties() {
        super.bindProperties();
    }

    @Override
    public boolean isValid() {
        checkBox.setSelected(this.value.toString().isEmpty());
        return checkBox.selectedProperty().getValue() && super.isValid();
    }
}
