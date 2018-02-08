package com.spawpaw.mybatis.generator.gui.entity;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 表中字段的元信息
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TableColumnMetaData {

    private BooleanProperty checked = new SimpleBooleanProperty(true);

    private StringProperty columnName = new SimpleStringProperty();

    private StringProperty jdbcType = new SimpleStringProperty();

    private StringProperty javaType = new SimpleStringProperty();

    private StringProperty propertyName = new SimpleStringProperty();

    private StringProperty typeHandler = new SimpleStringProperty();

    public String getColumnName() {
        return columnName.get();
    }

    public void setColumnName(String columnName) {
        this.columnName.set(columnName);
    }

    public String getJdbcType() {
        return jdbcType.get();
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType.set(jdbcType);
    }

    public String getPropertyName() {
        return propertyName.get();
    }

    public void setPropertyName(String propertyName) {
        this.propertyName.set(propertyName);
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public Boolean getChecked() {
        return this.checked.get();
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    public StringProperty typeHandlerProperty() {
        return typeHandler;
    }

    public String getTypeHandler() {
        return typeHandler.get();
    }

    public void setTypeHandler(String typeHandler) {
        this.typeHandler.set(typeHandler);
    }

    public StringProperty columnNameProperty() {
        return columnName;
    }

    public StringProperty jdbcTypeProperty() {
        return jdbcType;
    }

    public StringProperty propertyNameProperty() {
        return propertyName;
    }

    public String getJavaType() {
        return javaType.get();
    }

    public void setJavaType(String javaType) {
        this.javaType.set(javaType);
    }

    public StringProperty javaTypeProperty() {
        return javaType;
    }
}