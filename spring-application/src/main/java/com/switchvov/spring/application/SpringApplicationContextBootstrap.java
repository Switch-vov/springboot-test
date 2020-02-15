package com.switchvov.spring.application;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author switch
 * @since 2020/2/7
 */
@SpringBootApplication
public class SpringApplicationContextBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        System.out.println("ConfigurableApplicationContext 类型: " + context.getClass().getName());
        System.out.println("Environment 类型: " + context.getEnvironment().getClass().getName());

        // 关闭上下文
        context.close();
    }

}
