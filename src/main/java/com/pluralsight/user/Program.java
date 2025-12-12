package com.pluralsight.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pluralsight")
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }
}
