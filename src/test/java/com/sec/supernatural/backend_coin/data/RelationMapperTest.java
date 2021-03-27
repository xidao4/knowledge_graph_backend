package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Entity;
import com.sec.supernatural.backend_coin.po.Relation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.FixMethodOrder;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangyuchen
 * @date 2021/3/20 8:49 下午
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@FixMethodOrder(MethodSorters.JVM)
class RelationMapperTest {
    @Autowired
    RelationMapper relationMapper;
    @Autowired
    EntityMapper entityMapper;
    Date date;
    @Test
    void addRelation() {
        //int picId,String name, String node1, String node2, String type
        date=new Date();
        Entity entity1 = new Entity(0,date.toString());
        Entity entity2 = new Entity(0,date.toString()+"-2");
        entityMapper.addEntity(entity1);
        entityMapper.addEntity(entity2);
        Relation relation=new Relation(0,"test",entity1.getName(),entity2.getName(),null);
        relationMapper.addRelation(relation);
    }

    @Test
    void deleteRelation() {

    }

    @Test
    void getRelationsByNodesAndName() {
    }

    @Test
    void getRelationsByNode() {
    }

    @Test
    void changeRelationName() {
    }

    @Test
    void changeRelationType() {
    }

    @Test
    void changeRelationNameAndType() {
    }

    @Test
    void getRelationsBySource() {
    }

    @Test
    void getRelationsByPicId() {
    }
}