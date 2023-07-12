package com.example.demooooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class DemoooooApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoooooApplication.class, args);

    }

}
