package com.switchvov.servlet.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author switch
 */
@SpringBootApplication
@ServletComponentScan("com.switchvov.servlet.test.servlet")
public class ServletTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletTestApplication.class, args);
    }

}
