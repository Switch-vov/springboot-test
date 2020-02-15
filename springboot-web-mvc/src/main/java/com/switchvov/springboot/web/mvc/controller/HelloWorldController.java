package com.switchvov.springboot.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author switch
 * @since 2020/2/7
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index(@RequestParam int value, Model model) {
//        model.addAttribute("acceptLanguage", acceptLanguage);
//        model.addAttribute("jSessionId", jSessionId);
//        model.addAttribute("message", "Hello World");
        return "index";
    }
}
