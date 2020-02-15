package com.switchvov.manual.config.configuration;

import com.switchvov.manual.config.annotation.EnableHelloWorld;
import com.switchvov.manual.config.condition.ConditionOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2020/2/6
 */
@Configuration // Spring模式注解
@EnableHelloWorld // Spring @Enable 模式装配
@ConditionOnSystemProperty(name = "user.name", value = "switch") // 条件装配
public class HelloWorldAutoConfiguration {
}
