package com.nosbielc.mixed.salad.bancocentral.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class SecretKeyUtilsTest {

    private GoogleAuthenticator googleAuthenticator;
    private GoogleAuthenticatorKey googleAuthenticatorKey;

    @Before
    public void initTest() {
        googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticatorKey = googleAuthenticator.createCredentials();
    }

    @Test
    public void testSecretKeytoString() throws NoSuchAlgorithmException {
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        assertNotNull(SecretKeyUtils.secretKeytoString(secretKey));
    }

    @Test
    public void testStringToScretKey() {
        assertNotNull(SecretKeyUtils.stringToScretKey("test", "AES"));
    }

    @Test
    public void getKey() {
        assertNotNull(SecretKeyUtils.getKey());
    }

    @Test
    public void testIsCodeValid() {
        int code = googleAuthenticator.getTotpPassword(googleAuthenticatorKey.getKey());
        assertTrue(SecretKeyUtils.isCodeValid(googleAuthenticatorKey.getKey(), code));
    }

    @Test
    public void testIsCodeNotValid() {
        assertFalse(SecretKeyUtils.isCodeValid(googleAuthenticatorKey.getKey(), 12342));
    }

    @Test
    public void getHashTransferenciaSucesso() {
        assertNotNull(SecretKeyUtils.getHashTransferencia("t", "e", "s", "t", "MD5"));
    }

    @Test
    public void getHashTransferenciaErroTratado() {
        assertNotNull(SecretKeyUtils.getHashTransferencia("t", "e", "s", "t", "MD6"));
    }
}