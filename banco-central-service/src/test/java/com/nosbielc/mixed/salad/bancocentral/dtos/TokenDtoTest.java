package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TokenDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";

    TokenDto tokenDto;
    Banco banco;

    @Before
    public void initTest() {
        banco = new Banco(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
        tokenDto = new TokenDto(Long.MIN_VALUE, STR, STR, banco);
    }

    @Test
    public void testTokenDto() {
        tokenDto = new TokenDto();
        assertNotNull(tokenDto);
    }

    @Test
    public void testTokenDtoConstrutorBasico() {
        tokenDto = new TokenDto(Long.MIN_VALUE, STR, STR);
        assertNotNull(tokenDto);
    }

    @Test
    public void testTokenDtoComParametros() {
        assertTrue(Long.MIN_VALUE == tokenDto.getId());
        assertEquals(STR, tokenDto.getStrToken());
        assertEquals(STR, tokenDto.getStrConta());
    }

    @Test
    public void testTokenDtoComOBanco() {
        assertTrue(Long.MIN_VALUE == tokenDto.getId());
        assertEquals(STR, tokenDto.getStrToken());
        assertEquals(STR, tokenDto.getStrConta());
        assertTrue(tokenDto.getBanco() != null);
    }

    @Test
    public void getId() {
        assertTrue(Long.MIN_VALUE == tokenDto.getId());
    }

    @Test
    public void setId() {
        tokenDto.setId(1L);
        assertTrue(tokenDto.getId() == 1L);
    }

    @Test
    public void getStrToken() {
        assertEquals(STR, tokenDto.getStrToken());
    }

    @Test
    public void setStrToken() {
        tokenDto.setStrToken(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, tokenDto.getStrToken());
    }

    @Test
    public void getStrConta() {
        assertEquals(STR, tokenDto.getStrConta());
    }

    @Test
    public void setStrConta() {
        tokenDto.setStrConta(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, tokenDto.getStrConta());
    }

    @Test
    public void getBanco() {
        assertNotNull(tokenDto.getBanco());
    }

    @Test
    public void setBanco() {
        tokenDto.setBanco(tokenDto.getBanco());
        assertNotNull(tokenDto.getBanco());
    }
}