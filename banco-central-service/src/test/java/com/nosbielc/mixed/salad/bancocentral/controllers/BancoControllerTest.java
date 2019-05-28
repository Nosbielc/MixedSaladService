package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import org.hamcrest.core.IsNull;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"eureka.client.enabled:false"})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BancoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BancoServiceImpl bancoService;

    private static final String URL_BASE = "/api/v1/banco";
    private static final Long ID_BANCO = 1L;
    private static final String NOME_BASE = "CENTRAL";
    private static final String NOME = "BANCO-CENTRAL";

    @Test
    @WithMockUser
    public void testListarBancos() throws Exception {
        List<Banco> bancos = Arrays.asList(new Banco(ID_BANCO, NOME_BASE, NOME, Boolean.TRUE));
        Page<Banco> pageBancos = new PageImpl<>(bancos);

        BDDMockito.given(this.bancoService.findAllPageable(Mockito.any())).willReturn(pageBancos);

        mvc.perform(MockMvcRequestBuilders.get(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].id").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data.content[0].codBanco").value(ID_BANCO))
                .andExpect(jsonPath("$.data.content[0].strNomeBase").value(NOME_BASE))
                .andExpect(jsonPath("$.data.content[0].strNome").value(NOME))
                .andExpect(jsonPath("$.data.content[0].ativo").value(Boolean.TRUE))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    public void detalhe() {
    }

    @Test
    public void criar() {
    }

}