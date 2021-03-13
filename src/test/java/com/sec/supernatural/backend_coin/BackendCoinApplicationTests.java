package com.sec.supernatural.backend_coin;

import com.sec.supernatural.backend_coin.controller.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendCoinApplicationTests {

    @Autowired
    TestController testController;

    @Test
    void contextLoads() {
    }

    @Test
    void testNeo4j(){
        testController.add();
    }

}
