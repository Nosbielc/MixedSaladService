package com.nosbielc.mixed.salad.bancocentral.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.StringJoiner;

public class SecretKeyUtils {

    public static String secretKeytoString(SecretKey secretKey) {
        /**
         *  SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
         */
        // get base64 encoded version of the key
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }


    public static SecretKey stringToScretKey(String encodedKey, String algorithm) {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        // rebuild key using SecretKeySpec
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);
    }

    public static String getKey() {
        return new GoogleAuthenticator().createCredentials().getKey();
    }

    public static boolean isCodeValid(String key, int code) {
        return new GoogleAuthenticator().authorize(key, code);
    }

    public static String getHashTransferencia(String key1, String key2, String key3, String key4) {
        String s =
                new StringJoiner(":","","")
                        .add(key1)
                        .add(key4)
                        .add(key2)
                        .add(key4)
                        .add(key3)
                        .toString();
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update( s.getBytes(), 0 , s.length() );
            byte[] digest = m.digest();
            return new BigInteger(1,digest).toString(120);
        } catch (Exception e) {
            return String.valueOf(System.currentTimeMillis() + ":" +System.currentTimeMillis());
        }
    }

    private SecretKeyUtils() {
    }
}
