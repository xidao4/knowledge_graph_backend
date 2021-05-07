package com.sec.supernatural.backend_coin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.util.JwtUtil;
import com.sec.supernatural.backend_coin.vo.LoginVO;
import com.sec.supernatural.backend_coin.vo.PicIdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Map;

/**
 * @author wangyuchen
 * @date 2021/5/7 8:06 下午
 */
@SpringBootTest
public class JWTTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String postTemplate(String url, Object object, String token, int expectStatusCode) throws Exception{

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .header((token!=null)?"token":"token2", (token!=null)?token:"w.w.w")
                .contentType("application/json;charset=UTF-8");

        if(object != null){
            String jsonResult = JSONObject.toJSONString(object);
            mockHttpServletRequestBuilder = mockHttpServletRequestBuilder.content(jsonResult);
        }


        MvcResult mvcResult = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(expectStatusCode))
                .andReturn();

        return mvcResult.getResponse().getContentAsString();
    }

    @DisplayName("测试无token访问")
    @Test
    void noTokenTest() throws Exception{
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        postTemplate("/api/graph/download", picIdVO,null, 401);
    }

    @DisplayName("测试token过期访问")
    @Test
    void tokenExpiredTest() throws Exception{
        String token = JwtUtil.createToken(1, 1, Calendar.SECOND);
        Thread.sleep(2000);
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.setPicId("60706cf7723fe7362650e27f");
        postTemplate("/api/graph/download", picIdVO, token,401);
    }
}
