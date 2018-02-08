package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckableTextFieldControl extends IControl<String> {
    protected CheckBox checkBox = new CheckBox();
    protected TextField textField = new TextField();

    @Override
    public void initView() {
        layout.getChildren().addAll(checkBox, textField);
        checkBox.setTooltip(tooltip);
        textField.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        this.textField.promptTextProperty().bindBidirectional(this.promptTextProperty);
        this.textField.textProperty().bindBidirectional(this.value);

        this.checkBox.textProperty().bindBidirectional(this.labelTextProperty);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                value.setValue("");
            textField.setDisable(!newValue);
        });
        textField.setDisable(!checkBox.selectedProperty().getValue());
        checkBox.setMinWidth(MIN_WIDTH_LEFT);
        textField.setMinWidth(MIN_WIDTH_RIGHT);
    }

    @Override
    public boolean isValid() {
        checkBox.setSelected(this.value.toString().isEmpty());
        return checkBox.selectedProperty().getValue() && super.isValid();
    }


}
