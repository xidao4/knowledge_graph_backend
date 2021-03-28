package com.sec.supernatural.backend_coin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @author wangyuchen
 * @date 2021/3/20 9:08 下午
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class GraphControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    String name1;
    String name2;
    String name3;
    String name4;
    String name5;


    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        name1 = new Date().toString();
        name2 = name1 + "_";
        name3 = name1 + "__";
        name4 = name1 + "___";
        name5 = name1 + "____";
    }

    private MyResponse postTemplate(String url, Object object, int expect_code, Map<String, Object> headers) throws Exception{
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .contentType("application/json;charset=UTF-8");

        if(object != null){
            String jsonResult = JSONObject.toJSONString(object);
            mockHttpServletRequestBuilder = mockHttpServletRequestBuilder.content(jsonResult);
        }

        if(headers != null){
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                mockHttpServletRequestBuilder = mockHttpServletRequestBuilder.header(entry.getKey(), entry.getValue());
            }
        }

        MvcResult mvcResult = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MyResponse myResponse = JSONObject.parseObject(result, MyResponse.class);

        if(myResponse.getCode() != expect_code){
            System.out.println(myResponse.getCode());
            System.out.println(new String(myResponse.getData().toString()
                    .getBytes(StandardCharsets.ISO_8859_1), "UTF-8"));
        }

        assert myResponse.getCode() == expect_code;
        return myResponse;
    }

//    @Test
//    void getAll() throws Exception {
//        // 使用Postman 测试
//    }
//
//    @Test
//    void download() throws Exception {
//        // 使用Postman 测试
//    }
//
//    @DisplayName("增加实体")
//    @Test
//    void addEntity() throws Exception {
//
//        // 成功插入
//        EntityVO entityVO = new EntityVO();
//        entityVO.setPicId("0");
//        entityVO.setName(name1);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//        entityVO.setName(name2);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//        entityVO.setName(name3);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//        entityVO.setName(name4);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//
//        // 重复插入时，插入失败
//        EntityVO entityVO1 = new EntityVO();
//        entityVO1.setPicId("0");
//        entityVO1.setName("test-add");
//        postTemplate("/api/graph/addEntity", entityVO, 2500, null);
//    }
//
//    @Test
//    @AfterTestMethod("addEntity")
//    public void deleteEntity() throws Exception {
//        // 成功删除实体
//        EntityVO entityVO = new EntityVO();
//        entityVO.setPicId("0");
//        entityVO.setName(name4);
//        postTemplate("/api/graph/deleteEntity", entityVO, 0, null);
//
//        // 删除失败
//        entityVO.setName(new Date().toString());
//        postTemplate("/api/graph/deleteEntity", entityVO, 0, null);
//    }
//
//    @Test
//    @AfterTestMethod("addEntity")
//    public void changeEntity() throws Exception {
//        ChangeEntityVO changeEntityVO = new ChangeEntityVO();
//        changeEntityVO.setPicId("0");
//        changeEntityVO.setOldName(name1);
//        changeEntityVO.setNewName(name1 + "1");
//        postTemplate("/api/graph/deleteEntity", changeEntityVO, 0, null);
//    }
//
//    @Test
//    @AfterTestMethod("addEntity")
//    public void addRelation() throws Exception {
//        RelationVO relationVO = new RelationVO();
//        relationVO.setNode1(name1);
//        relationVO.setNode2(name2);
//        relationVO.setName(name5);
//        relationVO.setPicId("0");
//        relationVO.setType("test-type");
//        postTemplate("/api/graph/addRelation", relationVO, 0, null);
//    }
//
//    @Test
//    @AfterTestMethod("changeRelation")
//    public void deleteRelation() throws Exception {
//        RelationVO relationVO = new RelationVO();
//        relationVO.setNode1(name1);
//        relationVO.setNode2(name2);
//        relationVO.setName(name5);
//        relationVO.setPicId("0");
//        relationVO.setType("test-type");
//        postTemplate("/api/graph/deleteRelation", relationVO, 0, null);
//    }
//
//    @Test
//    @AfterTestMethod("addRelation")
//    public void changeRelation() throws Exception {
//        ChangeRelationVO changeRelationVO = new ChangeRelationVO();
//        changeRelationVO.setPicId("0");
//        changeRelationVO.setNode1(name1);
//        changeRelationVO.setNode2(name2);
//        changeRelationVO.setName(name5);
//        changeRelationVO.setNewName(name5 + "_");
//        changeRelationVO.setNewType("test-type_");
//        postTemplate("/api/graph/changeRelation", changeRelationVO, 0, null);
//    }
}