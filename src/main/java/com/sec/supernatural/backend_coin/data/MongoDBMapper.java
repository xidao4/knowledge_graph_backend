package com.sec.supernatural.backend_coin.data;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.po.Graph;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/3/29
 */
public interface MongoDBMapper {
    void saveGraph(Graph graph);
    Graph findGraph(String picId);
//    void insertMulti(List<JSONObject> jsonObjects, String collectionName);
}
