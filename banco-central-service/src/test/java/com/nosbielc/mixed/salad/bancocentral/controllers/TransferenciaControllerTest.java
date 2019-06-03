package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoTokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TokenSegurancaServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TransferenciaServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.utils.SecretKeyUtils;
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
import java.util.Optional;

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
    private static final String AUTENTICACAO = "xxxxxxxx";
    private static final Long ID_TOKEN = 1L;
    private static final String TOKEN = "10909990987878";
    private static final String CONTA = "00000000000";

    Transferencia transferencia;
    Banco bancoOrigem;
    Banco bancoDestino;
    TokenSeguranca tokenSeguranca;

    @Before
    public void initTest() {
        bancoOrigem = new Banco(ID_BANCO, NOME_BASE, NOME, Boolean.TRUE);
        bancoOrigem.setId(ID_BANCO);

        bancoDestino = new Banco(ID_BANCO, NOME_BASE, NOME, Boolean.TRUE);
        bancoDestino.setId(ID_BANCO);

        transferencia = new Transferencia(
                AUTENTICACAO, 120.99f, bancoOrigem,
                "00000000000", TransferenciaStatus.PROCESSING_CONCLUDED,
                bancoDestino, "11111111111", new Date());
        transferencia.setId(1L);

        tokenSeguranca = new TokenSeguranca(SecretKeyUtils.getKey(), CONTA,  bancoOrigem);
        tokenSeguranca.setId(ID_TOKEN);
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
    public void testListarTransferenciasComAutenticacao() throws Exception {
        Page<Transferencia> transferenciaPage = new PageImpl<>(Arrays.asList(transferencia));

        BDDMockito.given(this.transferenciaService.findOneAutenticacao(Mockito.any(), Mockito.any())).willReturn(transferenciaPage);

        mvc.perform(MockMvcRequestBuilders.get(URL_BASE.concat("?pag=0&ord=id&dir=ASC&autenticacao=").concat(AUTENTICACAO))
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
    public void testListarTransferenciasPorId() throws Exception {
        Optional<Transferencia> transferenciaOptional = Optional.of(transferencia);

        BDDMockito.given(this.transferenciaService.findById(Mockito.anyLong())).willReturn(transferenciaOptional);

        mvc.perform(MockMvcRequestBuilders.get(URL_BASE.concat("?pag=0&ord=id&dir=ASC&id=1"))
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
    public void testDetalhePorAutenticacao() throws Exception {
        Optional<Transferencia> transferenciaOptional = Optional.of(transferencia);

        BDDMockito.given(
                this.transferenciaService.findByAutenticacao(Mockito.anyString())).willReturn(transferenciaOptional);

        mvc.perform(MockMvcRequestBuilders.get(URL_BASE.concat("/minhaautenticacao"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.objects.id").value(transferencia.getId()))
                .andExpect(jsonPath("$.data.objects.autenticacao").value(transferencia.getAutenticacao()))
                .andExpect(jsonPath("$.data.objects.valorTransferencia").value(transferencia.getValorTransferencia()))
                .andExpect(jsonPath("$.data.objects.dateTimeTransferencia").value(transferencia.getDateTimeTransferencia()))
                .andExpect(jsonPath("$.data.objects.contaDestino").value(transferencia.getContaDestino()))
                .andExpect(jsonPath("$.data.objects.contaOrigem").value(transferencia.getContaOrigem()))
                .andExpect(jsonPath("$.data.objects.bancoOrigem").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.data.objects.bancoDestino").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.errors").isEmpty());

    }

    @Test
    @WithMockUser
    public void testCriarTransferenciaSucesso() throws Exception {

        Optional<Banco> bancoContext = Optional.of(bancoOrigem);
        Optional<TokenSeguranca> tokenContext = Optional.of(tokenSeguranca);

        BDDMockito.given(this.bancoService.findById(Mockito.any())).willReturn(bancoContext);
        BDDMockito.given(this.tokenSegurancaService.findByBancoAndStrConta(Mockito.any(Banco.class), Mockito.anyString()))
                .willReturn(tokenContext);
        BDDMockito.given(this.transferenciaService.persist(Mockito.any(Transferencia.class))).willReturn(transferencia);

        TransferenciaDto transferenciaDto = new TransferenciaDto();
        transferenciaDto.setContaDestino(transferencia.getContaDestino());
        transferenciaDto.setContaOrigem(transferencia.getContaOrigem());
        transferenciaDto.setValorTransferencia(transferencia.getValorTransferencia().toString());

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                .content(obterJsonTransferenciaDtoParaRequisicaoPost(transferenciaDto))
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.objects.id").value(ID_TOKEN))
                .andExpect(jsonPath("$.data.objects.autenticacao").value(transferencia.getAutenticacao()))
                .andExpect(jsonPath("$.data.objects.valorTransferencia").value(transferencia.getValorTransferencia()))
                .andExpect(jsonPath("$.data.objects.dateTimeTransferencia").value(transferencia.getDateTimeTransferencia()))
                .andExpect(jsonPath("$.data.objects.contaOrigem").value(transferencia.getContaOrigem()))
                .andExpect(jsonPath("$.data.objects.contaDestino").value(transferencia.getContaDestino()))
                .andExpect(jsonPath("$.data.objects.contaDestino").value(transferencia.getContaDestino()))
                .andExpect(jsonPath("$.data.objects.transferenciaStatus").value(transferencia.getTransferenciaStatus().name()))
                .andExpect(jsonPath("$.data.objects.bancoOrigem").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.data.objects.bancoDestino").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.errors").isEmpty());

    }

    @Test
    @WithMockUser
    public void testCriarTransferenciaErro400() throws Exception {

        Optional<Banco> bancoContext = Optional.of(bancoOrigem);
        Optional<TokenSeguranca> tokenContext = Optional.of(tokenSeguranca);

        BDDMockito.given(this.bancoService.findById(Mockito.any())).willReturn(bancoContext);
        BDDMockito.given(this.tokenSegurancaService.findByBancoAndStrConta(Mockito.any(Banco.class), Mockito.anyString()))
                .willReturn(tokenContext);
        BDDMockito.given(this.transferenciaService.persist(Mockito.any(Transferencia.class))).willReturn(transferencia);

        TransferenciaDto transferenciaDto = new TransferenciaDto();
        transferenciaDto.setContaDestino(null);
        transferenciaDto.setContaOrigem(transferencia.getContaOrigem());
        transferenciaDto.setValorTransferencia(null);

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                .content(obterJsonTransferenciaDtoParaRequisicaoPost(transferenciaDto))
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());

    }

    private String obterJsonTransferenciaDtoParaRequisicaoPost(TransferenciaDto transferenciaDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(transferenciaDto);
    }
}