package com.nosbielc.mixed.salad.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

}
