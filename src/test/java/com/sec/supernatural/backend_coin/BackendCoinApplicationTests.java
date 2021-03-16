package com.sec.supernatural.backend_coin;

import com.sec.supernatural.backend_coin.controller.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendCoinApplicationTests {

    @Autowired
    PersonController personController;

    @Test
    void contextLoads() {
    }

    @Test
    void testNeo4j(){
        personController.findByName("Tom Hanks");
    }

}
