package com.nosbielc.mixed.salad.gateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GatewayServiceApplicationTests {

    @Test
    public void contextLoads() {
        assertTrue(Boolean.TRUE);
    }


}
