package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author wangyuchen
 * @date 2021/3/27 10:51 上午
 */

@Mapper
@Repository
public interface UserMapper {
    User getUserByUsername(String username);
    int updateLastLoginTime(Integer userId, Date date);
    int register(User user);
}
