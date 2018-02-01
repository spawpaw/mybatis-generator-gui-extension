package com.spawpaw.mybatis.generator.gui.annotations;

import java.lang.annotation.*;

import static com.spawpaw.mybatis.generator.gui.util.Constants.tabs.BASIC_SETTINGS;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 将所标记的配置导出到指定的选项卡
 * 如果标记了多个本注解，将导出到多个选项卡
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ExportToTabs.class)
public @interface ExportToTab {

    String tabName() default BASIC_SETTINGS;

    int index() default 1000;
}
