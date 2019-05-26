package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class NovoTokenDtoTest {

    @Test
    public void testNovoTokenDtoComParametros() {
        NovoTokenDto novoTokenDto = new NovoTokenDto("str", Long.MIN_VALUE);
        assertTrue(Long.MIN_VALUE == novoTokenDto.getBancoId());
        assertTrue("str".equalsIgnoreCase(novoTokenDto.getStrConta()));
    }

}
