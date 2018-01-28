package com.spawpaw.mybatis.generator.gui.config;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ConfigWrapper {
    public Config config;
    public int index;
    public int group = 0;

    public ConfigWrapper(Config config, int index) {
        this.config = config;
        this.index = index;
    }
}
