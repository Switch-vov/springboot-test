package com.switchvov.springboot.web.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author switch
 * @since 2020/2/8
 */
@RestController
public class HelloWorldRestController {

    @GetMapping("/hello-world")
    public String helloWorld(@RequestParam(required = false, defaultValue = "") String message) {
        return "Hello World " + message;
    }

//    @CrossOrigin("*")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
