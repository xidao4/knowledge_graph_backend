package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.Link;
import com.sec.supernatural.backend_coin.po.Node;

import java.util.List;

public class GraphVO {
    private List<Node> nodes;
    private List<Link> links;
    private int picId;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
