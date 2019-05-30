package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoTokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.ValidaTokenDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TokenSegurancaServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"eureka.client.enabled:false"})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TokenSegurancaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BancoServiceImpl bancoService;

    @MockBean
    private TokenSegurancaServiceImpl tokenSegurancaService;

    private static final String URL_BASE = "/api/v1/token";
    private static final Long ID_BANCO = 1L;
    private static final String NOME_BASE = "CENTRAL";
    private static final String NOME = "BANCO-CENTRAL";
    private static final Long ID_TOKEN = 1L;
    private static final String TOKEN = "10909990987878";
    private static final String CONTA = "00000000000";

    private Banco banco;
    private TokenSeguranca tokenSeguranca;

    @Before
    public void initTest() {
        banco = new Banco(ID_BANCO, NOME_BASE, NOME, Boolean.TRUE);
        banco.setId(ID_BANCO);

        tokenSeguranca = new TokenSeguranca(SecretKeyUtils.getKey(), CONTA,  banco);
        tokenSeguranca.setId(ID_TOKEN);
    }

    @Test
    @WithMockUser
    public void testGerarToken() throws Exception {

        Optional<Banco> bancoContext = Optional.of(banco);
        Optional<TokenSeguranca> tokenContext = Optional.of(tokenSeguranca);

        BDDMockito.given(this.bancoService.findById(Mockito.any())).willReturn(bancoContext);
        BDDMockito.given(this.tokenSegurancaService.findByBancoAndStrConta(Mockito.any(Banco.class), Mockito.anyString()))
                .willReturn(tokenContext);
        BDDMockito.given(this.tokenSegurancaService.persist(Mockito.any(TokenSeguranca.class)))
                .willReturn(tokenContext.get());
        BDDMockito.given(this.tokenSegurancaService.merge(Mockito.any(TokenSeguranca.class)))
                .willReturn(tokenContext.get());

        NovoTokenDto novoTokenDto = new NovoTokenDto(CONTA, ID_BANCO);

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
            .content(obterJsonNovoTokenDtoParaRequisicaoPost(novoTokenDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.objects.id").value(ID_TOKEN))
            .andExpect(jsonPath("$.data.objects.strToken").value(tokenContext.get().getStrToken()))
            .andExpect(jsonPath("$.data.objects.strConta").value(CONTA))
            .andExpect(jsonPath("$.data.objects.banco").value(IsNull.nullValue()))
            .andExpect(jsonPath("$.errors").isEmpty());

    }

    @Test
    @WithMockUser
    public void testValidarToken() throws Exception {

        Optional<Banco> bancoContext = Optional.of(banco);

        tokenSeguranca.setStrToken(SecretKeyUtils.getKey());
        Optional<TokenSeguranca> tokenContext = Optional.of(tokenSeguranca);

        BDDMockito.given(this.bancoService.findById(Mockito.any())).willReturn(bancoContext);
        BDDMockito.given(this.tokenSegurancaService.findByBancoAndStrConta(Mockito.any(Banco.class), Mockito.anyString()))
                .willReturn(tokenContext);

        ValidaTokenDto validaTokenDto = new ValidaTokenDto(CONTA, ID_BANCO, Long.parseLong(TOKEN));

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE.concat("/validar"))
                .content(obterJsonValidaTokenDtoParaRequisicaoPost(validaTokenDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    private String obterJsonNovoTokenDtoParaRequisicaoPost(NovoTokenDto novoTokenDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(novoTokenDto);
    }

    private String obterJsonValidaTokenDtoParaRequisicaoPost(ValidaTokenDto validaTokenDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(validaTokenDto);
    }


}