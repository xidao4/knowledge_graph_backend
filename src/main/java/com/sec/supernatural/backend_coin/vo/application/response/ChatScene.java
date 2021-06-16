package com.sec.supernatural.backend_coin.vo.application.response;

import java.util.List;

public class ChatScene {
    String url;
    List<String> contentList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
