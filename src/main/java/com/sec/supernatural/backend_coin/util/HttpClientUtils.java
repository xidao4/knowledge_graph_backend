package com.sec.supernatural.backend_coin.util;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.UnsupportedEncodingException;

public class HttpClientUtils {
    public static HttpPost create(String question) throws UnsupportedEncodingException {

        // 创建http POST请求
        HttpPost httpPost=new HttpPost("http://localhost:5000/chat/getAnswer");

        String json="{\"question\":"+question+"}";
        StringEntity entity=new StringEntity(json);
        httpPost.setEntity(entity);

        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Content-type","application/json");
        //伪装浏览器
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");

        return httpPost;
    }
}
