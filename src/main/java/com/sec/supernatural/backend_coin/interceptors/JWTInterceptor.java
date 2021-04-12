package com.sec.supernatural.backend_coin.interceptors;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.supernatural.backend_coin.util.JwtUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyuchen
 * @date 2021/3/28 1:28 下午
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Value("${token.tokenExpireTime}")
    Integer tokenExpireTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>();
        if(null == token){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            map.put("msg", "请登录");
            map.put("code", -1);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(new ObjectMapper().writeValueAsString(map));
            return false;
        }
        try {
            DecodedJWT jwt = JwtUtil.verifyTokenAndGetUserId(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("userId");
            Integer userId = claim.asInt();
            if(null == userId){
                response.setHeader("token", JwtUtil.createToken(userId, tokenExpireTime));
                response.setStatus(HttpStatus.SC_NOT_ACCEPTABLE);
            }
            return true;
        } catch (TokenExpiredException e){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            map.put("msg", "请重新登录");
            map.put("code", -1);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(new ObjectMapper().writeValueAsString(map));
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
