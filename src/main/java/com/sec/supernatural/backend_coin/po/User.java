package com.sec.supernatural.backend_coin.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author wangyuchen
 * @date 2021/3/27 11:08 上午
 */
@Getter
@Setter
@ToString
public class User {
    Integer userId;
    String username;
    String password;
    Date createTime;
    Date lastLoginTime;
}
