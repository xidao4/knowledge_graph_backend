package com.sec.supernatural.backend_coin.bl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author shenyichen
 * @date 2021/4/9
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class GraphServiceTest {
    @Autowired
    GraphService graphService;

    @Test
    public void json2DaoTest() throws Exception {
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/test.json");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
        MyResponse myResponse = graphService.json2Dao(mFile);
        System.out.println(myResponse.getCode());
        System.out.println(myResponse.getData());
    }

    @Test
    public void dao2JsonUrlTest(){
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = graphService.dao2JsonUrl(picIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void saveTest() {
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
        MyResponse myResponse = graphService.save(graph);
        System.out.println(myResponse.getData());
    }

    @Test
    public void getPicElementsTest(){
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = graphService.getPicElements(picIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void getNodesByTypesTest(){
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = graphService.getNodesByTypes(picIdVO);
        System.out.println(myResponse.getData());
    }


    @Test
    public void getNodeTypes(){
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = graphService.getNodeTypes(picIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void getPicTypesTest(){
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        MyResponse myResponse = graphService.getPicTypes(picIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void uploadCustomizeImg() throws Exception {
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/ikura.jpeg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
        PicUnitVO picUnitVO = new PicUnitVO(1,"test_picId","test_picName",mFile);
        MyResponse myResponse = graphService.uploadCustomizeImg(picUnitVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void getPicElement(){
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("test_picId");
        MyResponse myResponse = graphService.getPicElement(picIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void storeThumbnailTest() throws Exception {
        ThumbnailVO thumbnailVO = new ThumbnailVO();
        thumbnailVO.setPicId("test_picId_3");
        thumbnailVO.setPicName("ikura");
        thumbnailVO.setUserId(1);
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/ikura.jpeg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
        thumbnailVO.setFile(mFile);
        MyResponse myResponse = graphService.storeThumbnail(thumbnailVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void getUserPicsTest(){
        UserIdVO userIdVO = new UserIdVO();
        userIdVO.setUserId(1);
        MyResponse myResponse = graphService.getUserPics(userIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void getHistoryTest(){
        UserIdVO userIdVO = new UserIdVO();
        userIdVO.setUserId(1);
        MyResponse myResponse = graphService.getHistory(userIdVO);
        System.out.println(myResponse.getData());
    }

    @Test
    public void searchTest(){
        SearchVO searchVO = new SearchVO();
        searchVO.setUserId(1);
        searchVO.setPicId("60706cf7723fe7362650e27f");
        searchVO.setKeyWord("hit");
        MyResponse myResponse = graphService.search(searchVO);
        System.out.println(myResponse.getData());
    }
}
