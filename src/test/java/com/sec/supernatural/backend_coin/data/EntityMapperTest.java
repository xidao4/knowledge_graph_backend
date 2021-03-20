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

    @Test
    void addEntity() {
        Entity entity = new Entity(0, new Date().toString());
        entityMapper.addEntity(entity);
    }

    @Test
    void deleteEntity() {
    }

    @Test
    void changeEntity() {
    }

    @Test
    void getEntity() {
    }

    @Test
    void getEntitiesByPicId() {
    }
}