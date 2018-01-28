package com.spawpaw.mybatis.generator.gui.config;

import com.jfoenix.controls.JFXCheckBox;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 带有CheckBox的TextField配置项
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CheckableTextFieldConfig extends TextFieldConfig {
    protected transient JFXCheckBox checkBox;
    private boolean checked;

    public CheckableTextFieldConfig(String name, String value, Boolean checked) {
        super(name, value);
        this.checked = checked;
    }

    @Override
    protected void initView() {
        checkBox = new JFXCheckBox();
        layout.getChildren().add(checkBox);
        super.initView();
    }

    @Override
    protected void bindProperties() {
        super.bindProperties();
        textField.disableProperty().setValue(!checked);
        checkBox.selectedProperty().setValue(checked);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            textField.disableProperty().setValue(!newValue);
        });
    }

    public boolean isChecked() {
        return checkBox.selectedProperty().getValue();
    }
}
