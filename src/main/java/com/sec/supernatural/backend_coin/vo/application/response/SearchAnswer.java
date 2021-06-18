package com.sec.supernatural.backend_coin.vo.application.response;

import java.util.List;

public class SearchAnswer {
    List<NodeInfo> contentList;
    String len;
    String answer;
    String code;
    ShowGraphData showGraphData;

    public List<NodeInfo> getContentList() {
        return contentList;
    }

    public void setContentList(List<NodeInfo> contentList) {
        this.contentList = contentList;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ShowGraphData getShowGraphData() {
        return showGraphData;
    }

    public void setShowGraphData(ShowGraphData showGraphData) {
        this.showGraphData = showGraphData;
    }
}
