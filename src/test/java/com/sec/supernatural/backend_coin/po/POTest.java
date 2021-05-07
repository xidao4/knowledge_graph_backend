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
        // Graph
        Graph graph = new Graph();
        graph.equals(graph);
        graph.canEqual(graph);
        graph.hashCode();
        graph.toString();
        // History
        History history = new History();
        history.equals(history);
        history.canEqual(history);
        history.hashCode();
        graph.toString();
        // PicUnit
        PicUnit picUnit = new PicUnit();
        picUnit.equals(picUnit);
        picUnit.canEqual(picUnit);
        picUnit.hashCode();
        picUnit.toString();
        // Thumbnail
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.equals(thumbnail);
        thumbnail.canEqual(thumbnail);
        thumbnail.hashCode();
        thumbnail.toString();
        // User
        User user = new User();
        user.equals(user);
        user.canEqual(user);
        user.hashCode();
        user.toString();
    }
}
