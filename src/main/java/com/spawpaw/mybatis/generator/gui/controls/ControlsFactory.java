package com.spawpaw.mybatis.generator.gui.controls;

import com.spawpaw.mybatis.generator.gui.annotations.Config;
import javafx.beans.property.Property;
import javafx.scene.layout.HBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ControlsFactory {
    public static HBox getLayout(Config config, Property value) {
        if (config == null) {
            System.out.println("无效的配置项,请为Property添加@Config注解");
            return new HBox();
        }
        IControl control;
        switch (config.type()) {
            case ComboBox:
                control = new ComboBoxControl();
                break;
            case CheckBox:
                control = new CheckBoxControl();
                break;
            case TextArea:
                control = new TextAreaControl();
                break;
            case ChoiceBox:
                control = new ChoiceBoxConfig();
                break;
            case TextField:
                control = new TextFieldControl();
                break;
            case DirChooser:
                control = new DirectoryChooserControl();
                break;
            case FileChooser:
                control = new FileChooserControl();
                break;
            case CheckableTextArea:
                control = new CheckableTextAreaControl();
                break;
            case CheckableTextField:
                control = new CheckableTextFieldControl();
                break;
            default:
                control = new TextFieldControl();
        }
        if (!config.bundle().isEmpty()) {
            control.set_labelText(config.bundle() + ".labelText");
            control.set_promptText(config.bundle() + ".promptText");
            control.set_helpText(config.bundle() + ".helpText");
            control.set_onValidateFailure(config.bundle() + ".onValidateError");
        } else {
            control.set_labelText(config.label());
            control.set_promptText(config.promptText());
            control.set_helpText(config.helpText());
        }
        control.set_testRegex(config.testRegex());
        control.set_visibleGroup(config.visibleGroup());
        control.set_onValidateFailure(config.onValidateFailure());
        control.setValue(value);

        return control.getLayout();
    }


}
