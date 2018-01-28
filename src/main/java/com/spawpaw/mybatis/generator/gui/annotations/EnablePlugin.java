package com.spawpaw.mybatis.generator.gui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 当添加该注解的Property/Config的值不为空时，将启用指定的plugin
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePlugin {
    String value() default "";
}
