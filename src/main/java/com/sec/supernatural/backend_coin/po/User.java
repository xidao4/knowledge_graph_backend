package com.sec.supernatural.backend_coin.po;

import lombok.Data;

import java.util.Date;

/**
 * @author wangyuchen
 * @date 2021/3/27 11:08 上午
 */
@Data
public class User {
    Integer userId;
    String username;
    String password;
    Date createTime;
    Date lastLoginTime;
}
