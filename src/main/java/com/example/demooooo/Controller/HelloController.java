package com.example.demooooo.Controller;

import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.util.*;
import org.apache.commons.*;
import com.example.demooooo.utility.SMSUtil;
import com.example.demooooo.vo.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


public class HelloController {

    @RequestMapping("/test")
    public String test1(){
        return "Hello spring-boot";
    }

    @GetMapping("/sendSMS")
    public ResponseEntity<Result>SendSMS(String phone){
        String code =

    }
}
