package com.spawpaw.mybatis.generator.gui.entity;

/**
 * Created By spawpaw@hotmail.com  2018-03-25
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TemplateConfig {
    private String template;
    private String destDir;
    private String destPackage;
    private String destFileName;


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public String getDestPackage() {
        return destPackage;
    }

    public void setDestPackage(String destPackage) {
        this.destPackage = destPackage;
    }

    public String getDestFileName() {
        return destFileName;
    }

    public void setDestFileName(String destFileName) {
        this.destFileName = destFileName;
    }
}
