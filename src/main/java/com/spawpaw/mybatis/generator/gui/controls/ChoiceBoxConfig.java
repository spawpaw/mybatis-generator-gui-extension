package com.spawpaw.mybatis.generator.gui.controls;

import javafx.scene.control.ChoiceBox;

import java.util.List;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ChoiceBoxConfig extends IControl<String> {
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    List<String> candidates;

    @Override
    protected void initView() {

        layout.getChildren().addAll(choiceBox);
    }

    @Override
    protected void bindProperties() {
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
