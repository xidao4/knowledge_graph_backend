package com.sec.supernatural.backend_coin.constant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.Supplier;

/**
 * @author shenyichen
 * @date 2021/5/7
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ConstantTest {
    @Test
    public void myResponseTest(){
        MyResponse myResponse = new MyResponse(ResponseCode.OK);
        MyResponse.checkForbidden(true);
        MyResponse.checkForbidden(false);
        MyResponse.checkNull(null);
        MyResponse.checkNull(myResponse);
    }
}
