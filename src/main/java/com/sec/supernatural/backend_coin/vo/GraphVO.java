package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.po.Entity;

import java.util.List;

public class GraphVO {
    private List<Entity> entities;
    private List<Relation> relations;
    private int picId;

    public List<Entity> getNodes() {
        return entities;
    }

    public void setNodes(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Relation> getLinks() {
        return relations;
    }

    public void setLinks(List<Relation> relations) {
        this.relations = relations;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
