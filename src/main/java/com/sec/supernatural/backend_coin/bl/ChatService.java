package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ChatService {
    MyResponse getAnswer(String roleId,String question) throws Exception;

    MyResponse uploadScene(MultipartFile mfile);
}
