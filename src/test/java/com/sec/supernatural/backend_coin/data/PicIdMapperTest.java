package com.sec.supernatural.backend_coin.data;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangyuchen
 * @date 2021/3/20 8:49 下午
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PicIdMapperTest {
    @Autowired
    private PicIdMapper picIdMapper;
    @Test
    void updatePicId() {
        int picId = picIdMapper.getPicId();
        picIdMapper.updatePicId();
        Assert.assertEquals(picId+1,picIdMapper.getPicId());
    }

    @Test
    void getPicId() {
        int picId = picIdMapper.getPicId();
        return;
    }
}