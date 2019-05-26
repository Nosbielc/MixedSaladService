package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ValidaTokenDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";

    private ValidaTokenDto validaTokenDto;

    @Before
    public void initTest(){
        validaTokenDto = new ValidaTokenDto(STR, Long.MIN_VALUE, Long.MIN_VALUE);
    }

    @Test
    public void testValidaToken() {
        assertNotNull(validaTokenDto);
        assertTrue(validaTokenDto.getBancoId() == Long.MIN_VALUE);
        assertTrue(validaTokenDto.getCode() == Long.MIN_VALUE);
        assertTrue(validaTokenDto.getStrConta() == STR);
    }

    @Test
    public void testToString()
    {
        assertEquals(
                "ValidaTokenDto[strConta='str', bancoId=-9223372036854775808, code=-9223372036854775808]",
                validaTokenDto.toString());
    }

    @Test
    public void getStrConta() {
        assertTrue(validaTokenDto.getStrConta().equalsIgnoreCase(STR));
    }

    @Test
    public void setStrConta() {
        validaTokenDto.setStrConta(STR_ATUALIZADO);
        assertTrue(validaTokenDto.getStrConta().equalsIgnoreCase(STR_ATUALIZADO));
    }

    @Test
    public void getBancoId() {
        assertTrue(validaTokenDto.getBancoId() == Long.MIN_VALUE);
    }

    @Test
    public void setBancoId() {
        validaTokenDto.setBancoId(1L);
        assertTrue(validaTokenDto.getBancoId() == 1L);
    }

    @Test
    public void getCode() {
        assertTrue(validaTokenDto.getCode() == Long.MIN_VALUE);
    }

    @Test
    public void setCode() {
        validaTokenDto.setCode(2L);
        assertTrue(validaTokenDto.getCode() == 2L);
    }
}