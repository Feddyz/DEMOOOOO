package com.example.demooooo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demooooo.mapper")
@MapperScan("com.example.demooooo.mapper")
public class DemoooooApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoooooApplication.class, args);

    }

}
