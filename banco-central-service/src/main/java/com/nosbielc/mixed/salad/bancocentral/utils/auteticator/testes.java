package com.nosbielc.mixed.salad.bancocentral.utils.auteticator;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class testes {

    public static void main(String[] args) throws InterruptedException {
        //Gerando autenticação
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        System.out.println("KeyVerification: " + key.getVerificationCode());
        System.out.println("Key: " + key.getKey());

        //Checando token
//        boolean isCodeValid = gAuth.authorize("NUWSNYXVSG67KUDI", 190631);
//        System.out.println("Valido ? " + isCodeValid);

        while (true) {
            int code = gAuth.getTotpPassword(key.getKey());
            boolean isCodeValidTest = gAuth.authorize(key.getKey(), code);
            System.out.println("Codigo Gerado pelo app ? " + code);
            System.out.println("Valido ? " + isCodeValidTest);
            Thread.sleep(90000);
        }

    }


}
