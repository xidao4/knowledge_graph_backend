package com.sec.supernatural.backend_coin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.util.JwtUtil;
import com.sec.supernatural.backend_coin.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
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
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Map;

/**
 * @author shenyichen
 * @date 2021/4/9
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GraphControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private GraphController graphController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private MyResponse postTemplate(String url, Object object, int expect_code, Map<String, Object> headers) throws Exception{

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("token", JwtUtil.createToken(1, 24, Calendar.HOUR))
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

    @Test
    void getAll() throws Exception {
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/test.json");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
        MyResponse myResponse = graphController.getAll(mFile);
        System.out.println(myResponse.getData());
    }

    @Test
    void download() throws Exception {
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        postTemplate("/api/graph/download",picIdVO,0,null);
    }

    @Test
    void save() throws Exception {
        String fileStr = "{\n" +
                "  \"nodes\": [{\"id\": \"0\", \"label\": \"hit_node_1\", \"oriLabel\": \"hit_node_1\", \"class\": \"type_1\"},\n" +
                "            {\"id\": \"1\", \"label\": \"hit_node_2\", \"oriLabel\": \"hit_node_2\", \"class\": \"type_1\"},\n" +
                "            {\"id\": \"2\", \"label\": \"node_1\", \"oriLabel\": \"node_1\", \"class\": \"type_2\"},\n" +
                "            {\"id\": \"3\", \"label\": \"node_2\", \"oriLabel\": \"node_2\", \"class\": \"type_3\"}\n" +
                "  ],\n" +
                "  \"edges\": [{\"id\": \"0\", \"label\": \"hit_edge_1\", \"oriLabel\": \"hit_edge_1\", \"source\": \"node_1\", \"target\": \"node_2\", \"class\": \"test_type_1\"},\n" +
                "            {\"id\": \"1\", \"label\": \"edge_1\", \"oriLabel\": \"edge_1\", \"source\": \"hit_node_1\", \"target\": \"hit_node_2\", \"class\": \"test_type_2\"},\n" +
                "            {\"id\": \"2\", \"label\": \"hit_edge_2\", \"oriLabel\": \"hit_edge_2\", \"source\": \"node_1\", \"target\": \"hit_node_2\", \"class\": \"test_type_1\"},\n" +
                "            {\"id\": \"3\", \"label\": \"edge_1\", \"oriLabel\": \"edge_1\", \"source\": \"node_1\", \"target\": \"node_2\", \"class\": \"test_type_2\"}\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(fileStr);
        JSONArray nodes = jsonObject.getJSONArray("nodes");
        JSONArray edges = jsonObject.getJSONArray("edges");
        Graph graph = new Graph();
        graph.setFnodes(nodes);
        graph.setFedges(edges);
        graph.setSnodes(nodes);
        graph.setSedges(edges);
        graph.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = graphController.save(graph);
        System.out.println(myResponse.getData());
    }

    @Test
    void getPicElements() throws Exception {
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        postTemplate("/api/graph/getPicElements",picIdVO,0,null);
    }

    @Test
    void getNodesByTypes() throws Exception {
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = postTemplate("/api/graph/getNodesByTypes",picIdVO,0,null);
        System.out.println(myResponse.getData());
    }

    @Test
    void getNodeTypes() throws Exception {
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = postTemplate("/api/graph/getNodeTypes",picIdVO,0,null);
        System.out.println(myResponse.getData());
    }

    @Test
    void getPicTypes() throws Exception {
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        postTemplate("/api/graph/getPicTypes",picIdVO,0,null);
    }

    @Test
    void uploadCustomizeImg() throws Exception {
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/ikura.jpeg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
        PicUnitVO picUnitVO = new PicUnitVO(1,"test_picId","test_picName",mFile);
        MyResponse myResponse = graphController.uploadCustomizeImg("test_picId",1,"test_picName",mFile);
        System.out.println(myResponse.getData());
    }

    @Test
    void getPicCustomizeElements() throws Exception {
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("test_picId");
        MyResponse myResponse = postTemplate("/api/graph/getPicCustomizeElements",picIdVO,0,null);
        System.out.println(myResponse.getData());
    }

    @Test
    void bindUrlToPic() throws Exception{
        BindUrlToPicVO bindUrlToPicVO = new BindUrlToPicVO();
        bindUrlToPicVO.setPicId("test_picId");
        bindUrlToPicVO.setCustomizeEntityName("m416");
        bindUrlToPicVO.setCustomizeImgUrl("http://118.182.96.49:8001/api/storage/image/3a588607c3c1b600b742acb5893b3864.jpg");
        MyResponse myResponse = graphController.bindUrlToPic(bindUrlToPicVO);
        System.out.println(myResponse.getData());
    }

    @Test
    void thumbnail() throws Exception {
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/ikura.jpeg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
        MyResponse myResponse = graphController.thumbnail("test_picId_3",1,"ikura",mFile);
        System.out.println(myResponse.getData());
    }

    @Test
    void getUserPics() throws Exception {
        UserIdVO userIdVO = new UserIdVO();
        userIdVO.setUserId(1);
        postTemplate("/api/graph/getUserPics",userIdVO,0,null);
    }

    @Test
    void getHistory() throws Exception {
        UserIdVO userIdVO = new UserIdVO();
        userIdVO.setUserId(1);
        postTemplate("/api/graph/getHistory",userIdVO,0,null);
    }

    @Test
    void search() throws Exception {
        SearchVO searchVO = new SearchVO();
        searchVO.setUserId(1);
        searchVO.setPicId("60706cf7723fe7362650e27f");
        searchVO.setKeyWord("hit");
        postTemplate("/api/graph/search",searchVO,0,null);
    }
}
