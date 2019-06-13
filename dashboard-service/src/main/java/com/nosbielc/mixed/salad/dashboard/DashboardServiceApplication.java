package com.nosbielc.mixed.salad.dashboard;

import be.ordina.msdashboard.EnableMicroservicesDashboardServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMicroservicesDashboardServer
public class DashboardServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        SpringApplication.run(DashboardServiceApplication.class, args);
    }

}
