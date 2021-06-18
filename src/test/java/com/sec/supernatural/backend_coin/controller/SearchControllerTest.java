package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.vo.application.request.NodeLabel;
import com.sec.supernatural.backend_coin.vo.application.request.SemanticSearchVO;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/6/18
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SearchControllerTest {
    @Autowired
    SearchController searchController;

    @Test
    public void getAnswer(){
        SemanticSearchVO semanticSearchVO = new SemanticSearchVO();
        semanticSearchVO.setQuestion("林黛玉最后怎么了");
        searchController.getAnswer(semanticSearchVO);
    }

    @Test
    public void uploadScene() throws IOException {
        File file = new File("datastore/1.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile mfile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        searchController.uploadScene(mfile);
    }

    @Test
    public void detail(){
        NodeLabel nodeLabel = new NodeLabel();
        nodeLabel.setLabel("林黛玉");
        searchController.detail(nodeLabel);
    }
}
