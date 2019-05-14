package com.nosbielc.mixed.salad.bancocentral;

import com.nosbielc.mixed.salad.bancocentral.componentes.ServerPortCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableCircuitBreaker
public class BancoCentralServiceApplication {

    public static void main(String[] args) {
        ServerPortCustomizer.setRandomPort();
        SpringApplication.run(BancoCentralServiceApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));
    }
}


//localhost:9000/bc/swagger-ui.html
