package com.spawpaw.mybatis.generator.gui.util;

import com.google.common.base.CaseFormat;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created By spawpaw@hotmail.com  2018-01-28
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Utils {
    private IntrospectedTable introspectedTable;


    public Utils(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }

    /**
     * 从properties中读取配置，如果不存在则不注入
     * 仅支持String和boolean类型的注入
     *
     * @param obj        要注入的对象
     * @param properties properties
     */
    public static <T> void injectFieldsFromProperties(T obj, Properties properties) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            //如果field对应的property为null
            if (properties.get(field.getName()) == null)
                continue;
            //如果不为null,注入所有属性（仅支持string,int,integer和boolean的注入）
            try {
                if (field.getType().getSimpleName().equalsIgnoreCase("String"))
                    field.set(obj, properties.get(field.getName()));
                if (field.getType().getSimpleName().equalsIgnoreCase("boolean"))
                    field.setBoolean(obj, isTrue(properties.getProperty(field.getName())));
                if (field.getType().getSimpleName().equalsIgnoreCase("short"))
                    field.setShort(obj, Short.valueOf(properties.getProperty(field.getName())));
                if (field.getType().getSimpleName().equalsIgnoreCase("int"))
                    field.setInt(obj, Integer.valueOf(properties.getProperty(field.getName())));
                if (field.getType().getSimpleName().equalsIgnoreCase("Integer"))
                    field.setInt(obj, Integer.valueOf(properties.getProperty(field.getName())));
                if (field.getType().getSimpleName().equalsIgnoreCase("double"))
                    field.setDouble(obj, Double.valueOf(properties.getProperty(field.getName())));
                if (field.getType().getSimpleName().equalsIgnoreCase("Float"))
                    field.setFloat(obj, Float.valueOf(properties.getProperty(field.getName())));
                if (field.getType().getSimpleName().equalsIgnoreCase("byte"))
                    field.setByte(obj, Byte.valueOf(properties.getProperty(field.getName())));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将字符串转化为布尔
     *
     * @param s 要转化的字符串，只支持true/false
     * @return 返回该字符串对应的boolean值
     */
    public static boolean isTrue(String s) {
        return "true".equalsIgnoreCase(s);
    }

    public static String getLowerCamelCase(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
    }

    public static String getUpperCamelCase(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s);
    }

    public static Method createPublicMethod(String returnType, String name, String annotation) {
        Method method = new Method(name);
        method.addAnnotation(annotation);
        if (returnType != null)
            method.setReturnType(new FullyQualifiedJavaType(returnType));
        method.setVisibility(JavaVisibility.PUBLIC);
        return method;
    }

    public static Method createPublicMethod(String returnType, String name) {
        Method method = new Method(name);
        if (returnType != null)
            method.setReturnType(new FullyQualifiedJavaType(returnType));
        method.setVisibility(JavaVisibility.PUBLIC);
        return method;
    }

    public static void addParameter(Method method, String annotation, String type, String name) {
        Parameter parameter = new Parameter(new FullyQualifiedJavaType(type), name);
        parameter.addAnnotation(annotation);
        method.addParameter(parameter);
    }

    public String getUpperCamelCaseTableName() {
        return getUpperCamelCase(introspectedTable.getFullyQualifiedTableNameAtRuntime());
    }

    public String getLowerCamelCaseTableName() {
        return getLowerCamelCase(introspectedTable.getFullyQualifiedTableNameAtRuntime());
    }

    public String getJavaMapperOriginalName() {
        return introspectedTable.getMyBatis3JavaMapperType();
    }

    public String getJavaMapperLowerCamelCase() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, new FullyQualifiedJavaType(getJavaMapperOriginalName()).getShortName());
    }

    public String getEntityOriginalName() {
        return introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    public String getEntityLowerCamelCase() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, new FullyQualifiedJavaType(getEntityOriginalName()).getShortName());
    }

    public String getExampleOriginalName() {
        return new FullyQualifiedJavaType(introspectedTable.getExampleType()).getShortName();
    }

    public String getExampleLowerCamelCase() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, new FullyQualifiedJavaType(getExampleOriginalName()).getShortName());
    }


}
