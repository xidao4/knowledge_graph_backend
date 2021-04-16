package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.po.PicUnit;
import com.sec.supernatural.backend_coin.vo.PicUnitVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/16
 **/
@SpringBootTest
public class PicUnitMapperTest {
    @Autowired
    PicUnitMapper picUnitMapper;

    @Test
    void addPicUnit() throws Exception {
        PicUnit picUnit = new PicUnit();
        picUnit.setUrl("http://118.182.96.49:8001/api/storage/image/679eb99be9403c5de88e0b4c41d9c3bd.jpeg");
        picUnit.setPicId("test_picId");
        picUnit.setUnitName("hahaha");
        picUnitMapper.addPicUnit(picUnit);
    }

    @Test
    void findByPicId(){
        List<PicUnit> picUnits = picUnitMapper.findByPicId("test_picId");
        System.out.println(picUnits.size());
    }

    @Test
    void updateUnitName(){
        picUnitMapper.updateUnitName("test_picId","http://118.182.96.49:8001/api/storage/image/3a588607c3c1b600b742acb5893b3864.jpg","hello");
    }

}
