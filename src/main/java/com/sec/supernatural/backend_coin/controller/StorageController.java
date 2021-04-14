package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author shenyichen
 * @date 2021/3/31
 */
@RestController
@RequestMapping("/api/storage")
public class StorageController {
    @Autowired
    StorageService storageService;

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) {
        ResponseEntity<byte[]> response = null;
        File file = storageService.load(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        try {
            response = new ResponseEntity<byte[]>(getBytesFromFile(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<byte[]> previewResume(@PathVariable String filename) {
        ResponseEntity<byte[]> response = null;
        File file = storageService.load(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        try {
            response = new ResponseEntity<byte[]>(getBytesFromFile(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);// 获取文件大小
        long lengths = file.length();
        System.out.println("lengths = " + lengths);
        if (lengths > Integer.MAX_VALUE) {
            // 文件太大，无法读取
            throw new IOException("File is to large "+file.getName());
        }
        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int)lengths];// 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
