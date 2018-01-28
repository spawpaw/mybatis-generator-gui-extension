package com.spawpaw.mybatis.generator.gui.config;

import com.spawpaw.mybatis.generator.gui.util.Constants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.Serializable;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 配置项的基类
 * 封装了控件和对应值，便于自定义MBG插件的扩展
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public abstract class Config<T> implements Serializable {
    protected transient HBox layout;
    private StringProperty name = new SimpleStringProperty("配置项名称");
    private Property<T> value;

    /**
     * @param name  该配置项的显示名称
     * @param value 该配置项的默认值
     */
    protected Config(String name, Property<T> value) {
        setName(name);
        this.value = value;
        initialize();
    }

    public Parent generateLayout() {
        return layout;
    }

    protected abstract void initView();

    protected abstract void bindProperties();

    public void initialize() {
        this.layout = new HBox();
        layout.setPadding(Constants.ui.DEFAULT_CTL_INSETS);
        initView();
        bindProperties();
    }

    public T getValue() {
        return value.getValue();
    }

    public void setValue(T value) {
        this.value.setValue(value);
    }

    public Property<T> valueProperty() {
        return value;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void bindVisibleProperty(BooleanProperty property) {
        layout.visibleProperty().bindBidirectional(property);
        layout.managedProperty().bindBidirectional(property);
    }

}
