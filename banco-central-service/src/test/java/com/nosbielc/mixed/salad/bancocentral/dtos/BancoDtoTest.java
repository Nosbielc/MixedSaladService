package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class BancoDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";

    private BancoDto bancoDto;
    private Banco banco;

    @Before
    public void initTest() {
        bancoDto = new BancoDto(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
        banco = new Banco(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
    }

    @Test
    public void testBancoDto() {
        BancoDto bancoDto = new BancoDto();
        assertTrue(Long.MIN_VALUE == bancoDto.getCodBanco());
    }

    @Test
    public void testBancoDtoComParametros() {
        assertTrue(Long.MIN_VALUE == bancoDto.getCodBanco());
        assertTrue(STR.equalsIgnoreCase(bancoDto.getStrNomeBase()));
        assertTrue(STR.equalsIgnoreCase(bancoDto.getStrNome()));
        assertTrue(bancoDto.getAtivo());
    }

    @Test
    public void testBancoDtoComOProprioObjeto() {

        BancoDto bancoDtoFull = new BancoDto(banco);
        assertTrue(Long.MIN_VALUE == bancoDtoFull.getCodBanco());
        assertTrue("str".equalsIgnoreCase(bancoDtoFull.getStrNomeBase()));
        assertTrue("str".equalsIgnoreCase(bancoDtoFull.getStrNome()));
        assertTrue(bancoDtoFull.getAtivo());
    }

    @Test
    public void testToString() {
        assertEquals(
                "BancoDto[id=null, codBanco=-9223372036854775808, strNomeBase='str', strNome='str', ativo=true]",
                bancoDto.toString()
        );
    }

    @Test
    public void getId() {
        banco.setId(Long.MIN_VALUE);
        bancoDto = new BancoDto(banco);
        assertTrue(bancoDto.getId() == Long.MIN_VALUE);
    }

    @Test
    public void setId() {
        banco.setId(Long.MIN_VALUE);
        bancoDto = new BancoDto(banco);
        bancoDto.setId(1L);
        assertTrue(bancoDto.getId() == 1L);
    }

    @Test
    public void getCodBanco() {
        assertTrue(bancoDto.getCodBanco() == Long.MIN_VALUE);
    }

    @Test
    public void setCodBanco() {
        bancoDto.setCodBanco(1L);
        assertTrue(bancoDto.getCodBanco() == 1L);
    }

    @Test
    public void getStrNomeBase() {
        assertTrue(bancoDto.getStrNomeBase().equalsIgnoreCase(STR));
    }

    @Test
    public void setStrNomeBase() {
        bancoDto.setStrNomeBase(STR_ATUALIZADO);
        assertTrue(bancoDto.getStrNomeBase().equalsIgnoreCase(STR_ATUALIZADO));
    }

    @Test
    public void getStrNome() {
        assertTrue(bancoDto.getStrNome().equalsIgnoreCase(STR));
    }

    @Test
    public void setStrNome() {
        bancoDto.setStrNome(STR_ATUALIZADO);
        assertTrue(bancoDto.getStrNome().equalsIgnoreCase(STR_ATUALIZADO));
    }

    @Test
    public void getAtivo() {
        assertTrue(bancoDto.getAtivo());
    }

    @Test
    public void setAtivo() {
        bancoDto.setAtivo(Boolean.FALSE);
        assertFalse(bancoDto.getAtivo());
    }
}
