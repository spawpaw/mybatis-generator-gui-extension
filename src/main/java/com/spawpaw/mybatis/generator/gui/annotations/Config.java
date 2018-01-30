package com.spawpaw.mybatis.generator.gui.annotations;

/**
 * Created By spawpaw@hotmail.com  2018-01-30
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public @interface Config {
    ConfigType type() default ConfigType.TextField;

    String label() default "";

    String promptText() default "";

    String helpText() default "";

    String visibleGroup() default "default";

    String testRegex() default "";

    String onValidateFailure() default "";
}
