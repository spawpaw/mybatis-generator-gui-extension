package com.spawpaw.mybatis.generator.gui.controls;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public abstract class IControl<T> {
    private static List<IControl> controls = new ArrayList<>();

    private String _labelText = "";
    private String _promptText = "";
    private String _helpText = "";
    private String visibleGroup = "";
    protected String testRegex = "";
    protected String onValidateFailure = "";

    protected StringProperty labelText = new SimpleStringProperty();
    protected StringProperty promptText = new SimpleStringProperty();
    protected StringProperty helpText = new SimpleStringProperty();
    protected Property<T> value;
    protected HBox layout = new HBox();

    protected IControl() {
        System.out.println("IControl:构造函数");
        controls.add(this);
    }

    private void initialize() {
        initView();
        bindProperties();
        refreshLabel();
    }


    protected abstract void initView();

    protected abstract void bindProperties();

    public HBox getLayout() {
        if (layout == null || layout.getChildren().size() == 0)
            initialize();
        return layout;
    }

    /**
     * 根据Locale更新显示的字符
     */
    public static void refreshLabels() {
        for (IControl control : controls) {
            control.refreshLabel();
        }
    }

    private void refreshLabel() {
        labelText.setValue(_labelText);
        promptText.setValue(_promptText);
        helpText.setValue(_helpText);

    }

    public void set_labelText(String _labelText) {
        this._labelText = _labelText;
    }

    public void set_promptText(String _promptText) {
        this._promptText = _promptText;
    }

    public void set_helpText(String _helpText) {
        this._helpText = _helpText;
    }

    public void setVisibleGroup(String visibleGroup) {
        this.visibleGroup = visibleGroup;
    }

    public void setTestRegex(String testRegex) {
        this.testRegex = testRegex;
    }

    public void setOnValidateFailure(String onValidateFailure) {
        this.onValidateFailure = onValidateFailure;
    }

    public void setValue(T value) {
        this.value.setValue(value);
    }

    /**
     * 当值为null/空字符串/控件被disable时，返回false
     */
    public boolean isValid() {
        return value.getValue().toString().isEmpty();
    }
}
