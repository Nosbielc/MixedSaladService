package com.nosbielc.mixed.salad.bancocentral.utils.auteticator;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testes {

    private static final Logger log = LoggerFactory.getLogger(Testes.class);
    private static Boolean isHabilitarLoop = Boolean.TRUE;

    public static void main(String[] args) throws InterruptedException {
        //Gerando autenticação
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        log.info("KeyVerification: {}", key.getVerificationCode());
        log.info("Key: {}", key.getKey());

        while (isHabilitarLoop) {
            int code = gAuth.getTotpPassword(key.getKey());
            boolean isCodeValidTest = gAuth.authorize(key.getKey(), code);
            log.info("Codigo Gerado pelo app ? {} ", code);
            log.info("Valido ? {} ", isCodeValidTest);
            Thread.sleep(90000);
        }

    }


}
