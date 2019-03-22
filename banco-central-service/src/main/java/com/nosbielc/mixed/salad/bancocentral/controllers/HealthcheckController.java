package com.nosbielc.mixed.salad.bancocentral.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthcheckController.class);

    @GetMapping(value = "/healthcheck")
    @ResponseStatus(value = HttpStatus.OK)
    public void healthcheck() {
        logger.info("healthcheck called");
    }
}
