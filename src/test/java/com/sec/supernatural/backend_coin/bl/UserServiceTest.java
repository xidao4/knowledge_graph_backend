package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.LoginVO;
import com.sec.supernatural.backend_coin.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author shenyichen
 * @date 2021/4/9
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void loginTest(){
        LoginVO loginVO = new LoginVO();
        loginVO.setUsername("super");
        loginVO.setPassword("super");
        MyResponse myResponse = userService.login(loginVO);
        System.out.println(myResponse.getCode());
        System.out.println(myResponse.getData());
        UserVO userVO = (UserVO) myResponse.getData();
        Assert.assertEquals(Integer.valueOf(1),userVO.getUserId());
    }
}
