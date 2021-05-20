package com.sec.supernatural.backend_coin.util;

import com.auth0.jwt.exceptions.JWTCreationException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangyuchen
 * @date 2021/5/7 9:13 下午
 */
@SpringBootTest
public class MD5Test {
    @Test
    public void constructorTest() throws JWTCreationException {
        MD5Encryption md5Encryption = new MD5Encryption();
    }
}
