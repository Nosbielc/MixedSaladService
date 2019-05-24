package com.nosbielc.mixed.salad.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    @GetMapping("/user")
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
}
