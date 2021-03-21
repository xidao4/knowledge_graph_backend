package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.vo.GraphVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangyuchen
 * @date 2021/3/20 8:50 下午
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class GraphServiceTest {
    @Autowired
    private GraphService graphService;

    @Test
    void json2Dao() {
//        // String fullFileName = StringUtils.cleanPath(mfile.getOriginalFilename());//测试时要替换成file.getName
//        Path path = Paths.get("C:\\Users\\admin\\Desktop\\test.txt");
//        File file = new File(path.toUri());
//        try{
//            FileInputStream fileInputStream = new FileInputStream(file);
//            MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
//            graphService.json2Dao(multipartFile);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Test
    void getAll() {
        GraphVO graphVO = graphService.getAll(2);
    }

    @Test
    void dao2Json() {
        MultipartFile multipartFile = graphService.dao2Json(2);
        Assert.assertNotEquals(0,multipartFile.getSize());
    }

    @Test
    void addEntity() {
    }

    @Test
    void deleteEntity() {
    }

    @Test
    void changeEntity() {
    }

    @Test
    void addRelation() {
    }

    @Test
    void deleteRelation() {
    }

    @Test
    void changeRelation() {
    }
}