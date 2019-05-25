package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TokenDtoTest {

    @Test
    public void testTokenDto() {
        TokenDto tokenDto = new TokenDto();
        assertTrue(tokenDto != null);
    }

    @Test
    public void testTokenDtoComParametros() {
        TokenDto tokenDto = new TokenDto(Long.MIN_VALUE, "str", "str");
        assertTrue(Long.MIN_VALUE == tokenDto.getId());
        assertTrue("str".equalsIgnoreCase(tokenDto.getStrToken()));
        assertTrue("str".equalsIgnoreCase(tokenDto.getStrConta()));
    }

    @Test
    public void testTokenDtoComOBanco() {
        Banco banco = new Banco(Long.MIN_VALUE, "str", "str", Boolean.TRUE);
        TokenDto tokenDto = new TokenDto(Long.MIN_VALUE, "str", "str", banco);
        assertTrue(Long.MIN_VALUE == tokenDto.getId());
        assertTrue("str".equalsIgnoreCase(tokenDto.getStrToken()));
        assertTrue("str".equalsIgnoreCase(tokenDto.getStrConta()));
        assertTrue(tokenDto.getBanco() != null);
    }
}