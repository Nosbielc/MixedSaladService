package com.nosbielc.mixed.salad.bancocentral.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CircuitBreakerService {

    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "reliable")
    public String reading() {
        URI uri = URI.create("http://localhost:99999/recomendado");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "CircuitBreaker Executado, mostrando uma mensagem padrão";
    }
}
