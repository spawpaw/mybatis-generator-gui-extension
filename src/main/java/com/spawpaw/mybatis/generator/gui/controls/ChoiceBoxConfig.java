package com.spawpaw.mybatis.generator.gui.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ChoiceBoxConfig extends IControl<String> {
    Label label;
    ChoiceBox<String> choiceBox;
    List<String> candidates;

    @Override
    protected void initView() {
        label = new Label();
        choiceBox = new ChoiceBox<>();
        layout.getChildren().addAll(label, choiceBox);
        label.setTooltip(tooltip);
        choiceBox.setTooltip(tooltip);
    }

    @Override
    protected void bindProperties() {
        label.setMinWidth(MIN_WIDTH_LEFT);
        choiceBox.setMinWidth(MIN_WIDTH_RIGHT);

        choiceBox.setItems(FXCollections.observableList(Arrays.asList(_testRegex.split("\\|"))));
        choiceBox.setTooltip(tooltip);
        label.textProperty().bindBidirectional(labelTextProperty);
        choiceBox.valueProperty().bindBidirectional(this.value);
    }

    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
