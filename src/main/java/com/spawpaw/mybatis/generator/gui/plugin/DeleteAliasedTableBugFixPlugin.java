package com.spawpaw.mybatis.generator.gui.plugin;

import com.spawpaw.mybatis.generator.gui.util.Utils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;
import java.util.Properties;

public class DeleteAliasedTableBugFixPlugin extends PluginAdapter {

    /**
     * DONT_FIX
     * DELETE_ALIAS_FROM_TABLE_ALIAS
     * DELETE_FROM_TABLE
     */
    private String fixType = "DONT_FIX";

    @Override

    public void setProperties(Properties properties) {
        super.setProperties(properties);
        Utils.injectFieldsFromProperties(this, properties);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return !"DONT_FIX".equals(fixType);
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        fixMysqlAliasBug(element, introspectedTable);
        return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        fixMysqlAliasBug(element, introspectedTable);
        return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
    }

    private void fixMysqlAliasBug(XmlElement element, IntrospectedTable introspectedTable) {
        for (int i = 0; i < element.getElements().size(); i++) {
            Element elem = element.getElements().get(i);
            String formattedContent = elem.getFormattedContent(0);
            if (formattedContent.contains("delete from")) {
                element.getElements().remove(i);
                String sql;
                if ("DELETE_ALIAS_FROM_TABLE_ALIAS".equals(fixType)) {
                    sql = String.format("delete %s from %s"
                            , introspectedTable.getTableConfiguration().getAlias()
                            , introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()
                    );
                } else if ("DELETE_FROM_TABLE".equals(fixType)) {
                    sql = String.format("delete from %s"
                            , introspectedTable.getFullyQualifiedTableNameAtRuntime()
                    );
                } else {
                    throw new RuntimeException("配置错误，请勿随意修改DeleteWorkAroundPlugin的配置");
                }
                element.getElements().add(i, new TextElement(sql));
                break;
            }
        }
    }
}
