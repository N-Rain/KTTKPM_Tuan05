package com.example.kttkpm_8_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.kttkpm_8_3.service")
public class Kttkpm83Application {

    public static void main(String[] args) {
        SpringApplication.run(Kttkpm83Application.class, args);
    }

}
