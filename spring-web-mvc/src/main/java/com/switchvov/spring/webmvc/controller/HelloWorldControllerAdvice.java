package com.switchvov.spring.webmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author switch
 * @since 2020/2/7
 */
@ControllerAdvice(assignableTypes = HelloWorldController.class)
public class HelloWorldControllerAdvice {

    @ModelAttribute("acceptLanguage")
    public String acceptLanguage(@RequestHeader("Accept-Language") String acceptLanguage) {
        return acceptLanguage;
    }

    @ModelAttribute("jSessionId")
    public String jSessionId(@CookieValue("JSESSIONID") String jSessionId) {
        return jSessionId;
    }

    @ModelAttribute("message")
    public String message() {
        return "Hello World";
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> onException(Throwable throwable) {
        return ResponseEntity.ok(throwable.getMessage());
    }
}
