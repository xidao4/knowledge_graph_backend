package com.sec.supernatural.backend_coin.po;

public class Link {
    private int id;
    private String name;
    private String source;
    private String target;
    private String type;
    private int picId;

    public Link(String name, String source, String target, String type,int picId){
        this.name = name;
        this.source = source;
        this.target = target;
        this.type = type;
        this.picId=picId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
}
