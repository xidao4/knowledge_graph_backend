package com.sec.supernatural.backend_coin.bl;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**
 * @author shenyichen
 * @date 2021/3/31
 */
public interface StorageService {
    String storeFile(String fileStr, String filename);
    String storeImage(MultipartFile file);
    File load(String filename);
}
