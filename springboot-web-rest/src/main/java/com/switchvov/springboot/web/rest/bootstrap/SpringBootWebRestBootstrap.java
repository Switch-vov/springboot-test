package com.switchvov.springboot.web.rest.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author switch
 * @since 2020/2/8
 */
@SpringBootApplication(scanBasePackages = "com.switchvov.springboot.web.rest")
public class SpringBootWebRestBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebRestBootstrap.class, args);
    }
}
