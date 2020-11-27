package com.xzm.springbootjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public void index(){
        System.out.println("1111111111");
    }

    @GetMapping("/test")
    public void test(){
        System.out.println("2222222222");
    }
}
