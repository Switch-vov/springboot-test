package com.switchvov.manual.config.configuration;

import org.springframework.context.annotation.Bean;

/**
 * @author switch
 * @since 2020/2/6
 */
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() { // 方法名即 Bean 名称
        return "Hello,World 2018";
    }

}
