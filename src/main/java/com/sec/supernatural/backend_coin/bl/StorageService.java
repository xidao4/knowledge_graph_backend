package com.sec.supernatural.backend_coin.bl;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/3/31
 */
public interface StorageService {
    public String storeFile(String fileStr, String filename);
    public Resource load(String filename)
    public void init();
}
