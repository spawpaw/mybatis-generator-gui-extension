package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TextAreaControl extends IControl<String> {
    private Label label = new Label();
    private TextArea textArea = new TextArea();

    @Override
    protected void initView() {
        layout.getChildren().addAll(label, textArea);
    }

    @Override
    protected void bindProperties() {
        label.textProperty().bindBidirectional(this.labelText);
        textArea.textProperty().bindBidirectional(this.value);
    }
}
