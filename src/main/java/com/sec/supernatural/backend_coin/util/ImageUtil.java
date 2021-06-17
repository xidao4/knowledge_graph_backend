package com.sec.supernatural.backend_coin.util;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.binary.Base64;


/**
 * @author yanyugang
 * @description ImageUtil
 * @date 2019-08-05 10:58
 */
public class ImageUtil {
    // MultipartFile转BASE64字符串
    public static String multipartFileToBASE64(MultipartFile mFile) throws Exception{
        String[] suffixArra=mFile.getOriginalFilename().split("\\.");
//        String preffix="data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
        String base64EncoderImg=Base64.encodeBase64String(mFile.getBytes()).replaceAll("[\\s*\t\n\r]", "");
        return base64EncoderImg;
    }
}
