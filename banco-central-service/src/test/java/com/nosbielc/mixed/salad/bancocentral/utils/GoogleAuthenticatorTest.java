package com.nosbielc.mixed.salad.bancocentral.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class GoogleAuthenticatorTest {

    private static final Logger log = LoggerFactory.getLogger(GoogleAuthenticatorTest.class);

    private GoogleAuthenticator googleAuthenticator;
    private GoogleAuthenticatorKey googleAuthenticatorKey;

    @Before
    public void initTest() {
        googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticatorKey = googleAuthenticator.createCredentials();
        log.info("KeyVerification: {}", googleAuthenticatorKey.getVerificationCode());
        log.info("Key: {}", googleAuthenticatorKey.getKey());
    }

    @Test
    public void testGoogleAuthenticatorSucesso() {
        int code = googleAuthenticator.getTotpPassword(googleAuthenticatorKey.getKey());
        log.info("Codigo Gerado pelo app ? {} ", code);
        assertTrue(googleAuthenticator.authorize(googleAuthenticatorKey.getKey(), code));
    }

    @Test
    public void testGoogleAuthenticatorErro() {
        int code = 121212;
        log.info("Codigo Gerado pelo app ? {} ", code);
        assertFalse(googleAuthenticator.authorize(googleAuthenticatorKey.getKey(), code));
    }

}
