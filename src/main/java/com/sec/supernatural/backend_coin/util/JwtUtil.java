package com.sec.supernatural.backend_coin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyuchen
 * @date 2021/3/27 11:13 上午
 */

import java.util.*;
@Slf4j
public class JwtUtil {
    public static final String TOKEN_NAME = "token";
    static final String SIGNATURE = "supernatural-coin-token";
    static final String ISSUSER = "supernatural";
    static final String SUBJECT = "this is supernatural coin token";
    static final String AUDIENCE = "WEB";

    public static String createToken(Integer userId, Integer expireTime, int expireTimeType) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SIGNATURE);
            Map<String, Object> map = new HashMap<>();
            Date nowDate = new Date();
            Calendar instance = Calendar.getInstance();
            instance.add(expireTimeType, expireTime);
            Date expireDate = instance.getTime();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            return JWT.create()
                    // 设置头部信息 Header
                    .withHeader(map)
                    // 设置 载荷 Payload
                    .withClaim("userId", userId)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    // 生成签名的时间
                    .withIssuedAt(nowDate)
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    // 签名 Signature
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public static DecodedJWT verifyTokenAndGetUserId(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SIGNATURE);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUSER)
                .build();
        return verifier.verify(token);
    }
}

