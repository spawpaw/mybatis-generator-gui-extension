package com.spawpaw.mybatis.generator.gui.annotations;


import java.lang.annotation.*;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 将配置导出到某个插件
 * <p>
 * 如果遇到无法识别的类型，将调用该配置项的toString方法获取值
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ExportToPlugins.class)
public @interface ExportToPlugin {
    /**
     * 要导出到插件的全名
     */
    String plugin();

    /**
     * 该项配置的键
     */
    String key() default "";
}
