package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.UserService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyuchen
 * @date 2021/3/27 10:42 上午
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public MyResponse login(@RequestBody LoginVO loginVO){
        log.debug("POST:/user/login:" + loginVO.toString());
        return userService.login(loginVO);
    }
}
