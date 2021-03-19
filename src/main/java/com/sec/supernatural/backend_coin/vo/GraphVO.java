package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.po.Entity;

import java.util.List;

public class GraphVO {
    private List<Entity> entities;
    private List<Relation> relations;
    private int picId;

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
