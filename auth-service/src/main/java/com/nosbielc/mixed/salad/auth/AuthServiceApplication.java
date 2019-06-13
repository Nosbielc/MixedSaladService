package com.nosbielc.mixed.salad.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
