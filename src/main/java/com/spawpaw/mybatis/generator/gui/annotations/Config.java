package com.spawpaw.mybatis.generator.gui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
    ConfigType type() default ConfigType.TextField;

    /**
     * i18n资源的key
     * 如果不填此项，可以使用下面的字段来手动指定
     */
    String bundle() default "";

    /**
     * 表单控件的label
     * 可以使用i18n资源的key，下同
     */
    String label() default "";

    /**
     * 表单输入框的prompt
     */
    String promptText() default "";

    /**
     * 表单输入框的提示，在鼠标放到空间上时，会在弹出的tooltip上展示该信息
     */
    String helpText() default "";

    String visibleGroup() default "default";

    /**
     * 值的测试正则式，在生成代码之前会通过该正则式来判断是否有字段填写错误
     */
    String testRegex() default "";

    String onValidateFailure() default "";
}
