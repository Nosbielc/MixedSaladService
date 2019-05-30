package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TokenSegurancaServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TransferenciaServiceImpl;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"eureka.client.enabled:false"})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransferenciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BancoServiceImpl bancoService;

    @MockBean
    private TokenSegurancaServiceImpl tokenSegurancaService;

    @MockBean
    private TransferenciaServiceImpl transferenciaService;

    private static final String URL_BASE = "/api/v1/transferencia";
    private static final Long ID_BANCO = 1L;
    private static final String NOME_BASE = "CENTRAL";
    private static final String NOME = "BANCO-CENTRAL";

    Transferencia transferencia;
    Banco bancoOrigem;
    Banco bancoDestino;

    @Before
    public void initTest() {
        bancoOrigem = new Banco(ID_BANCO, NOME_BASE, NOME, Boolean.TRUE);
        bancoOrigem.setId(ID_BANCO);

        bancoDestino = new Banco(ID_BANCO, NOME_BASE, NOME, Boolean.TRUE);
        bancoDestino.setId(ID_BANCO);

        transferencia = new Transferencia(
                "xxxxxxxx", 120.99f, bancoOrigem,
                "00000000000", TransferenciaStatus.PROCESSING_CONCLUDED,
                bancoDestino, "11111111111", new Date());
        transferencia.setId(1L);
    }

    @Test
    @WithMockUser
    public void testListarTransferenciasSemFiltros() throws Exception {
        Page<Transferencia> transferenciaPage = new PageImpl<>(Arrays.asList(transferencia));

        BDDMockito.given(this.transferenciaService.findAllPageable(Mockito.any())).willReturn(transferenciaPage);

        mvc.perform(MockMvcRequestBuilders.get(URL_BASE.concat("?pag=0&ord=id&dir=ASC"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].id").value(transferencia.getId()))
                .andExpect(jsonPath("$.data.content[0].autenticacao").value(transferencia.getAutenticacao()))
                .andExpect(jsonPath("$.data.content[0].valorTransferencia").value(transferencia.getValorTransferencia()))
                .andExpect(jsonPath("$.data.content[0].dateTimeTransferencia").value(transferencia.getDateTimeTransferencia()))
                .andExpect(jsonPath("$.data.content[0].contaDestino").value(transferencia.getContaDestino()))
                .andExpect(jsonPath("$.data.content[0].contaOrigem").value(transferencia.getContaOrigem()))
                .andExpect(jsonPath("$.data.content[0].bancoOrigem").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.data.content[0].bancoDestino").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    @WithMockUser
    public void detalhe() {
    }

    @Test
    @WithMockUser
    public void criar() {
    }
}