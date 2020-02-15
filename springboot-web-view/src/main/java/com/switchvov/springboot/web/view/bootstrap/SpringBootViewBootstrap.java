package com.switchvov.springboot.web.view.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author switch
 * @since 2020/2/8
 */
@SpringBootApplication(scanBasePackages = "com.switchvov.springboot.web.view")
public class SpringBootViewBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootViewBootstrap.class, args);
    }
}
