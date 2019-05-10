package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.nosbielc.mixed.salad.bancocentral.integration.CircuitBreakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerConroller {

    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerConroller.class);


    @Autowired
    CircuitBreakerService circuitBreakerService;

    @GetMapping(value = "/circuitBreaker")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> circuitBreaker() {
        logger.info("circuitBreaker called");

        return ResponseEntity.ok(this.circuitBreakerService.reading());
    }

}
