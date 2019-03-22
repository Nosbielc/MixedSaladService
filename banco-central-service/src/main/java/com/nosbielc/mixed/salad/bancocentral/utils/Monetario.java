package com.nosbielc.mixed.salad.bancocentral.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Monetario {

    public static double converte(String arg) throws Exception {
        //obtem um NumberFormat para o Locale default (BR)
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        //converte um número com vírgulas ex: 2,56 para double
        double number = nf.parse(arg).doubleValue();
        return number;
    }

    public static BigDecimal converteToBig(String arg) throws Exception  {
        return new BigDecimal(converte(arg)).setScale(2, RoundingMode.HALF_EVEN);
    }

}
