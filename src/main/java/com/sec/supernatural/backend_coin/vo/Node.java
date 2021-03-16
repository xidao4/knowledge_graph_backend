package com.sec.supernatural.backend_coin.vo;

/**
 * 自定义节点类型
 */
public class Node {
    private String id;
    private int group;//类目 后期用枚举类型表示

    public Node(String id, int group) {
        this.id = id;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
