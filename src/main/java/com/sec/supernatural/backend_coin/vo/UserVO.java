package com.sec.supernatural.backend_coin.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangyuchen
 * @date 2021/3/27 11:07 上午
 */
@Getter
@Setter
@ToString
public class UserVO {
    Integer userId;
    String username;
    String createTime;
    String lastLoginTime;
    String token;
    String userType;
}
