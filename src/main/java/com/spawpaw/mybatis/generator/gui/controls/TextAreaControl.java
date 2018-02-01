package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TextAreaControl extends IControl<String> {
    protected TextArea textArea;
    private Label label;

    @Override
    protected void initView() {

        label = new Label();
        textArea = new TextArea();
        layout.getChildren().addAll(label, textArea);
    }

    @Override
    protected void bindProperties() {
        label.setMinWidth(MIN_WIDTH_LEFT);
        textArea.setMinWidth(MIN_WIDTH_RIGHT);
        label.textProperty().bindBidirectional(this.labelTextProperty);
        textArea.textProperty().bindBidirectional(this.value);
    }
}
