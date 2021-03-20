package com.sec.supernatural.backend_coin.vo;

import java.util.Map;

public class HttpResponseVO {
    /**
     * 返回的状态码
     */
    private int status;

    /**
     * 返回的数据信息
     */
    private String body;

    /**
     * 返回的头信息
     */
    private Map<String, String> header;

    public HttpResponseVO(int status, String body, Map<String, String> header){
        this.status = status;
        this.body = body;
        this.header = header;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
