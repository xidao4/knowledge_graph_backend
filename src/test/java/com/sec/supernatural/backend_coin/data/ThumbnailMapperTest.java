package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Thumbnail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/6
 **/
@SpringBootTest
public class ThumbnailMapperTest {
    @Autowired
    ThumbnailMapper thumbnailMapper;

    @Test
//    @Transactional
    void addThumbnailTest(){
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUrl("https://imgessl.kugou.com/uploadpic/softhead/240/20201223/20201223180019433.jpg");
        thumbnail.setPicId("test_picUrl_2");
        thumbnail.setPicName("ayase");
        thumbnail.setUserId(1);
        thumbnailMapper.addThumbnail(thumbnail);
    }

    @Test
//    @Transactional
    void findByUserIdTest(){
        List<Thumbnail> thumbnails = thumbnailMapper.findByUserId(1);
        System.out.println("size: "+thumbnails.size());
        for(int i=0;i<thumbnails.size();i++){
            System.out.println(thumbnails.get(i).getPicId());
        }
    }
}
