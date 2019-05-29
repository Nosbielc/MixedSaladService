package com.nosbielc.mixed.salad.bancocentral.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class TOTP {

    private TOTP() {
        // Construtor
    }

    public static String getToken() throws NoSuchAlgorithmException{

        String secretSeed = convertStringToHex("bancounidos123456789");
        StringBuilder result = new StringBuilder();
        long timeWindow = 60L;
        long exactTime = System.currentTimeMillis()/1000L;
        long preRounded = (exactTime/timeWindow);
        StringBuilder roundedTime = new StringBuilder().append(Long.toHexString(preRounded).toUpperCase());

        while(roundedTime.length()<16){
            roundedTime = new StringBuilder("0").append(roundedTime);
        }

        byte[] hash = HMAC.hmac(hexStr2Bytes(secretSeed), hexStr2Bytes(roundedTime.toString()));
        int offset = hash[hash.length -1] & 0xf;
        int otp =
                ((hash[offset + 0] & 0x7f) << 24) |
                        ((hash[offset + 1] & 0xff) << 16) |
                        ((hash[offset + 2] & 0xff) << 8) |
                        (hash[offset + 3] & 0xff);
        otp = otp % 1000000;

        result.append(Integer.toString(otp));
        while(result.length()<6){
            result = new StringBuilder("0").append(result);
        }

        return result.toString();
    }

    private static byte[] hexStr2Bytes(String hex){//Responsavel por Converter HEX em Bytes
        byte[] bArray = new BigInteger("10" + hex,16).toByteArray();
        byte[] ret = new byte[bArray.length - 1];
        for (int i = 0; i < ret.length; i++)
            ret[i] = bArray[i+1];
        return ret;
    }
    public static String convertStringToHex(String str){//Responsavel por converter String para HEX

        char[] chars = str.toCharArray();

        StringBuilder hex = new StringBuilder();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

}