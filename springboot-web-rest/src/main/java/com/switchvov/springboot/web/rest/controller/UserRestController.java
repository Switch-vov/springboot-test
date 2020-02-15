package com.switchvov.springboot.web.rest.controller;

import com.switchvov.springboot.web.rest.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author switch
 * @since 2020/2/8
 */
@RestController
public class UserRestController {
    @PostMapping(value = "/echo/user", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public User user(@RequestBody User user) {
        return user;
    }
}
