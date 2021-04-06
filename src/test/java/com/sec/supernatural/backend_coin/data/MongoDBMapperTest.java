package com.sec.supernatural.backend_coin.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.po.Graph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shenyichen
 * @date 2021/4/6
 **/
@SpringBootTest
public class MongoDBMapperTest {
    @Autowired
    MongoDBMapper mongoDBMapper;

    @Test
//    @Transactional
    void saveGraphTest(){
        Graph graph = new Graph();
        String testStr = "[{'id': '0', 'label': 'n0', 'class': 'c0'}]";
        JSONArray testJArray = JSONArray.parseArray(testStr);
        graph.setSedges(testJArray);
        graph.setSnodes(testJArray);
        graph.setFedges(testJArray);
        graph.setFnodes(testJArray);
        mongoDBMapper.saveGraph(graph);
    }

    @Test
//    @Transactional
    void findGraphTest(){
        Graph graph = mongoDBMapper.findGraph("606c624332e01c00dd449efb");
        System.out.println(graph.getFedges());
    }
}
