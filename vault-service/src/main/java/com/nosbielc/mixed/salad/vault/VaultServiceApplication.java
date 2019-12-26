package com.nosbielc.mixed.salad.vault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class VaultServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        SpringApplication.run(VaultServiceApplication.class, args);
    }

}
