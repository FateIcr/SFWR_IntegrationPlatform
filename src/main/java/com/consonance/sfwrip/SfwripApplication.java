package com.consonance.sfwrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SfwripApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfwripApplication.class, args);
    }

}
