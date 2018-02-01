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

    String bundle() default "";

    String label() default "";

    String promptText() default "";

    String helpText() default "";

    String visibleGroup() default "default";

    String testRegex() default "";

    String onValidateFailure() default "";
}
