package com.sec.supernatural.backend_coin;

import com.sec.supernatural.backend_coin.bl.GraphService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendCoinApplicationTests {

    @Autowired
    GraphService graphService;

    @Test
    void contextLoads() {
    }


}
