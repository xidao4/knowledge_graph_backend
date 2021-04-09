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
    void saveGraphTest(){
        Graph graph = new Graph();
        String testStr = "[{'id': '0', 'label': 'n0', 'class': 'c0'}]";
        JSONArray testJArray = JSONArray.parseArray(testStr);
        graph.setSedges(testJArray);
        graph.setSnodes(testJArray);
        graph.setFedges(testJArray);
        graph.setFnodes(testJArray);
        graph.setPicId("test_picId");
        mongoDBMapper.saveGraph(graph);
    }

    @Test
    void findGraphTest(){
        Graph graph = mongoDBMapper.findGraph("60706cf7723fe7362650e27f");
        System.out.println(graph.getFedges());
    }

    @Test
    void removeGraphTest(){
        mongoDBMapper.removeGraph("test_picId");
    }
}
