package com.sec.supernatural.backend_coin.po;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author shenyichen
 * @date 2021/5/7
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class POTest {
    @Test
    public void poTest(){
        Graph graph = new Graph();
        Graph graph1 = new Graph();
        graph.equals(graph1);
        graph.canEqual(graph1);
        graph.hashCode();
    }
}
