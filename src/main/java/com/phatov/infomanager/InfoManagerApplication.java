package com.phatov.infomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.phatov.infomanager.controllers")
public class InfoManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfoManagerApplication.class, args);
    }
}
