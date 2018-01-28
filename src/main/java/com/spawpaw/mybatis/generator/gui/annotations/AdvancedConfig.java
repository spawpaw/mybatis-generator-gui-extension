package com.spawpaw.mybatis.generator.gui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 被该注解标记的配置项将在开启简洁模式时隐藏
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdvancedConfig {
}
