package com.sec.supernatural.backend_coin.vo.application.response;

import com.sec.supernatural.backend_coin.po.Node;

import java.util.Map;

/**
 * @author shenyichen
 * @date 2021/6/18
 **/
public class NodeInfoVO {
    String title;
    String info;
    String id;
    String categories;

    public NodeInfoVO(){

    }

    public NodeInfoVO(Node node){
        Map<String, Object> properties = node.getProperties();
        this.title = (String) properties.get("label");
        this.info = (String) properties.get("info");
        this.categories = (String) properties.get("categories");
        this.id = (String) properties.get("id");
    }
}
