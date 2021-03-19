package com.sec.supernatural.backend_coin;

//import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.bl.GraphService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class FileTransferTest {
    @Autowired
    GraphService graphService;

    @Test
    void testDao2Json(){
        MultipartFile  mFile = graphService.dao2Json(0);
        System.out.println(mFile.getOriginalFilename());
    }

    @Test
    void testJson2Dao(){
        Path path = Paths.get("C:\\Users\\admin\\Desktop\\test.txt");
        File file = new File(path.toUri());
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
            graphService.json2Dao(multipartFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
