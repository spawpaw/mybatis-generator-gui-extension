package com.spawpaw.mybatis.generator.gui.controls;

import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public abstract class IControl<T> {
    protected static final int MIN_WIDTH_LEFT = 150;
    protected static final int MIN_WIDTH_RIGHT = 150;
    private static List<IControl> controls = new ArrayList<>();
    protected String _testRegex = "";
    protected String _onValidateFailure = "";
    protected StringProperty labelTextProperty = new SimpleStringProperty("");
    protected StringProperty promptTextProperty = new SimpleStringProperty("");
    protected StringProperty helpTextProperty = new SimpleStringProperty("");
    protected StringProperty onValidateFailureTextProperty = new SimpleStringProperty("");
    protected Property<T> value;
    protected HBox layout = new HBox();
    protected Tooltip tooltip = new Tooltip();
    protected Tooltip onValidateFailureToolTip = new Tooltip();
    private String _labelText = "";
    private String _promptText = "";
    private String _helpText = "";
    private String _visibleGroup = "";

    protected IControl() {
        tooltip.textProperty().bindBidirectional(helpTextProperty);
        tooltip.setStyle("-fx-font-size:14");
        onValidateFailureToolTip.textProperty().bindBidirectional(onValidateFailureTextProperty);
        controls.add(this);
        refreshLabel();
    }

    /**
     * 刷新所有控件的label
     */
    public static void refreshLabels() {
        for (IControl control : controls) {
            control.refreshLabel();
        }
    }

    /**
     * 根据Locale更新显示的字符
     */
    private void refreshLabel() {
        labelTextProperty.setValue(Constants.getI18nStr(_labelText));
        promptTextProperty.setValue(Constants.getI18nStr(_promptText));
        helpTextProperty.setValue(Constants.getI18nStr(_helpText));
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
        layout.setPadding(Constants.ui.DEFAULT_CTL_INSETS);
        return layout;
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

    public void set_visibleGroup(String _visibleGroup) {
        this._visibleGroup = _visibleGroup;
    }

    public void set_testRegex(String _testRegex) {
        this._testRegex = _testRegex;
    }

    public void set_onValidateFailure(String _onValidateFailure) {
        this._onValidateFailure = _onValidateFailure;
    }

    public void setValue(Property<T> value) {
        this.value = value;
    }

    /**
     * 当值为null/空字符串/控件被disable时，返回false
     */
    public boolean isValid() {
        return value.getValue().toString().isEmpty();
    }
}
