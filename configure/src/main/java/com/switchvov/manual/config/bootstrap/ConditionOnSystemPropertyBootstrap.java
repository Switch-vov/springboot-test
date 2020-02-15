package com.switchvov.manual.config.bootstrap;

import com.switchvov.manual.config.condition.ConditionOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author switch
 * @since 2020/2/6
 */
public class ConditionOnSystemPropertyBootstrap {

    @Bean
    @ConditionOnSystemProperty(name = "user.name", value = "switch")
    public String helloWorld() {
        return "hello world";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionOnSystemPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("out: " + helloWorld);
        context.close();
    }
}
