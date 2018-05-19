package com.spawpaw.mybatis.generator.gui.entity;

import com.spawpaw.mybatis.generator.gui.util.ExampleUtil;
import com.spawpaw.mybatis.generator.gui.util.JavaBeansUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.Context;

import java.util.Map;

/**
 * Created By spawpaw@hotmail.com  2018-03-22
 * this class holds the basic info for a column.
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Column extends ConfigMatcher {
    public final String actualName;//真实列名称
    public final String fieldName;//该字段entity中的变量名称
    public final String fieldType;//该字段的类型
    public final String getterName;//该字段在entity中的getter名称
    public final String setterName;//该字段在entity中的setter名称

    //以下为该字段在Example类中的方法名
    public final String betweenMethod;
    public final String notBetweenMethod;
    public final String equalMethod;
    public final String greaterThanMethod;
    public final String greaterThenOrEqualMethod;
    public final String inMethod;
    public final String notInMethod;
    public final String lessThanMethod;
    public final String lessThanOrEqualMethod;
    public final String likeMethod;
    public final String notEqualMethod;
    public final String notLikeMethod;
    public final String notNullMethod;
    public final String nullMethod;
    //基本属性
    int index;//该列的显示顺序
    boolean disable = true;//当没有配置该字段时，默认为true，当配置该字段时，默认为false


    public Column(Context context, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Map<String, String> parent) {
        super(introspectedColumn.getRemarks(), parent);
        Field field = JavaBeansUtil.getJavaBeansField(introspectedColumn, context, introspectedTable);

        fieldName = field.getName();
        fieldType = field.getType().getFullyQualifiedName();
        actualName = introspectedColumn.getActualColumnName();
        getterName = JavaBeansUtil.getGetterMethodName(field.getName(), field.getType());
        setterName = JavaBeansUtil.getSetterMethodName(field.getName());

        this.betweenMethod = ExampleUtil.getSetBetweenOrNotBetweenMethod(introspectedColumn, true).getName();
        this.notBetweenMethod = ExampleUtil.getSetBetweenOrNotBetweenMethod(introspectedColumn, false).getName();
        this.equalMethod = ExampleUtil.getSetEqualMethod(introspectedColumn).getName();
        this.greaterThanMethod = ExampleUtil.getSetGreaterThanMethod(introspectedColumn).getName();
        this.greaterThenOrEqualMethod = ExampleUtil.getSetGreaterThenOrEqualMethod(introspectedColumn).getName();
        this.inMethod = ExampleUtil.getSetInOrNotInMethod(introspectedColumn, true).getName();
        this.notInMethod = ExampleUtil.getSetInOrNotInMethod(introspectedColumn, false).getName();
        this.lessThanMethod = ExampleUtil.getSetLessThanMethod(introspectedColumn).getName();
        this.lessThanOrEqualMethod = ExampleUtil.getSetLessThanOrEqualMethod(introspectedColumn).getName();
        this.likeMethod = ExampleUtil.getSetLikeMethod(introspectedColumn).getName();
        this.notEqualMethod = ExampleUtil.getSetNotEqualMethod(introspectedColumn).getName();
        this.notLikeMethod = ExampleUtil.getSetNotLikeMethod(introspectedColumn).getName();
        this.notNullMethod = ExampleUtil.getSetNotNullMethod(introspectedColumn).getName();
        this.nullMethod = ExampleUtil.getSetNullMethod(introspectedColumn).getName();

        if (contains("index"))
            index = Integer.valueOf(get("index"));
        if (contains("disable"))
            disable = Boolean.valueOf(get("disable"));
    }

    @Override
    public boolean contains(String key) {
        System.err.println("key:" + key + "          " + (super.contains(key) || super.contains(actualName + "." + key)));
        return super.contains(key) || super.contains(actualName + "." + key);
    }

    @Override
    public String get(String key) {
        System.err.println("getkey:" + key + "          " + (super.contains(key) || super.contains(actualName + "." + key)));
        return containsKey(key) ? super.get(key) : super.get(actualName + "." + key);
    }

    public int getIndex() {
        return index;
    }

    public boolean isDisable() {
        return disable;
    }

    public String getActualName() {
        return actualName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getGetterName() {
        return getterName;
    }

    public String getSetterName() {
        return setterName;
    }

    public String getBetweenMethod() {
        return betweenMethod;
    }

    public String getNotBetweenMethod() {
        return notBetweenMethod;
    }

    public String getEqualMethod() {
        return equalMethod;
    }

    public String getGreaterThanMethod() {
        return greaterThanMethod;
    }

    public String getGreaterThenOrEqualMethod() {
        return greaterThenOrEqualMethod;
    }

    public String getInMethod() {
        return inMethod;
    }

    public String getNotInMethod() {
        return notInMethod;
    }

    public String getLessThanMethod() {
        return lessThanMethod;
    }

    public String getLessThanOrEqualMethod() {
        return lessThanOrEqualMethod;
    }

    public String getLikeMethod() {
        return likeMethod;
    }

    public String getNotEqualMethod() {
        return notEqualMethod;
    }

    public String getNotLikeMethod() {
        return notLikeMethod;
    }

    public String getNotNullMethod() {
        return notNullMethod;
    }

    public String getNullMethod() {
        return nullMethod;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
