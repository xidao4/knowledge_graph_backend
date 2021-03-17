package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${storage-dir}")
    private String storageDir;

    private Path root;

    private Logger logger = Logger.getLogger(getClass().getName());

    @PostConstruct
    public void initRoot() {
        logger.info("storage-dir = " + storageDir);
        root = Paths.get(storageDir);
        init();
    }
    @Override
    public void init() {
        logger.info("init storage dir!");
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            System.out.println("cannot create storage dir!");
            e.printStackTrace();
        }
    }

    @Override
    public String store(MultipartFile file, HttpServletRequest request) throws IOException {
        logger.info(file.getOriginalFilename());
        String prefix = DigestUtils.md5DigestAsHex(file.getBytes());
        String origin = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = origin.substring(origin.lastIndexOf('.'));
        String filename = prefix + ext;
        logger.info(filename);
        String res = "";
        if (file.isEmpty()) {
            System.out.println("unable to store empty file!");
            return res;
        }
        if (filename.contains("..")) {
            System.out.println("invalid path : " + filename);
            return res;
        }
        if (!exists(filename)) {
            try (InputStream inputStream = file.getInputStream()) {
                FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(storageDir + File.separator + filename));
                byte[] bs = new byte[1024];
                int len;
                while((len = fileInputStream.read(bs)) !=-1 ){
                    bos.write(bs, 0, len);
                }
                bos.flush();
                bos.close();
            } catch (IOException e) {
                System.out.println("failed to store file : " + filename);
                e.printStackTrace();
                return res;
            }
        }else{
            System.out.println("file already exists!");
//            String url = request.getScheme()+"://"+ "172.17.244.3"+":"+request.getServerPort()+"/api/admin/image/"+filename;
            String url = request.getScheme()+"://"+ "101.133.237.239"+":"+request.getServerPort()+"/api/admin/image/"+filename;
            res = url;
        }
        return res;
    }

    public boolean exists(String filename) {
        return Files.exists(root.resolve(filename));
    }
}
