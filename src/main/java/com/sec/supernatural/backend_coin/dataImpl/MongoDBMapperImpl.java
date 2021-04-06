package com.sec.supernatural.backend_coin.dataImpl;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.data.MongoDBMapper;
import com.sec.supernatural.backend_coin.po.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/3/29
 */
@Component
public class MongoDBMapperImpl implements MongoDBMapper {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveGraph(Graph graph) {
        // save和insert的区别：
        // Saves the object, overwriting any object that might have the same id
        // 参考：https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template.save-update-remove
        mongoTemplate.save(graph,"Graph");
    }

    @Override
    public Graph findGraph(String picId) {
        Query query = Query.query(Criteria.where("_id").is(picId));
        Graph graph = mongoTemplate.findOne(query, Graph.class);
        return graph;
    }

    @Override
    public void insertMulti(List<JSONObject> jsonObjects, String collectionName) {
        mongoTemplate.save(jsonObjects, collectionName);
    }
}
