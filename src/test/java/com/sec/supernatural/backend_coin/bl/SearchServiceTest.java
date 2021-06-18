package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/6/18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    SearchService searchService;

    @Test
    public void getAnswer() throws Exception {
        MyResponse myResponse = searchService.getAnswer("林黛玉最后怎么了");
        System.out.println(myResponse);
    }

    @Test
    public void detail(){
        MyResponse myResponse = searchService.detail("林黛玉");
        System.out.println(myResponse);
    }


    @Test
    public void uploadScene() throws IOException {
        File file = new File("datastore\\1.jpg");
        if (!file.exists()) {
            System.out.println("文件不存在！");
        } else {
            System.out.println("文件存在！");
        }
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        MyResponse myResponse = searchService.uploadScene(multipartFile);
        System.out.println(myResponse);
    }
}
