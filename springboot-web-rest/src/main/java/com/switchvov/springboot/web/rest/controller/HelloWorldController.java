package com.switchvov.springboot.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author switch
 * @since 2020/2/8
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

}
