package com.sec.supernatural.backend_coin.vo.application.request;


public class SemanticSearchVO {
    String question;

    public SemanticSearchVO(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
