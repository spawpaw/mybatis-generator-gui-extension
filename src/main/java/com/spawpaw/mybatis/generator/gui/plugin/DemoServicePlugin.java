package com.spawpaw.mybatis.generator.gui.plugin;

import com.spawpaw.mybatis.generator.gui.util.Utils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class DemoServicePlugin extends PluginAdapter {
    public String projectDir = "";      //cannot be null
    public String servicePackage = "";  //cannot be null
    public String serviceName = null;   //if null, use the `domain object name`+`Service`
    public String baseUrl = null;       //if null, use the lower camel case of `domain object name`
    public String commonResponseWithPageInfoType = "";

    //在字段注释中添加`@EnableSearch()`来启用搜索，支持如下参数：
    //full:             []全文搜索，仅全部匹配时返回对应记录.
    //like:             []模糊搜索(大小写敏感)
    //likeIgnoreCase:   []模糊搜索(大小写不敏感)
    //largerThan:       比指定值大
    //lessThan:         比指定值小
    //例如@EnableSearch(full,like,likeIgnoreCase)
    //如不填参数，则仅开启full
    public boolean enableGetList = true;                    //GET:      uri?criteria
    public String getListRequestMethod = "GET";
    public String getListUrl = "";
    public Integer getListMaxPageSize = 1000;

    public boolean enableGetByPK = true;                    //GET:      uri/id
    public String getByPKRequestMethod = "GET";
    public String getByPKUrl = "/{id}";

    public boolean enableCreate = true;                     //POST:     uri
    public String createRequestMethod = "POST";
    public String createUrl = "";

    public boolean enableCreateBatch = true;                //POST:     uri
    public String createBatchRequestMethod = "POST";
    public String createBatchUrl = "/batch";

    public boolean enableUpdateByPK = true;                 //PUT:      uri/{id}
    public String updateByPKRequestMethod = "PUT";
    public String updateByPKUrl = "";

    public boolean enableUpdateByPKSelective = true;        //PATCH:      uri/{id}        only update none-null fields
    public String updateByPKSelectiveRequestMethod = "PATCH";
    public String updateByPKSelectiveUrl = "";

    public boolean enableUpdateBatch = true;                //PUT:      uri
    public String updateBatchMethodName = "PUT";
    public String updateBatchUrl = "";

    public boolean enableDeleteByPK = true;                 //DELETE:   uri/{id}
    public String deleteByPKRequestMethod = "DELETE";
    public String deleteByPKUrl = "/{id}";

    public boolean enableDeleteBatch = true;                //DELETE:   uri?id=1&id=2&id=3   批量删除
    public String deleteBatchRequestMethod = "DELETE";
    public String deleteBatchUrl = "";

    private Utils util;

    @Override
    public void setProperties(Properties properties) {
        Utils.injectFieldsFromProperties(this, properties);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        //初始化
        util = new Utils(introspectedTable);
        //service名
        if (serviceName == null)
            serviceName = util.getEntityOriginalName() + "Service";
        //base url
        if (baseUrl == null)
            baseUrl = util.getEntityLowerCamelCase();

        List<GeneratedJavaFile> files = new ArrayList<>();
        TopLevelClass restfulService = new TopLevelClass(servicePackage + "." + serviceName);
        restfulService.setVisibility(JavaVisibility.PUBLIC);
        restfulService.addImportedType("java.util.List");
        restfulService.addImportedType("org.springframework.web.bind.annotation.*");
        restfulService.addImportedType("javax.annotation.Resource");
        restfulService.addImportedType("javax.servlet.http.HttpSession");
        restfulService.addImportedType(introspectedTable.getMyBatis3JavaMapperType());
        restfulService.addImportedType(introspectedTable.getExampleType());
        restfulService.addImportedType(introspectedTable.getBaseRecordType());
        restfulService.addImportedType(commonResponseWithPageInfoType);
        restfulService.addAnnotation("@RestController");
        restfulService.addAnnotation(String.format("@RequestMapping(path = \"/%s\")", baseUrl));

        Field mapper = new Field(util.getJavaMapperLowerCamelCase(), new FullyQualifiedJavaType(util.getJavaMapperOriginalName()));
        mapper.addAnnotation("@Resource");
        restfulService.addField(mapper);

        if (enableGetList)
            restfulService.addMethod(getList());
        if (enableGetByPK)
            restfulService.addMethod(getByPK());
        if (enableCreate)
            restfulService.addMethod(create());

        // TODO: 2017/11/22 create batch  批量增加
//        if (enableCreateBatch)
//        restfulService.addMethod(createBatch());

        if (enableUpdateByPK)
            restfulService.addMethod(updateByPK());
        if (enableUpdateByPKSelective)
            restfulService.addMethod(updateByPkSelective());

        // TODO: 2017/11/28 updateBatch 批量修改
//        if (enableUpdateBatch)
//            restfulService.addMethod(updateByPK());

        if (enableDeleteByPK)
            restfulService.addMethod(deleteByPK());

        // TODO: 2017/11/22 delete batch 批量删除
//        if (enableDeleteBatch)
//        restfulService.addMethod(deleteBatch());

        if (enableDeleteBatch)
            restfulService.addMethod(deleteBatch());

        GeneratedJavaFile gjf = new GeneratedJavaFile(restfulService, projectDir, new DefaultJavaFormatter());
        files.add(gjf);
        return files;
    }


    private Method getList() {
        Method method = Utils.createPublicMethod(String.format("List<%s>", util.getEntityOriginalName()), "getList");
        addRequestMapping(method, getListUrl, getListRequestMethod);
        Utils.addParameter(method, "@RequestParam(name = \"page\", required = false)", "Integer", "page");
        Utils.addParameter(method, "@RequestParam(name = \"pageSize\", required = false)", "Integer", "pageSize");
        Utils.addParameter(method, "", "HttpSession", "session");

        method.addBodyLine(String.format("%s example=new %s();", util.getExampleOriginalName(), util.getExampleOriginalName()) +
                "if(page==null||pageSize==null){" + "page=1;pageSize=" + getListMaxPageSize + ";}" +//如果请求中包含分页请求，则进行分页操作，否则选取全表

                "example.setPageInfo(page,pageSize);" +
                String.format("List<%s> data=%s.selectByExample(example);", util.getEntityOriginalName(), util.getJavaMapperLowerCamelCase()) +
                String.format("return %s.countByExample(example);", util.getJavaMapperLowerCamelCase())
        );
        return method;
    }

    private Method getByPK() {
        Method method = Utils.createPublicMethod(String.format(util.getEntityOriginalName(), util.getEntityOriginalName()), "getByPK");
        addRequestMapping(method, getByPKUrl, getByPKRequestMethod);
        Utils.addParameter(method, " @PathVariable(\"id\")", "int", "id");
        Utils.addParameter(method, "", "HttpSession", "session");

        method.addBodyLine(String.format("return %s.selectByPrimaryKey(id);", util.getJavaMapperLowerCamelCase()));
        return method;
    }

    private Method create() {
        Method method = Utils.createPublicMethod(util.getEntityOriginalName(), "create");
        addRequestMapping(method, createUrl, createRequestMethod);
        Utils.addParameter(method, "@RequestBody", util.getEntityOriginalName(), util.getEntityLowerCamelCase());
        Utils.addParameter(method, "", "HttpSession", "session");

        method.addBodyLine(String.format("%s.insert(%s);", util.getJavaMapperLowerCamelCase(), util.getEntityLowerCamelCase()));
        method.addBodyLine(String.format("return %s;", util.getEntityLowerCamelCase()));
        return method;
    }

    private Method updateByPK() {
        Method method = Utils.createPublicMethod(null, "updateByPK");
        addRequestMapping(method, updateByPKUrl, updateByPKRequestMethod);
        Utils.addParameter(method, "@RequestBody", util.getEntityOriginalName(), util.getEntityLowerCamelCase());
        Utils.addParameter(method, "", "HttpSession", "session");

        method.addBodyLine(String.format("%s.updateByPrimaryKey(%s);", util.getJavaMapperLowerCamelCase(), util.getEntityLowerCamelCase()));
        return method;
    }

    private Method updateByPkSelective() {
        Method method = Utils.createPublicMethod(null, "updateByPkSelective");
        addRequestMapping(method, updateByPKSelectiveUrl, updateByPKSelectiveRequestMethod);
        Utils.addParameter(method, "@RequestBody", util.getEntityOriginalName(), util.getEntityLowerCamelCase());
        Utils.addParameter(method, "", "HttpSession", "session");

        method.addBodyLine(String.format("%s.updateByPrimaryKeySelective(%s);", util.getJavaMapperLowerCamelCase(), util.getEntityLowerCamelCase()));
        return method;
    }

    private Method deleteByPK() {
        Method method = Utils.createPublicMethod("int", "deleteByPK");
        addRequestMapping(method, deleteByPKUrl, deleteByPKRequestMethod);
        Utils.addParameter(method, "@RequestBody", util.getEntityOriginalName(), util.getEntityLowerCamelCase());
        Utils.addParameter(method, "", "HttpSession", "session");
        Utils.addParameter(method, " @PathVariable(\"id\")", "int", "id");

        method.addBodyLine(String.format("return %s.deleteByPrimaryKey(id);", util.getJavaMapperLowerCamelCase()));
        return method;
    }

    private Method deleteBatch() {
        Method method = Utils.createPublicMethod("int", "deleteBatch");
        addRequestMapping(method, deleteBatchUrl, deleteBatchRequestMethod);
        Utils.addParameter(method, "", "HttpSession", "session");
        Utils.addParameter(method, "@RequestParam", "List<Integer>", "ids");

        method.addBodyLine(String.format("%s example = new %s();", util.getExampleOriginalName(), util.getExampleOriginalName()));
        method.addBodyLine("example.or().andIdIn(ids);");
        method.addBodyLine(String.format("return %s.deleteByExample(example);", util.getJavaMapperLowerCamelCase()));
        return method;
    }

    private void addRequestMapping(Method method, String path, String requestMethod) {
        method.addAnnotation(String.format("@RequestMapping(path=\"%s\",method=RequestMethod.%s)", path, requestMethod));
    }

}
