package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckBoxControl extends IControl<Boolean> {
    CheckBox checkBox = new CheckBox();

    @Override
    protected void initView() {
        layout.getChildren().addAll(checkBox);
    }

    @Override
    protected void bindProperties() {
        checkBox.selectedProperty().bindBidirectional(value);
        checkBox.textProperty().bindBidirectional(this.labelText);
    }
}
