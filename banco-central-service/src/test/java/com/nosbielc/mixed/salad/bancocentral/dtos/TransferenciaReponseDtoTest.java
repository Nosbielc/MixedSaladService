package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TransferenciaReponseDtoTest {

    private static final String STR = "str";
    private static final String STR_ATUALIZADO = "str-atualizado";
    private static final Float VLR_TRANSFERENCIA = 1.00f;
    private static final Float VLR_TRANSFERENCIA_ATUALIZADO = 1.50f;

    private TransferenciaReponseDto transferenciaReponseDto;
    private Transferencia transferencia;
    private Banco bancoOrigem, bancoDestino;
    private BancoDto bancoDtoOrigem, bancoDtoDestino;

    @Before
    public void initTest() {
        bancoOrigem = new Banco(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
        bancoDestino = new Banco(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
        bancoDtoOrigem = new BancoDto(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
        bancoDtoDestino = new BancoDto(Long.MIN_VALUE, STR, STR, Boolean.TRUE);
        transferencia =
                new Transferencia(STR, VLR_TRANSFERENCIA, bancoOrigem
                , STR, TransferenciaStatus.REQUESTED, bancoDestino, STR, new Date());
        transferencia.setId(Long.MIN_VALUE);
        transferenciaReponseDto = new TransferenciaReponseDto(transferencia);
    }

    @Test
    public void testTransferenciaReponseDto () {
        transferenciaReponseDto = new TransferenciaReponseDto();
        assertNotNull(transferenciaReponseDto);
    }

    @Test
    public void testTransferenciaReponseDtoComParametros() {
        transferenciaReponseDto =
                new TransferenciaReponseDto(Long.MIN_VALUE, STR, VLR_TRANSFERENCIA, new Date(),
                        bancoDtoOrigem, STR, TransferenciaStatus.PENDING,
                        bancoDtoDestino, STR);
        assertNotNull(transferenciaReponseDto);
    }

    @Test
    public void testToString() {
        String toString = transferenciaReponseDto.toString();
        assertEquals(
                toString,
                transferenciaReponseDto.toString()
        );
    }

    @Test
    public void getId() {
        assertTrue(Long.MIN_VALUE == transferenciaReponseDto.getId());
    }

    @Test
    public void setId() {
        transferenciaReponseDto.setId(1L);
        assertTrue(1L == transferenciaReponseDto.getId());
    }

    @Test
    public void getAutenticacao() {
        assertEquals(STR, transferenciaReponseDto.getAutenticacao());
    }

    @Test
    public void setAutenticacao() {
        transferenciaReponseDto.setAutenticacao(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, transferenciaReponseDto.getAutenticacao());
    }

    @Test
    public void getValorTransferencia() {
        assertTrue(VLR_TRANSFERENCIA == transferenciaReponseDto.getValorTransferencia());
    }

    @Test
    public void setValorTransferencia() {
        transferenciaReponseDto.setValorTransferencia(VLR_TRANSFERENCIA_ATUALIZADO);
        assertTrue(VLR_TRANSFERENCIA_ATUALIZADO == transferenciaReponseDto.getValorTransferencia());
    }

    @Test
    public void getDateTimeTransferencia() {
        assertNotNull(transferenciaReponseDto.getDateTimeTransferencia());
    }

    @Test
    public void setDateTimeTransferencia() {
        transferenciaReponseDto.setDateTimeTransferencia(null);
        assertNull(transferenciaReponseDto.getDateTimeTransferencia());
        transferenciaReponseDto.setDateTimeTransferencia(new Date());
        assertNotNull(transferenciaReponseDto.getDateTimeTransferencia());
    }

    @Test
    public void getBancoOrigem() {
        assertNotNull(transferenciaReponseDto.getBancoOrigem());
    }

    @Test
    public void setBancoOrigem() {
        transferenciaReponseDto.setBancoOrigem(null);
        assertNull(transferenciaReponseDto.getBancoOrigem());
        transferenciaReponseDto.setBancoOrigem(bancoDtoOrigem);
        assertNotNull(transferenciaReponseDto.getBancoOrigem());
    }

    @Test
    public void getContaOrigem() {
        assertEquals(STR, transferenciaReponseDto.getContaOrigem());
    }

    @Test
    public void setContaOrigem() {
        transferenciaReponseDto.setContaOrigem(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, transferenciaReponseDto.getContaOrigem());
    }

    @Test
    public void getTransferenciaStatus() {
        assertNotNull(transferenciaReponseDto.getTransferenciaStatus());
    }

    @Test
    public void setTransferenciaStatus() {
        transferenciaReponseDto.setTransferenciaStatus(TransferenciaStatus.PROCESSING_CONCLUDED);
        assertEquals(TransferenciaStatus.PROCESSING_CONCLUDED,
                transferenciaReponseDto.getTransferenciaStatus());
    }

    @Test
    public void getBancoDestino() {
        assertNotNull(transferenciaReponseDto.getBancoDestino());
    }

    @Test
    public void setBancoDestino() {
        transferenciaReponseDto.setBancoDestino(null);
        assertNull(transferenciaReponseDto.getBancoDestino());
        transferenciaReponseDto.setBancoDestino(bancoDtoDestino);
        assertNotNull(transferenciaReponseDto.getBancoDestino());
    }

    @Test
    public void getContaDestino() {
        assertEquals(STR, transferenciaReponseDto.getContaDestino());
    }

    @Test
    public void setContaDestino() {
        transferenciaReponseDto.setContaDestino(STR_ATUALIZADO);
        assertEquals(STR_ATUALIZADO, transferenciaReponseDto.getContaDestino());
    }
}