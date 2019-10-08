package com.nosbielc.mixed.salad.bancoold;

import com.nosbielc.mixed.salad.bancoold.componentes.ServerPortCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableCircuitBreaker
public class BancoOldServiceApplication {

    public static void main(String[] args) throws InterruptedException {
//        TimeUnit.MINUTES.sleep(1);
        ServerPortCustomizer.setRandomPort(); // comentado para evitar erro de portas no docker-compose
        SpringApplication.run(BancoOldServiceApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));
    }

}

//localhost:PORT/bc/swagger-ui.html
