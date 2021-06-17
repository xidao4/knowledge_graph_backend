package com.sec.supernatural.backend_coin.integration;

import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.bl.StorageService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.controller.GraphController;
import com.sec.supernatural.backend_coin.controller.StorageController;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.vo.PicIdVO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * @author shenyichen
 * @date 2021/4/18
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    GraphService graphService;
    @Autowired
    StorageService storageService;
    @Autowired
    StorageController storageController;
    @Autowired
    GraphController graphController;

//    @Test
//    public void Test() throws Exception{
//        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/test.json");
//        InputStream inputStream = new FileInputStream(file);
//        MultipartFile mFile = new MockMultipartFile(file.getName(), inputStream);
//        MyResponse myResponse = graphController.getAll(mFile);
//        Graph graph = (Graph) myResponse.getData();
//        String picId = graph.getPicId();
//        PicIdVO picIdVO = new PicIdVO();
//        picIdVO.setPicId(picId);
//        MyResponse myResponse1 = graphController.download(picIdVO);
//        System.out.println(myResponse1.getData());
//    }
}

