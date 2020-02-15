package com.switchvov.springboot.web.mvc.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author switch
 * @since 2020/2/7
 */
@SpringBootApplication(scanBasePackages = "com.switchvov.springboot.web.mvc")
public class SpringBootWebMvcBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcBootstrap.class, args);
    }
}
