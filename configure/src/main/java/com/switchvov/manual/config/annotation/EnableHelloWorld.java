package com.switchvov.manual.config.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author switch
 * @since 2020/2/6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldConfiguration.class)
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
