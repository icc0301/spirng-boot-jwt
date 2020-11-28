package com.xzm.springbootjwt.controller;

import com.xzm.springbootjwt.service.TokenService;
import com.xzm.springbootjwt.until.NologinRequire;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final TokenService tokenService;

    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/login")
    @NologinRequire
    public String login(){
        System.out.println("login");
        return tokenService.getToken(1,"123456");
    }
}
