package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Relation;

public class LinkVO {
    private String name;
    private String node1;
    private String node2;
    private String type;

    public LinkVO(Relation relation){
        this.name = relation.getName();
        this.node1 = relation.getNode1();
        this.node2 = relation.getNode2();
        this.type = relation.getType();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode1() {
        return node1;
    }

    public void setNode1(String node1) {
        this.node1 = node1;
    }

    public String getNode2() {
        return node2;
    }

    public void setNode2(String node2) {
        this.node2 = node2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
