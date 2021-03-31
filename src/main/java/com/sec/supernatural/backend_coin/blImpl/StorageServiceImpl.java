package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.StorageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * @author shenyichen
 * @date 2021/3/31
 */
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
    public String storeFile(String fileStr, String filename) {
        String res = "";
        if (fileStr.isEmpty()) {
            System.out.println("unable to store empty file!");
            return res;
        }
        if (filename.contains("..")) {
            System.out.println("invalid path : " + filename);
            return res;
        }
        if (!exists(filename)) {
            File file = root.resolve(filename).toFile();
            try{
                PrintStream ps = new PrintStream(new FileOutputStream(file));
                ps.println(fileStr);
                ps.close();
            }catch (FileNotFoundException e){
                System.out.println("failed to store file : " + filename);
                e.printStackTrace();
                return res;
            }
        }else{
            System.out.println("file already exists!");
            String url = "https://118.182.96.49:9020/api/storage/file/"+filename;
            res = url;
        }
        return res;
    }

    @Override
    public String storeImage(MultipartFile file) {
        String res = "";
        logger.info(file.getOriginalFilename());
        String prefix;
        try {
            prefix = DigestUtils.md5DigestAsHex(file.getBytes());
        }catch (IOException e){
            System.out.println("fail to get bytes from file !");
            return res;
        }
        String origin = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = origin.substring(origin.lastIndexOf('.'));
        String filename = prefix + ext;
        logger.info(filename);
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
                //压缩
                BufferedImage BI = ImageIO.read(inputStream);
                double srcWidth = BI.getWidth(); // 源图宽度
                double srcHeight = BI.getHeight(); // 源图高度
                int destWidth = (int) srcWidth;
                int destHeight = (int) srcHeight;
                if(srcWidth>710){
                    destWidth = 710;
                    destHeight = (int) (destWidth * srcHeight / srcWidth);
                }
                File f = root.resolve(filename).toFile();
                Thumbnails.of(BI).size(destHeight,destWidth).toFile(f);
                String url = "https://118.182.96.49:9020/api/storage/image/"+filename;
                res = url;
            } catch (IOException e) {
                System.out.println("failed to store file : " + filename);
                e.printStackTrace();
                return res;
            }
        }else{
            System.out.println("img already exists!");
            String url = "https://118.182.96.49:9020/api/storage/image/"+filename;
            res = url;
        }
        return res;
    }

    @Override
    public Resource load(String filename) {
        try {
            Resource resource = new UrlResource(root.resolve(filename).toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                System.out.println("failed to read file : " + filename);
                return null;
            }
        } catch (MalformedURLException e) {
            System.out.println("failed to read file : " + filename);
            return null;
        }
    }

    public boolean exists(String filename) {
        return Files.exists(root.resolve(filename));
    }
}
