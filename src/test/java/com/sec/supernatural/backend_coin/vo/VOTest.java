package com.sec.supernatural.backend_coin.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author shenyichen
 * @date 2021/5/7
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VOTest {
    @Test
    public void voTest(){
        // BindUrlToPicVO
        BindUrlToPicVO bindUrlToPicVO = new BindUrlToPicVO();
        bindUrlToPicVO.hashCode();
        bindUrlToPicVO.toString();
    }
}
