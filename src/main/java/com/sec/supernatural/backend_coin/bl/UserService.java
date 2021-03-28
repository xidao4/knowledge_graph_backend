package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.LoginVO;

/**
 * @author wangyuchen
 * @date 2021/3/27 10:47 上午
 */
public interface UserService {
    MyResponse login(LoginVO loginVO);
}
