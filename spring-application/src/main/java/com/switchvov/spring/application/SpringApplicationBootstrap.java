package com.switchvov.spring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

/**
 * @author switch
 */
@SpringBootApplication
public class SpringApplicationBootstrap {

    public static void main(String[] args) {
//        SpringApplication.run(SpringBootApplication.class, args);
        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(Collections.singleton(SpringApplicationBootstrap.class.getName()));
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);

//        System.out.println(context.getBean(SpringBootApplication.class));
    }


}
