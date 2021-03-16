package com.sec.supernatural.backend_coin.vo;

public class Link {
    private String source;
    private String target;
    private int val;

    public Link(String source, String target, int val) {
        this.source = source;
        this.target = target;
        this.val = val;
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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
