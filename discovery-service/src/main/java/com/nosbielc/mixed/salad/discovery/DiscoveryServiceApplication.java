package com.nosbielc.mixed.salad.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

    public static void main(String[] args) throws Exception {
        //TimeUnit.SECONDS.sleep(30);
        SpringApplication.run(DiscoveryServiceApplication.class, args);
    }

}
