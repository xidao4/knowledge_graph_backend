package com.sec.supernatural.backend_coin;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/6/18
 **/
public class ClassifyTest {
    @Test
    public void classify() throws IOException {
        File file = new File("datastore/1.jpg");
        if (!file.exists()) {
            System.out.println("文件不存在！");
        } else {
            System.out.println("文件存在！");
        }
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        String label = TestClassify.easydlImageClassify(multipartFile);
        System.out.println(label);
    }
}
