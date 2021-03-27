package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.vo.ChangeRelationVO;
import com.sec.supernatural.backend_coin.vo.EntityVO;
import com.sec.supernatural.backend_coin.vo.GraphVO;
import com.sec.supernatural.backend_coin.vo.RelationVO;
import org.apache.commons.codec.language.DaitchMokotoffSoundex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

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

    String name1;
    String name2;
    String name3;
    String name4;
    String name5;

    @Before
    void init(){
        name1 = new Date().toString();
        name2 = name1 + "_";
        name3 = name1 + "__";
        name4 = name1 + "___";
        name5 = name1 + "____";
    }

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
        Assert.assertNotNull(graphVO);
    }

    @Test
    void dao2Json() {
        MultipartFile multipartFile = graphService.dao2Json(2);
        Assert.assertNotEquals(0, multipartFile.getSize());
    }

    @Test
    void addEntity() {
        EntityVO entityVO = new EntityVO();
        entityVO.setPicId("0");
        entityVO.setName(name1);
        graphService.addEntity(entityVO);
        entityVO.setName(name2);
        graphService.addEntity(entityVO);
        entityVO.setName(name3);
        graphService.addEntity(entityVO);
        entityVO.setName(name4);
        graphService.addEntity(entityVO);
    }

    @Test
    @AfterTestMethod("addEntity")
    public void deleteEntity() {
        EntityVO entityVO = new EntityVO();
        entityVO.setPicId("0");
        entityVO.setName(name3);
        graphService.deleteEntity(entityVO);
    }

    @Test
    @AfterTestMethod("addEntity")
    public void changeEntity() {
        graphService.changeEntity(0, name4, name4 + "_");
    }

    @Test
    @AfterTestMethod("addEntity")
    public void addRelation() {
        RelationVO relationVO = new RelationVO();
        relationVO.setNode1(name1);
        relationVO.setNode2(name2);
        relationVO.setName(new Date().toString());
        relationVO.setPicId("0");
        relationVO.setType("test-type");
        graphService.addRelation(relationVO);
    }

    @Test
    @AfterTestMethod("changeRelation")
    public void deleteRelation() {
        RelationVO relationVO = new RelationVO();
        relationVO.setNode1(name1);
        relationVO.setNode2(name2);
        relationVO.setName(name5);
        relationVO.setPicId("0");
        relationVO.setType("test-type");
        graphService.deleteRelation(relationVO);
    }

    @Test
    @AfterTestMethod("addRelation")
    public void changeRelation() {
        ChangeRelationVO changeRelationVO = new ChangeRelationVO();
        changeRelationVO.setPicId("0");
        changeRelationVO.setNode1(name1);
        changeRelationVO.setNode2(name2);
        changeRelationVO.setName(name5);
        changeRelationVO.setNewName(name5 + "_");
        changeRelationVO.setNewType("test-type_");
        graphService.changeRelation(changeRelationVO);
    }
}