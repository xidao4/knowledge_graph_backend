package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Entity;

public class NodeVO {
    private String name;

    public NodeVO(Entity entity){
        this.name = entity.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
