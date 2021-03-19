package com.sec.supernatural.backend_coin.po;

public class Entity {
    private String name;
    private int picId;

    public Entity(int picId, String name){
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

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", picId=" + picId +
                '}';
    }
}