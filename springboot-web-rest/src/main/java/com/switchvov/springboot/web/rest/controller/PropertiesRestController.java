package com.switchvov.springboot.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

/**
 * @author switch
 * @since 2020/2/8
 */
@Controller
public class PropertiesRestController {
    @PostMapping(value = "/add/props", consumes = "text/properties;charset=UTF-8")
    @ResponseBody
    public Properties addUser(@RequestBody Properties properties) {
        return properties;
    }

    @PostMapping(value = "/add/props/2", consumes = "text/properties;charset=UTF-8")
    public Properties addUser2(Properties properties) {
        return properties;
    }
}
