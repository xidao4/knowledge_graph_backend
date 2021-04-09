package com.sec.supernatural.backend_coin.bl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author shenyichen
 * @date 2021/4/9
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class StorageServiceTest {
    @Autowired
    StorageService storageService;

    @Test
    public void storeFileTest(){
        String url = storageService.storeFile("test_file_str","test_filename.json");
        System.out.println(url);
    }

    @Test
    public void storeImageTest() throws Exception {
        File file = new File("src/test/java/com/sec/supernatural/backend_coin/testData/ikura.jpeg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        String url = storageService.storeImage(multipartFile);
        System.out.println(url);
    }

    @Test
    public void loadTest() throws IOException {
        Resource resource = storageService.load("test_filename.json");
        System.out.println(resource.getFile().length());
    }
}
