package com.nosbielc.mixed.salad.bancocentral.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Monetario {

    private Monetario() {
        throw new IllegalStateException("Monetario Class");
    }

    public static double converte(String arg) throws ParseException {
        //obtem um NumberFormat para o Locale default (BR)
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        //converte um número com vírgulas ex: 2,56 para double
        return nf.parse(arg).doubleValue();
    }

    public static BigDecimal converteToBig(String arg) throws ParseException {
        return BigDecimal.valueOf(converte(arg)).setScale(2, RoundingMode.HALF_EVEN);
    }

}
