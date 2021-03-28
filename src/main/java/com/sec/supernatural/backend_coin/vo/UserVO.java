package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

/**
 * @author wangyuchen
 * @date 2021/3/27 11:07 上午
 */
@Data
public class UserVO {
    Integer userId;
    String username;
    String createTime;
    String lastLoginTime;
    String token;
}
