package com.example.demooooo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RestController
    public class HelloController {

        @RequestMapping("/test")
        public String test(){
            return "Hello spring-boot";
        }
    }
}
