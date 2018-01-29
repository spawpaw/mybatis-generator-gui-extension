
# MyBatis Generator Config Hierarchy
> mybatisGeneratorConfig的层次结构  
> DTD: http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd
## generatorConfiguration
- properties?  `resource`  `url`
- classPathEntry* `location`
- context+  `id` `defaultModelType` `targetRuntime` `introspectedColumnImpl`   
    - property*  `autoDelimitKeywords` `beginningDelimiter` `endingDelimiter` `javaFileEncoding` `javaFormatter` `xmlFormatter` 
    - plugin* `type`
    - commentGenerator? `type`
    - (connectionFactory  `type` | jdbcConnection  `driverClass` `connectionURL` `userId` `password`)
      - property*  `name` `value`
    - javaTypeResolver?  `type`
      - property*  `name` `value`
    - javaModelGenerator   `targetPackage` `targetProject` 
      - property*  `name` `value`
    - sqlMapGenerator?  `targetPackage` `targetProject`
      - property*  `name` `value`
    - javaClientGenerator?  `type` `targetPackage` `targetProject` `implementationPackage` 
      - property*  `name` `value`
    - table+ `catalog` `schema` `tableName` `alias` `domainObjectName` `mapperName` `sqlProviderName` `enableInsert` `enableSelectByPrimaryKey` `enableSelectByExample` `enableUpdateByPrimaryKey` `enableDeleteByPrimaryKey` `enableDeleteByExample` `enableCountByExample` `enableUpdateByExample` `selectByPrimaryKeyQueryId` `selectByExampleQueryId` `modelType` `escapeWildcards` `delimitIdentifiers` `delimitAllColumns` 
      - property*   `constructorBased` `ignoreQualifiersAtRuntime` `immutable` `modelOnly` `rootClass` `rootInterface` `runtimeCatalog` `runtimeSchema` `runtimeTableName` `selectAllOrderByClause` `trimStrings` `useActualColumnNames` `useColumnIndexes` `useCompoundPropertyNames`
      - generatedKey? `column` `sqlStatement` `identity` `type` 
      - columnRenamingRule? `searchString` `replaceString` 
      - (
         columnOverride  `column` `property` `javaType` `jdbcType` `typeHandler` `isGeneratedAlways` `delimitedColumnName`   
       | ignoreColumn     `column`   `delimitedColumnName`  
       | ignoreColumnsByRegex 
          - except* `pattern`  
       )*  
