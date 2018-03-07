package com.spawpaw.mybatis.generator.gui.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Arrays;

/**
 * Created By spawpaw@hotmail.com  2018-03-07
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ComboBoxControl extends IControl<String> {
    protected ComboBox<String> comboBox = new ComboBox<>();
    protected Label label = new Label();


    @Override
    public void initView() {
        layout.getChildren().addAll(label, comboBox);
        comboBox.setTooltip(tooltip);
        label.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        label.setMinWidth(MIN_WIDTH_LEFT);
        comboBox.setMinWidth(MIN_WIDTH_RIGHT);
        comboBox.setEditable(true);
        comboBox.setItems(FXCollections.observableList(Arrays.asList(_testRegex.split("\n"))));
        this.comboBox.promptTextProperty().bindBidirectional(this.promptTextProperty);
        this.comboBox.valueProperty().bindBidirectional(value);
        this.label.textProperty().bindBidirectional(this.labelTextProperty);
    }
}
