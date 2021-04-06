package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author shenyichen
 * @date 2021/4/6
 **/
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    @Transactional
    void getUserByUsernameTest(){
        User user = userMapper.getUserByUsername("super");
        Assert.assertEquals(user.getPassword(),"GzIxZVzrt6H3g+3fJ9JUyg==");
        Assert.assertEquals(user.getUserId().intValue(),1);
    }

    @Test
//    @Transactional
    void updateLastLoginTimeTest(){
        Date date = new Date();
        userMapper.updateLastLoginTime(1,date);
        User user = userMapper.getUserByUsername("super");
        System.out.println(user.getLastLoginTime());
    }

    @Test
    @Transactional
    void registerTest(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setLastLoginTime(user.getCreateTime());
        user.setPassword("test_password");
        user.setUsername("test_name");
        userMapper.register(user);
    }
}
