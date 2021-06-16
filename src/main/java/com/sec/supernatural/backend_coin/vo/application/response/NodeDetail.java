package com.sec.supernatural.backend_coin.vo.application.response;

import java.util.List;

public class NodeDetail {
    String title;
    String info;
    String categories;
    String ending;
    String reason;
    String img;
    List<NodeDisplay> nodes;
    List<EdgeDisplay> edges;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<NodeDisplay> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDisplay> nodes) {
        this.nodes = nodes;
    }

    public List<EdgeDisplay> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDisplay> edges) {
        this.edges = edges;
    }
}
