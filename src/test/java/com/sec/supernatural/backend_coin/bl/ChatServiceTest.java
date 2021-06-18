package com.sec.supernatural.backend_coin.bl;

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
public class ChatServiceTest {
    @Autowired
    ChatService chatService;

    @Test
    public void getAnswer() throws Exception {
        chatService.getAnswer("0","林黛玉的结局");
    }

    @Test
    public void uploadScene() throws IOException {
        File file = new File("datastore/1.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile mfile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        chatService.uploadScene(mfile);
    }
}
