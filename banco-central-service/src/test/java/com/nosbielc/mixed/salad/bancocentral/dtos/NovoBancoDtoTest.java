package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class NovoBancoDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";

    private NovoBancoDto novoBancoDto;

    @Before
    public void initTest() {
        novoBancoDto = new NovoBancoDto(Long.MIN_VALUE, STR, STR);
    }

    @Test
    public void testNovoBancoDto() {
        novoBancoDto = new NovoBancoDto();
        assertTrue(Long.MIN_VALUE == novoBancoDto.getCodBanco());
    }

    @Test
    public void testNovoBancoDtoComParametros() {
        assertTrue(Long.MIN_VALUE == novoBancoDto.getCodBanco());
        assertTrue(STR.equalsIgnoreCase(novoBancoDto.getStrNomeBase()));
        assertTrue(STR.equalsIgnoreCase(novoBancoDto.getStrNome()));
    }

    @Test
    public void getCodBanco() {
        assertTrue(novoBancoDto.getCodBanco() == Long.MIN_VALUE);
    }

    @Test
    public void setCodBanco() {
        novoBancoDto.setCodBanco(1L);
        assertTrue(novoBancoDto.getCodBanco() == 1L);
    }

    @Test
    public void getStrNomeBase() {
        assertTrue(novoBancoDto.getStrNomeBase().equalsIgnoreCase(STR));
    }

    @Test
    public void setStrNomeBase() {
        novoBancoDto.setStrNomeBase(STR_ATUALIZADO);
        assertTrue(novoBancoDto.getStrNomeBase().equalsIgnoreCase(STR_ATUALIZADO));
    }

    @Test
    public void getStrNome() {
        assertTrue(novoBancoDto.getStrNome().equalsIgnoreCase(STR));
    }

    @Test
    public void setStrNome() {
        novoBancoDto.setStrNome(STR_ATUALIZADO);
        assertTrue(novoBancoDto.getStrNome().equalsIgnoreCase(STR_ATUALIZADO));
    }

    @Test
    public void testToString() {
        assertEquals(
                "NovoBancoDto[codBanco=-9223372036854775808, strNomeBase='str', strNome='str']",
                novoBancoDto.toString()
        );
    }
}
