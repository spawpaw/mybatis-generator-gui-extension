package com.spawpaw.mybatis.generator.gui.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 分页插件，为mapper提供limit和offset
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class PagePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

        //field
        Field limit = new Field();
        limit.setName("limit");
        limit.setVisibility(JavaVisibility.PRIVATE);
        limit.setType(integerWrapper);
        topLevelClass.addField(limit);

        Field offset = new Field();
        offset.setName("offset");
        offset.setVisibility(JavaVisibility.PRIVATE);
        offset.setType(integerWrapper);
        topLevelClass.addField(offset);

        //getter && setter
        Method setLimit = new Method();
        setLimit.setVisibility(JavaVisibility.PUBLIC);
        setLimit.setName("setLimit");
        setLimit.addParameter(new Parameter(integerWrapper, "limit"));
        setLimit.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(setLimit);

        Method getLimit = new Method();
        getLimit.setVisibility(JavaVisibility.PUBLIC);
        getLimit.setReturnType(integerWrapper);
        getLimit.setName("getLimit");
        getLimit.addBodyLine("return limit;");
        topLevelClass.addMethod(getLimit);


        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("setOffset");
        setOffset.addParameter(new Parameter(integerWrapper, "offset"));
        setOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(setOffset);

        Method getOffset = new Method();
        getOffset.setVisibility(JavaVisibility.PUBLIC);
        getOffset.setReturnType(integerWrapper);
        getOffset.setName("getOffset");
        getOffset.addBodyLine("return offset;");
        topLevelClass.addMethod(getOffset);

        Method setPageInfo = new Method();
        setPageInfo.setVisibility(JavaVisibility.PUBLIC);
        setPageInfo.setName("setPageInfo");
        setPageInfo.addParameter(new Parameter(integerWrapper, "currentPage"));
        setPageInfo.addParameter(new Parameter(integerWrapper, "pageSize"));
        setPageInfo.addBodyLine("if(pageSize<1) throw new IllegalArgumentException(\"页大小不能小于1！\");");
        setPageInfo.addBodyLine("this.limit=pageSize;");
        setPageInfo.addBodyLine("if(currentPage<1) throw new IllegalArgumentException(\"页数不能小于1！\");");
        setPageInfo.addBodyLine("this.offset=(currentPage-1)*pageSize;");

        topLevelClass.addMethod(setPageInfo);

        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement ifSetLimitSqlStatement = new XmlElement("if");
        ifSetLimitSqlStatement.addAttribute(new Attribute("test", "limit != null"));

        XmlElement ifSetOffsetSqlStatement = new XmlElement("if");
        ifSetOffsetSqlStatement.addAttribute(new Attribute("test", "offset != null"));
        ifSetOffsetSqlStatement.addElement(new TextElement("limit ${offset}, ${limit}"));
        ifSetLimitSqlStatement.addElement(ifSetOffsetSqlStatement);

        XmlElement ifOffsetNullElement = new XmlElement("if");
        ifOffsetNullElement.addAttribute(new Attribute("test", "offset == null"));
        ifOffsetNullElement.addElement(new TextElement("limit ${limit}"));
        ifSetLimitSqlStatement.addElement(ifOffsetNullElement);

        element.addElement(ifSetLimitSqlStatement);

        return true;
    }
}
