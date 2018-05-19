package com.spawpaw.mybatis.generator.gui.entity;

import com.spawpaw.mybatis.generator.gui.util.Utils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created By spawpaw@hotmail.com  2018-03-22
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Table extends ConfigMatcher {
    public final String actualName;
    public final String entityPackage;
    public final String entityName;
    public final String entityLowerCamel;
    public final String exampleName;
    public final String exampleLowerCamel;
    public final String mapperPackage;
    public final String mapperName;
    public final String mapperLowerCamel;
    public List<Column> columns = new ArrayList<>();
    Logger log = LoggerFactory.getLogger(Table.class);


    public Table(Context context, IntrospectedTable introspectedTable, Map<String, String> parent) {
        super(introspectedTable.getRemarks(), parent);
        log.info(">>>>>> begin initialize table configuration");

        actualName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();

        entityPackage = introspectedTable.getIbatis2SqlMapPackage();
        entityName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        entityLowerCamel = Utils.getLowerCamelCase(entityName);

        exampleName = introspectedTable.getExampleType();
        exampleLowerCamel = Utils.getLowerCamelCase(exampleName);

        mapperPackage = introspectedTable.getFullyQualifiedTable().getDomainObjectSubPackage();
        mapperName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        mapperLowerCamel = Utils.getLowerCamelCase(mapperName);

        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            columns.add(new Column(context, introspectedTable, introspectedColumn, this));
        }
        log.info("<<<<<< initialized table configuration\n");
    }


    public Logger getLog() {
        return log;
    }

    public String getActualName() {
        return actualName;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getEntityLowerCamel() {
        return entityLowerCamel;
    }

    public String getExampleName() {
        return exampleName;
    }

    public String getExampleLowerCamel() {
        return exampleLowerCamel;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public String getMapperName() {
        return mapperName;
    }

    public String getMapperLowerCamel() {
        return mapperLowerCamel;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setLog(Logger log) {
        this.log = log;
    }
}
