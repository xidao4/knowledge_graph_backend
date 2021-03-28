package com.sec.supernatural.backend_coin.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wangyuchen
 * @date 2021/3/27 10:50 上午
 */
@Slf4j
public class MD5Encryption {
    public static String encrypt(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = s.getBytes();
            byte[] output = md.digest(input);
            // 6位编码
            return Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return s;
        }
    }
}

