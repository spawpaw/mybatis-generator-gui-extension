package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckBoxControl extends IControl<Boolean> {
    CheckBox checkBox;

    @Override
    protected void initView() {
        checkBox = new CheckBox();
        layout.getChildren().addAll(checkBox);
        checkBox.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        checkBox.setTooltip(tooltip);
        checkBox.selectedProperty().bindBidirectional(value);
        checkBox.textProperty().bindBidirectional(this.labelTextProperty);
    }
}
