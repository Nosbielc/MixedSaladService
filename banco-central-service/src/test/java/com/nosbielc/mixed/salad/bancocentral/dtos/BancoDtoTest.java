package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BancoDtoTest {

    @Test
    public void testNovoBancoDto() {
        BancoDto bancoDto = new BancoDto();
        assertTrue(Long.MIN_VALUE == bancoDto.getCodBanco());
    }

    @Test
    public void testNovoBancoDtoComParametros() {
        BancoDto bancoDto = new BancoDto(Long.MIN_VALUE, "str", "str", Boolean.TRUE);
        assertTrue(Long.MIN_VALUE == bancoDto.getCodBanco());
        assertTrue("str".equalsIgnoreCase(bancoDto.getStrNomeBase()));
        assertTrue("str".equalsIgnoreCase(bancoDto.getStrNome()));
        assertTrue(bancoDto.getAtivo());
    }

    @Test
    public void testNovoBancoDtoComOProprioObjeto() {
        Banco banco = new Banco(Long.MIN_VALUE, "str", "str", Boolean.TRUE);
        BancoDto bancoDtoFull = new BancoDto(banco);
        assertTrue(Long.MIN_VALUE == bancoDtoFull.getCodBanco());
        assertTrue("str".equalsIgnoreCase(bancoDtoFull.getStrNomeBase()));
        assertTrue("str".equalsIgnoreCase(bancoDtoFull.getStrNome()));
        assertTrue(bancoDtoFull.getAtivo());
    }
}
