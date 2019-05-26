package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class NovoTokenDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";

    NovoTokenDto novoTokenDto;

    @Before
    public void initTest() {
        novoTokenDto = new NovoTokenDto(STR, Long.MIN_VALUE);
    }

    @Test
    public void testNovoTokenDtoComParametros() {
        assertTrue(Long.MIN_VALUE == novoTokenDto.getBancoId());
        assertTrue("str".equalsIgnoreCase(novoTokenDto.getStrConta()));
    }

    @Test
    public void testToString() {
        assertEquals(
                "NovoTokenDto[strConta='str', bancoId=-9223372036854775808]",
                novoTokenDto.toString()
        );
    }

    @Test
    public void getStrConta() {
        assertEquals(STR, novoTokenDto.getStrConta());
    }

    @Test
    public void setStrConta() {
        novoTokenDto.setStrConta(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, novoTokenDto.getStrConta());
    }

    @Test
    public void getBancoId() {
        assertTrue(Long.MIN_VALUE == novoTokenDto.getBancoId());
    }

    @Test
    public void setBancoId() {
        novoTokenDto.setBancoId(1L);
        assertTrue(1L == novoTokenDto.getBancoId());
    }
}
