package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckableTextAreaControl extends IControl<String> {
    protected TextArea textArea = new TextArea();
    CheckBox checkBox = new CheckBox();

    @Override
    public void initView() {
        layout.getChildren().addAll(checkBox, textArea);
        checkBox.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        textArea.textProperty().bindBidirectional(value);
        textArea.promptTextProperty().bindBidirectional(promptTextProperty);
        checkBox.textProperty().bindBidirectional(labelTextProperty);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                value.setValue("");
            textArea.setDisable(!newValue);
        });
        textArea.setDisable(!checkBox.selectedProperty().getValue());
    }

    @Override
    public boolean isValid() {
        checkBox.setSelected(this.value.toString().isEmpty());
        return checkBox.selectedProperty().getValue() && super.isValid();
    }
}
