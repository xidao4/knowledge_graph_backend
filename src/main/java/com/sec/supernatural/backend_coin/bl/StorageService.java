package com.sec.supernatural.backend_coin.bl;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface StorageService {
    public String store(MultipartFile file, HttpServletRequest request) throws IOException;
    public void init();
}
