package com.sec.supernatural.backend_coin.po;

public class Node {
    private String name;
    private int picId;

    public Node(int picId,String name){
        this.name = name;
        this.picId=picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
