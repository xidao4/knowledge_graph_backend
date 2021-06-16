package com.sec.supernatural.backend_coin.vo.application.request;

public class ChatVO {
    String roleId;
    String question;

    public ChatVO(String roleId, String question) {
        this.roleId = roleId;
        this.question = question;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
