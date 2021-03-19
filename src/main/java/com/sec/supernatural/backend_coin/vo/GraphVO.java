package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.po.Entity;

import java.util.List;

public class GraphVO {
    private List<NodeVO> nodes;
    private List<LinkVO> links;
    private String picId;

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

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }
}
