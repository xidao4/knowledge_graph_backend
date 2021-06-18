package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.ChatService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.application.request.ChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping("/getAnswer")
    public MyResponse getAnswer(@RequestBody ChatVO chatVO){
        try{
            return chatService.getAnswer(chatVO.getRoleId(),chatVO.getQuestion());
        }catch (Exception e){
            e.printStackTrace();
            return MyResponse.error("HttpClient有误");
        }
    }

    @PostMapping("/uploadScene")
    public MyResponse uploadScene(@RequestBody MultipartFile mfile){
        return chatService.uploadScene(mfile);
    }
}
