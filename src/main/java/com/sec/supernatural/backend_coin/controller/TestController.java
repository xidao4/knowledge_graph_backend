package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.TestBl;
import com.sec.supernatural.backend_coin.po.TestPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestBl testBl;

    /**
     * 新增电影节点
     * */
    @RequestMapping("/add")
    public int add() {
        TestPO testPO = new TestPO();
        testPO.setTitle("MinAntMovie");
        int i = testBl.addMovie(testPO);
        return i;
    }

}
