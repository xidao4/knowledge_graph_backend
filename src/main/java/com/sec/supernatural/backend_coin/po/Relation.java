package com.sec.supernatural.backend_coin.po;

public class Relation {
    private int id;
    private String name;
    private String node1;
    private String node2;
    private String type;
    private int picId;

    public Relation(int picId,String name, String node1, String node2, String type){
        this.picId=picId;
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", node1='" + node1 + '\'' +
                ", node2='" + node2 + '\'' +
                ", type='" + type + '\'' +
                ", picId=" + picId +
                '}';
    }
}
