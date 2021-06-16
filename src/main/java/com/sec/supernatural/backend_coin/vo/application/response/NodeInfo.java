package com.sec.supernatural.backend_coin.vo.application.response;

public class NodeInfo {
    String title;
    String info;
    String categories;
    String id;
    //['person']:人名
    //['event']:事件
    //['location']:地点
    //['time']:时间


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
