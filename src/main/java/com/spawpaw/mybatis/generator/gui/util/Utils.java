package com.spawpaw.mybatis.generator.gui.util;

import com.google.common.base.CaseFormat;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created By spawpaw@hotmail.com  2018-01-28
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Utils {
    private static Logger log = LoggerFactory.getLogger(Utils.class);
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
            field.setAccessible(true);
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
        if ("true".equalsIgnoreCase(s)) return true;
        if ("false".equalsIgnoreCase(s)) return false;
        try {
            if (Integer.valueOf(s) != 0) return true;
            else if (Integer.valueOf(s) == 0) return false;
        } catch (Exception e) {
            //do nothing;
        }
        log.warn("您为一个boolean类型的字段指定了错误的值，应为true/false，实际为：{}", s);
        return false;
    }


    /**
     * 获取字符串的小骆驼峰形式
     */
    public static String getLowerCamelCase(String s) {
        if (s.startsWith("_"))
            return s;

        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, getUpperCamelCase(s));
    }

    /**
     * 几种规范的命名方式：
     * LOWER_CAMEL:         lowerCamel,lower
     * UPPER_CAMEL:         UpperCamel,Upper
     * LOWER_UNDERSCORE:    lower_underscore,
     * UPPER_UNDERSCORE:    UPPER_UNDERSCORE,
     * LOWER_HYPHEN:        lower-hyphen
     * <p>
     * 对于不规范的命名方式，仅提供基本的处理，并不保证完全符合需求,主要是underscore和camelcase混合的情况：
     * 0.将所有-替换成_
     * 1.对所有首部包含下划线的字符串将不作处理：_camel_Case_将直接返回
     * 2.1.去掉所有尾部的下划线：camel_Case_将转化为camel_Case
     * 2.2.如果字符串仍然包含下划线，或者字符串全部为大写，或者全部为小写，则按underscore处理：camel_CaSe_将转化为CamelCase
     * 2.3.否则按照CamelCase处理
     */
    public static String getUpperCamelCase(String s) {
        //0.
        s = s.replace("-", "_");
        //1.
        if (s.startsWith("_"))
            return s;

        //2.1去掉尾巴
        if (s.endsWith("_")) {
            s = s.replaceAll("(_)+$", "");
        }
        //2.2.如果全大写，或包含_
        if (s.replaceAll("[A-Z]+", "").isEmpty() || s.replaceAll("[a-z]+", "").isEmpty() || s.contains("_")) {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s.toUpperCase());
        }
        //2.3.如果不包含下划线
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, s);
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
