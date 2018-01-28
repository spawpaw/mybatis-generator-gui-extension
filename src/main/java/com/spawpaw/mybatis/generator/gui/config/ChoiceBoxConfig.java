package com.spawpaw.mybatis.generator.gui.config;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ChoiceBoxConfig extends Config<String> {
    transient JFXComboBox<String> comboBox;
    List<String> candidates;


    public ChoiceBoxConfig(String name, String value, List<String> candidates) {
        super(name, new SimpleStringProperty(value));
        this.candidates = candidates;
        bindProperties();
    }

    public ChoiceBoxConfig(String name, String value, String[] candidates) {
        this(name, value, Arrays.asList(candidates));
    }

    @Override
    protected void initView() {
        comboBox = new JFXComboBox<>();
        comboBox.promptTextProperty().bindBidirectional(nameProperty());
        comboBox.setLabelFloat(true);
        comboBox.autosize();
        layout.getChildren().add(comboBox);
    }

    @Override
    protected void bindProperties() {
        if (candidates != null) {
            for (String candidate : this.candidates) {
                comboBox.getItems().add(candidate);
            }
            this.comboBox.valueProperty().bindBidirectional(valueProperty());
        }
    }
}
