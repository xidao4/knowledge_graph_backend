package com.sec.supernatural.backend_coin.data;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wangyuchen
 * @date 2021/3/26 11:10 上午
 */
public interface NodeDao {

    void saveByDocument(JSONObject document);
}
