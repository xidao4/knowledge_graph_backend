package com.sec.supernatural.backend_coin.integration;


import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.ChangeRelationVO;
import com.sec.supernatural.backend_coin.vo.EntityVO;
import com.sec.supernatural.backend_coin.vo.RelationVO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @author wangyuchen
 * @date 2021/3/21 00:36 上午
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    GraphService graphService;

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

//    /**
//     * @throws Exception
//     */
//    @Test
//    public void Test() throws Exception{
//        MultipartFile multipartFile = graphService.dao2Json(2);
//        int picIdNum = graphService.json2Dao(multipartFile);
//        String picId = String.valueOf(picIdNum);
//
//        // 增加节点
//        EntityVO entityVO = new EntityVO();
//        entityVO.setPicId(picId);
//        entityVO.setName(name1);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//        entityVO.setName(name2);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//
//        // 增加关系
//        RelationVO relationVO = new RelationVO();
//        relationVO.setNode1(name1);
//        relationVO.setNode2(name2);
//        relationVO.setName(name5);
//        relationVO.setPicId(picId);
//        relationVO.setType("test-type");
//        postTemplate("/api/graph/addRelation", relationVO, 0, null);
//
//        // 修改关系
//        ChangeRelationVO changeRelationVO = new ChangeRelationVO();
//        changeRelationVO.setPicId(picId);
//        changeRelationVO.setNode1(name1);
//        changeRelationVO.setNode2(name2);
//        changeRelationVO.setName(name5);
//        changeRelationVO.setNewName(name5 + "_");
//        changeRelationVO.setNewType("test-type_");
//        postTemplate("/api/graph/changeRelation", changeRelationVO, -1, null);
//    }
//
//    @Test
//    public void Test2() throws Exception{
//        MultipartFile multipartFile = graphService.dao2Json(2);
//        int picIdNum = graphService.json2Dao(multipartFile);
//        String picId = String.valueOf(picIdNum);
//
//        // 增加节点
//        EntityVO entityVO = new EntityVO();
//        entityVO.setPicId(picId);
//        entityVO.setName(name3);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//        entityVO.setName(name4);
//        postTemplate("/api/graph/addEntity", entityVO, 0, null);
//
//        // 增加关系
//        RelationVO relationVO = new RelationVO();
//        relationVO.setNode1(name3);
//        relationVO.setNode2(name4);
//        relationVO.setName(name5);
//        relationVO.setPicId(picId);
//        relationVO.setType("test-type");
//        postTemplate("/api/graph/addRelation", relationVO, 0, null);
//
//        // 删除关系
//        relationVO.setNode1(name3);
//        relationVO.setNode2(name4);
//        relationVO.setName(name5);
//        relationVO.setPicId(picId);
//        relationVO.setType("test-type");
//        postTemplate("/api/graph/deleteRelation", relationVO, 0, null);
//    }

}

