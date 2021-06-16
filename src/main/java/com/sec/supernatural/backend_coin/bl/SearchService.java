package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import org.springframework.web.multipart.MultipartFile;

public interface SearchService {
    MyResponse getAnswer(String question) throws Exception;

    MyResponse uploadScene(MultipartFile mfile);

    MyResponse detail(String id);
}
