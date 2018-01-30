package com.spawpaw.mybatis.generator.gui.controls;

import com.spawpaw.mybatis.generator.gui.annotations.ConfigType;
import javafx.beans.property.Property;
import javafx.scene.layout.HBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ControlsFactory {
    public static HBox getLayout(ConfigType type, String labelText, String promptText, String helpText, String testRegex, String onValidateFailure, String visibleGroup, Property defaultValue) {
        IControl control;
        switch (type) {
            case CheckBox:
            case TextArea:
            case ChoiceBox:
            case TextField:
                control = new TextFieldControl();
                break;
            case DirChooser:
            case FileChooser:
            case CheckableTextArea:
            case CheckableTextField:
                control=new CheckableTextFieldControl();
                break;
            default:
                control = new TextFieldControl();
        }
        control.set_labelText(labelText);
        control.set_promptText(promptText);
        control.set_helpText(helpText);
        control.setTestRegex(testRegex);
        control.setVisibleGroup(visibleGroup);
        control.setOnValidateFailure(onValidateFailure);
        control.setValue(defaultValue);

        return control.getLayout();
    }


}
