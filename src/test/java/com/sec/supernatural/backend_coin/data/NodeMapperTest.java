package com.sec.supernatural.backend_coin.data;

import com.alibaba.fastjson.JSONArray;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.po.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljy
 * @date 2021/5/21
 **/
@SpringBootTest
public class NodeMapperTest {
    @Autowired
    NodeMapper nodeMapper;

    @Test
    void insert(){
        Node node=new Node();
        Map<String,Object> properties=new HashMap<>();
        properties.put("picId",0);
        properties.put("label","速度与激情9");
        properties.put("class","电影");
        node.setProperties(properties);
        nodeMapper.insert(node);

        Node node2=new Node();
        Map<String,Object> properties2=new HashMap<>();
        properties.put("picId",0);
        properties.put("label","林诣彬");
        properties.put("class","人名");
        node.setProperties(properties);
        nodeMapper.insert(node);
    }


}
