package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.po.Entity;

import java.util.List;

public class GraphVO {
    private List<NodeVO> nodes;
    private List<LinkVO> links;
    private int picId;

    public List<NodeVO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeVO> nodes) {
        this.nodes = nodes;
    }

    public List<LinkVO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkVO> links) {
        this.links = links;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
