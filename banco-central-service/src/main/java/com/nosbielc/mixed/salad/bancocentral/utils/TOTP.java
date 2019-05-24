package com.nosbielc.mixed.salad.bancocentral.utils;

import com.nosbielc.mixed.salad.bancocentral.utils.otp.TimeBasedOneTimePasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TOTP {

    private static final Logger log = LoggerFactory.getLogger(TOTP.class);

    public static void main(String... args) throws Exception {
        while (true)
            new TOTP().teste();
    }

    public void teste() throws Exception {
//        System.out.println(getToken());

        String s="bancounidos";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update( s.getBytes(), 0 , s.length() );

            byte[] digest = m.digest();

            String hexa = new BigInteger(1,digest).toString(120);

//            System.out.println("MD5: " + hexa);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }


        final TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator();

        final SecretKey secretKey;
        {
            String algoritimo = totp.getAlgorithm();
//            System.out.println("Algoritimo " + algoritimo);
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(algoritimo);

            // SHA-1 and SHA-256 prefer 64-byte (512-bit) keys; SHA512 prefers 128-byte (1024-bit) keys
            keyGenerator.init(512);

            secretKey = keyGenerator.generateKey();

        }

        String scrKeyUsado = SecretKeyUtils.secretKeytoString(secretKey);

//        System.out.format("scrKeyUsado: %s \n", scrKeyUsado);

        final Date now = new Date();
        final Date later = new Date(now.getTime() + totp.getTimeStep(TimeUnit.MILLISECONDS));

            int tokenA = totp.generateOneTimePassword(secretKey, now);
            int tokenB = totp.generateOneTimePassword(secretKey, later);

//            System.out.format("Current password 1: %06d\n", tokenA);
//            System.out.format("Future password:  %06d\n", tokenB);


            Thread.sleep(58000);

            final SecretKey secretKeyValidacao;
            {
                secretKeyValidacao = SecretKeyUtils.stringToScretKey(scrKeyUsado, totp.getAlgorithm());
            }

            String scrKeyUsadoValidacao = SecretKeyUtils.secretKeytoString(secretKey);

//            System.out.format("scrKeyUsadoValidacao: %s \n", scrKeyUsadoValidacao);

            final Date nowValidacao = new Date();
            final Date laterValidacao = new Date(nowValidacao.getTime() + totp.getTimeStep(TimeUnit.MILLISECONDS));

            int tokenAValidacao = totp.generateOneTimePassword(secretKeyValidacao, nowValidacao);
            int tokenBValidacao = totp.generateOneTimePassword(secretKeyValidacao, laterValidacao);

            LocalDateTime fromDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime newDateTime = nowValidacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//            System.out.format("Tempo Decorrido: %s \n", fromDateTime.until(newDateTime, ChronoUnit.SECONDS));
//            System.out.format("Current password 1: %06d\n", tokenAValidacao);
//            System.out.format("Future password:  %06d\n", tokenBValidacao);

            if(tokenA == tokenAValidacao || tokenA == tokenBValidacao ||
                    tokenB == tokenAValidacao || tokenB == tokenBValidacao) {
                System.out.println("Validado com sucesso");
            } else {
                System.out.println("Token Falso");
            }

    }

    public static String getToken() throws NoSuchAlgorithmException{

        String secretSeed = convertStringToHex("bancounidos123456789");
        String result = null;
        long timeWindow = 60L;
        long exactTime = System.currentTimeMillis()/1000L;
        long preRounded = (long)(exactTime/timeWindow);
        String roundedTime = Long.toHexString(preRounded).toUpperCase();

        while(roundedTime.length()<16){
            roundedTime = "0" + roundedTime;
        }

        byte[] hash = HMAC.hmac(hexStr2Bytes(secretSeed), hexStr2Bytes(roundedTime));
        int offset = hash[hash.length -1] & 0xf;
        int otp =
                ((hash[offset + 0] & 0x7f) << 24) |
                        ((hash[offset + 1] & 0xff) << 16) |
                        ((hash[offset + 2] & 0xff) << 8) |
                        (hash[offset + 3] & 0xff);
        otp = otp % 1000000;

        result = Integer.toString(otp);
        while(result.length()<6){
            result = "0"+result;
        }

        return result;
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

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

}