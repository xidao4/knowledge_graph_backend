package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Entity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;


/**
 * @author wangyuchen
 * @date 2021/3/20 8:49 下午
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class EntityMapperTest {
    @Autowired
    EntityMapper entityMapper;
    Date date;
    Date date2;
    String random;
    @Test
    void addEntity() {
        date=new Date();
        Entity entity = new Entity(0,date.toString());
        entityMapper.addEntity(entity);
    }

    @Test
    void deleteEntity() {
        Date d=new Date();
        Entity entity = new Entity(0,d.toString());
        entityMapper.addEntity(entity);
        entityMapper.deleteEntity(0,d.toString());
    }

    @Test
    void changeEntity() {
        date2=new Date();
        Entity entity = new Entity(0,date2.toString());
        entityMapper.addEntity(entity);
        random=String.valueOf(Math.random());
        entityMapper.changeEntity(0,date2.toString(),random);
    }

    @Test
    void getEntity() {
        entityMapper.getEntity(0,random);
    }

    @Test
    void getEntitiesByPicId() {
        entityMapper.getEntitiesByPicId(0);
    }
}