package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.UserService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.data.UserMapper;
import com.sec.supernatural.backend_coin.po.User;
import com.sec.supernatural.backend_coin.util.JwtUtil;
import com.sec.supernatural.backend_coin.util.MD5Encryption;
import com.sec.supernatural.backend_coin.vo.LoginVO;
import com.sec.supernatural.backend_coin.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wangyuchen
 * @date 2021/3/27 10:47 上午
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXISTED = "用户不存在";
    private static final String PASSWORD_ERROR = "密码错误";

    @Autowired
    UserMapper userMapper;

    @Value("${token.tokenExpireTime}")
    Integer tokenExpireTime;

    @Override
    public MyResponse login(LoginVO loginVO) {
        User user = userMapper.getUserByUsername(loginVO.getUsername());
        if(null == user){
            return MyResponse.error(USER_NOT_EXISTED);
        }
        if(user.getPassword().equals(MD5Encryption.encrypt(loginVO.getPassword()))){
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            Date date = new Date();
            userMapper.updateLastLoginTime(userVO.getUserId(), date);
            userVO.setCreateTime(user.getCreateTime().toString());
            userVO.setLastLoginTime(date.toString());
            userVO.setToken(JwtUtil.createToken(userVO.getUserId(), tokenExpireTime));
            return MyResponse.ok(userVO);
        }
        else {
            return MyResponse.error(PASSWORD_ERROR);
        }
    }
}
