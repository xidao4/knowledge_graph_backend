package com.sec.supernatural.backend_coin.data;

import com.alibaba.fastjson.JSONArray;
import com.sec.supernatural.backend_coin.po.Edge;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.po.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljy
 * @date 2021/5/21
 **/
@SpringBootTest
public class EdgeMapperTest {
    @Autowired
    EdgeMapper edgeMapper;

    @Test
    void insert(){
        Map<String,Object> properties=new HashMap<>();
        properties.put("label","导演");
        properties.put("type","not subclass of");
        //Edge edge=new Edge("林诣彬","速度与激情9","0",properties);
        //edgeMapper.insert(edge);
    }

    @Test
    void getNeighborsByLabel(){
        List<Edge> edges=edgeMapper.getNeighborEdgesByLabel("贾宝玉","0");
        System.out.println(edges.size());
    }
}
