package com.switchvov.manual.config.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @author switch
 * @since 2020/2/6
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface FirstLevelRepository {
    String value() default "";
}
