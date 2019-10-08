package com.nosbielc.mixed.salad.bancoold.componentes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ServerLauncherCustomizer {

    private static final Logger log = LoggerFactory.getLogger(ServerLauncherCustomizer.class);

    @Value("${spring.profiles.active}")
    private static String activeProfile;

    public static void checkProfilesActive() throws InterruptedException {

        switch (activeProfile) {
            case "dev":
                log.info(" - - - - - - - Aplicação em modo DEV - - - - - - - ");
                break;
            case "prod, native":
            case "prod":
                log.info(" - - - - - - - Aplicação em modo PROD - - - - - - - ");
                TimeUnit.SECONDS.sleep(30);
                ServerPortCustomizer.setRandomPort(); // comentado para evitar erro de portas no docker-compose
                break;
            default:
                log.info(" - - - - - - - Aplicação em modo DESCONHECIDO - - - - - - - ");
        }

    }

    public void check() {
        //ainda em desemvolvimento
    }

}
