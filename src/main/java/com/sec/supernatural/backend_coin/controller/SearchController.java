package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.SearchService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.application.request.NodeLabel;
import com.sec.supernatural.backend_coin.vo.application.request.SemanticSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @PostMapping("/getAnswer")
    public MyResponse getAnswer(@RequestBody SemanticSearchVO semanticSearchVO){
        try{
            System.out.println("1");
            return searchService.getAnswer(semanticSearchVO.getQuestion());
        }catch (Exception e){
            e.printStackTrace();
            return MyResponse.error("HttpClient有误");
        }
    }
    @PostMapping("/uploadScene")
    public MyResponse uploadScene(@RequestBody MultipartFile mfile){
        return searchService.uploadScene(mfile);
    }

    @PostMapping("/detail")
    public MyResponse detail(@RequestBody NodeLabel label){
        return searchService.detail(label.getLabel());
    }
}
