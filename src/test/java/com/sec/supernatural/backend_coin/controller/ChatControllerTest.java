package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.vo.application.request.ChatVO;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/6/18
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ChatControllerTest {
    @Autowired
    ChatController chatController;

    @Test
    public void getAnswer(){
        ChatVO chatVO = new ChatVO();
        chatVO.setRoleId("0");
        chatVO.setQuestion("林黛玉的结局");
        chatController.getAnswer(chatVO);
    }

    @Test
    public void uploadScene() throws IOException {
        File file = new File("datastore\\1.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile mfile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        chatController.uploadScene(mfile);
    }
}
