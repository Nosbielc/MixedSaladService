package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NovoBancoDtoTest {

    @Test
    public void testNovoBancoDto() {
        NovoBancoDto bancoDto = new NovoBancoDto();
        assertTrue(Long.MIN_VALUE == bancoDto.getCodBanco());
    }

    @Test
    public void testNovoBancoDtoComParametros() {
        NovoBancoDto bancoDto = new NovoBancoDto(Long.MIN_VALUE, "str", "str");
        assertTrue(Long.MIN_VALUE == bancoDto.getCodBanco());
        assertTrue("str".equalsIgnoreCase(bancoDto.getStrNomeBase()));
        assertTrue("str".equalsIgnoreCase(bancoDto.getStrNome()));
    }

}
