package com.spawpaw.mybatis.generator.gui.annotations;

import javafx.scene.layout.HBox;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ConfigWrapper {
    //    Config config;
//    ExportToTab[] exportToTabs;
//    ExportToPlugin[] exportToPlugins;
//    EnablePlugin[] enablePlugins;
//    Property property;
    public HBox layout;
    public int index;

    public ConfigWrapper(HBox layout, int index) {
        this.index = index;
        this.layout = layout;
    }
}
