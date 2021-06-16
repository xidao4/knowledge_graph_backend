package com.sec.supernatural.backend_coin.vo.application.response;

public class NodeDisplay {
    String id;
    String label;
    //String type;//useless before
    //String class;
    String nodeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
