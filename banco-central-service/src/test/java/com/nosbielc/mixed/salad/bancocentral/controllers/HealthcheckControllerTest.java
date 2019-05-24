package com.nosbielc.mixed.salad.bancocentral.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HealthcheckControllerTest {

    @Autowired
    private MockMvc mvc;

    private static final String URL_BASE = "/healthcheck";

    @Test
    @WithMockUser
    public void testGetHealthcheck() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_BASE))
                .andExpect(status().isOk());
    }
}
