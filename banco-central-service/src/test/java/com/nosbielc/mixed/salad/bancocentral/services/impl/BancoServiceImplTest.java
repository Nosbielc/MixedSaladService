package com.nosbielc.mixed.salad.bancocentral.services.impl;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"eureka.client.enabled:false"})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BancoServiceImplTest {

    @MockBean
    private BancoServiceImpl bancoService;

    private Banco banco;

    @Before
    public void initTest() {
        banco = new Banco(Long.MIN_VALUE, "NomeBase", "Nome", Boolean.TRUE);
    }

    @Test
    @WithMockUser
    public void testFindAllPageable() {
        Page<Banco> bancoPage = new PageImpl<>(Arrays.asList(banco));

        BDDMockito.given(this.bancoService.findAllPageable(Mockito.any())).willReturn(bancoPage);
        PageRequest pageRequest = PageRequest.of(0, 100, Sort.Direction.valueOf("DESC"), "id");

        Page<Banco> bancoRetorno = bancoService.findAllPageable(pageRequest);

        assertNotNull(bancoRetorno);
        assertTrue(bancoRetorno.stream().count() > 0);

    }

    @Test
    @WithMockUser
    public void testFindById() {
        Optional<Banco> bancoOptional = Optional.of(banco);

        BDDMockito.given(this.bancoService.findById(Mockito.anyLong())).willReturn(bancoOptional);

        Optional<Banco> bancoRetorno = this.bancoService.findById(1L);

        assertTrue(bancoRetorno.isPresent());
        assertEquals(banco.getId(), bancoRetorno.get().getId() );
        assertEquals(banco.getCodBanco(), bancoRetorno.get().getCodBanco() );
        assertEquals(banco.getAtivo(), bancoRetorno.get().getAtivo() );
        assertEquals(banco.getStrNomeBase(), bancoRetorno.get().getStrNomeBase() );
        assertEquals(banco.getStrNome(), bancoRetorno.get().getStrNome() );

    }

    @Test
    @WithMockUser
    public void testPersist() {

        BDDMockito.given(this.bancoService.persist(Mockito.any(Banco.class))).willReturn(banco);

        Banco bancoRetorno = this.bancoService.persist(banco);

        assertNotNull(bancoRetorno);
        assertEquals(banco.getId(), bancoRetorno.getId() );
        assertEquals(banco.getCodBanco(), bancoRetorno.getCodBanco() );
        assertEquals(banco.getAtivo(), bancoRetorno.getAtivo() );
        assertEquals(banco.getStrNomeBase(), bancoRetorno.getStrNomeBase() );
        assertEquals(banco.getStrNome(), bancoRetorno.getStrNome() );

    }

    @Test
    @WithMockUser
    public void testRemove() {

        Optional<Banco> bancoOptional = Optional.empty();

        BDDMockito.given(this.bancoService.persist(Mockito.any(Banco.class))).willReturn(banco);
        BDDMockito.given(this.bancoService.findById(Mockito.anyLong())).willReturn(bancoOptional);

        this.bancoService.remove(banco);

        Optional<Banco> bancoRetorno = this.bancoService.findById(1L);

        assertFalse(bancoRetorno.isPresent());

    }

    @Test
    @WithMockUser
    public void testFindAll() {

        Optional<List<Banco>> listBanco = Optional.of(Arrays.asList(banco, banco, banco));

        BDDMockito.given(this.bancoService.findAll()).willReturn(listBanco);

        Optional<List<Banco>> listBancoRetorno = this.bancoService.findAll();

        assertTrue(listBancoRetorno.isPresent());
        assertTrue(listBancoRetorno.get().stream().count() == 3);

    }
}