package com.spawpaw.mybatis.generator.gui.util;

import com.spawpaw.mybatis.generator.gui.controls.IControl;
import javafx.geometry.Insets;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 常量池
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Constants {
    //保存配置的目录
    public static final String CONFIG_SAVE_PATH = "data/config/";
    public static final String CONNECTION_SAVE_PATH = "data/connection/";
    private static Locale SPECIFIED_LOCALE = Locale.getDefault();
    //缓存Res
    private static Map<Locale, ResourceBundle> resourceBundles = new HashMap<>();

    //当前系统的语言，为国际化准备
    public static void setLocale(Locale locale) {
        SPECIFIED_LOCALE = locale;
        IControl.refreshLabels();
    }

    public static String getI18nStr(String s) {
        try {
            if (!getResourcesBundle().getString(s).isEmpty())
                return getResourcesBundle().getString(s);
        } catch (Exception e) {
            return s;
        }
        return s;
    }

    public static ResourceBundle getResourcesBundle() {
        if (SPECIFIED_LOCALE.equals(Locale.CHINA)
                || SPECIFIED_LOCALE.equals(Locale.CHINESE)
                || SPECIFIED_LOCALE.equals(Locale.SIMPLIFIED_CHINESE)
                || SPECIFIED_LOCALE.equals(Locale.PRC)
                || SPECIFIED_LOCALE.equals(Locale.TRADITIONAL_CHINESE)
                || SPECIFIED_LOCALE.equals(Locale.TAIWAN)
                ) {
            if (resourceBundles.get(Locale.CHINA) == null)
                resourceBundles.put(Locale.CHINA, ResourceBundle.getBundle("i18n.locale", Locale.CHINA));
            return resourceBundles.get(Locale.CHINA);
        } else {
            if (resourceBundles.get(Locale.ENGLISH) == null)
                resourceBundles.put(Locale.ENGLISH, ResourceBundle.getBundle("i18n.locale", Locale.ENGLISH));
            return resourceBundles.get(Locale.ENGLISH);
        }
    }

    //选项卡，当配置过多时，将分为多个选项卡显示，在这里统一管理选项卡的名称
    public static class tabs {
        public static final String SHORTCUT = "ui.tab.0.SHORTCUT";
        public static final String BASIC_SETTINGS = "ui.tab.1.BASIC_SETTINGS";
        public static final String DATA_ACCESS_OBJECT = "ui.tab.2.DATA_ACCESS_OBJECT";
        public static final String DOMAIN_OBJECT = "ui.tab.3.DOMAIN_OBJECT";
        public static final String MVC = "ui.tab.4.MVC";
        public static final String COMMENT = "ui.tab.5.COMMENT";
        public static final String CACHE = "ui.tab.6.CACHE";
    }

    //与UI有关的常量
    public static class ui {
        public static final String MAIN_WINDOW_TITLE = "GUI extension for mybatis-generator";
        public static final int MIN_TEXT_FIELD_WIDTH = 360;
        public static final Insets DEFAULT_CTL_INSETS = (new Insets(8, 0, 8, 0));
        public static final Insets DEFAULT_LAYOUT_INSETS = (new Insets(8, 8, 8, 8));
    }


}
