package com.nosbielc.mixed.salad.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipkinServiceApplication {

    public static void main(String[] args) throws Exception {
        //TimeUnit.SECONDS.sleep(30);
        SpringApplication.run(ZipkinServiceApplication.class, args);
    }

}
