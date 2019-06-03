package com.nosbielc.mixed.salad.bancocentral.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MonetarioTest {

    @Test
    public void converte() throws ParseException {
        String valor = "9,99";
        assertEquals(9.99, Monetario.converte(valor), 0);
    }

    @Test
    public void converteToBig() throws ParseException {
        BigDecimal bigDecimal = new BigDecimal("9.99");
        assertEquals(bigDecimal, Monetario.converteToBig("9,99"));
    }
}