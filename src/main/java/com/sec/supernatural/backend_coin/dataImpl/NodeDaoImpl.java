package com.sec.supernatural.backend_coin.dataImpl;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.data.NodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangyuchen
 * @date 2021/3/26 11:25 上午
 */
@Component
public class NodeDaoImpl implements NodeDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveByDocument(JSONObject document) {
        mongoTemplate.save(document, "Node");
    }
}
