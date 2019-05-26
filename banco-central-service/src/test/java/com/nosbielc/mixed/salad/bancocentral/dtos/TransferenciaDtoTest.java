package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TransferenciaDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";
    private static final String VLR_TRANSFERENCIA = "1.00";
    private static final String VLR_TRANSFERENCIA_ATUALIZADO = "1.50";

    TransferenciaDto transferenciaDto;

    @Before
    public void initTest() {
        transferenciaDto = new TransferenciaDto();
        transferenciaDto.setContaDestino(STR);
        transferenciaDto.setContaOrigem(STR);
        transferenciaDto.setValorTransferencia(VLR_TRANSFERENCIA);
    }

    @Test
    public void testTransferenciaDto() {
        transferenciaDto = new TransferenciaDto();
        assertNotNull(transferenciaDto);
    }

    @Test
    public void testToString() {
        assertEquals(
                "TransferenciaDto[valorTransferencia='1.00', bancoOrigem=-9223372036854775808, " +
                        "contaOrigem='str', bancoDestino=-9223372036854775808, contaDestino='str', " +
                        "code=-9223372036854775808]",
                transferenciaDto.toString()
        );
    }

    @Test
    public void getValorTransferencia() {
        assertEquals(VLR_TRANSFERENCIA, transferenciaDto.getValorTransferencia());
    }

    @Test
    public void setValorTransferencia() {
        transferenciaDto.setValorTransferencia(VLR_TRANSFERENCIA_ATUALIZADO);
        assertEquals(VLR_TRANSFERENCIA_ATUALIZADO, transferenciaDto.getValorTransferencia());
    }

    @Test
    public void getBancoOrigem() {
        assertTrue(Long.MIN_VALUE == transferenciaDto.getBancoOrigem());
    }

    @Test
    public void setBancoOrigem() {
        transferenciaDto.setBancoOrigem(1L);
        assertTrue(1L == transferenciaDto.getBancoOrigem());
    }

    @Test
    public void getContaOrigem() {
        assertEquals(STR, transferenciaDto.getContaOrigem());
    }

    @Test
    public void setContaOrigem() {
        transferenciaDto.setContaOrigem(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, transferenciaDto.getContaOrigem());
    }

    @Test
    public void getBancoDestino() {
        assertTrue(Long.MIN_VALUE == transferenciaDto.getBancoDestino());
    }

    @Test
    public void setBancoDestino() {
        transferenciaDto.setBancoDestino(1L);
        assertTrue(1L == transferenciaDto.getBancoDestino());
    }

    @Test
    public void getContaDestino() {
        assertEquals(STR, transferenciaDto.getContaDestino());
    }

    @Test
    public void setContaDestino() {
        transferenciaDto.setContaDestino(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, transferenciaDto.getContaDestino());
    }

    @Test
    public void getCode() {
        assertTrue(Long.MIN_VALUE == transferenciaDto.getCode());
    }

    @Test
    public void setCode() {
        transferenciaDto.setCode(1L);
        assertTrue(1L == transferenciaDto.getCode());
    }
}