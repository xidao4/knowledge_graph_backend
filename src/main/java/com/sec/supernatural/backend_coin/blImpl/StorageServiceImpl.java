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
        System.out.println("root: " + root);
        init();
    }

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
        }
        return "https://118.182.96.49:8001/api/storage/file/"+filename;
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
        if(origin==null || origin.length()==0){
            origin = ".jpg";
        }
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
                String url = "https://118.182.96.49:8001/api/storage/image/"+filename;
                res = url;
            } catch (IOException e) {
                System.out.println("failed to store file : " + filename);
                e.printStackTrace();
                return res;
            }
        }else{
            System.out.println("img already exists!");
            String url = "https://118.182.96.49:8001/api/storage/image/"+filename;
            res = url;
        }
        return res;
    }

    @Override
    public File load(String filename) {
        File file = root.resolve(filename).toFile();
//        try {
//            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
//            String s = "哈哈我de不出来啦";
//            fos.write(s.getBytes());
//            fos.close();
//        } catch (Exception e){
//            e.printStackTrace();
//            System.out.println("写入失败");
//            System.out.println(e.getMessage());
//            System.out.println(e.getStackTrace());
//        }
        System.out.println("path"+file.getAbsolutePath());
        String str=System.getProperty("user.dir");
        System.out.println(str);
        File dir = new File(storageDir);
        String[] child = dir.list();
        if (child == null) {
            System.out.println("empty!!!");

        } else {
            System.out.println("not empty");
            for (int i = 0; i < child.length; i++) {
                String str1 = child[i];
                System.out.println(str1);

            }
        }
        if(file.exists()){
        }else if(file.canRead()){
            System.out.println("[DEBUG] file not exists!");
        }else{
            System.out.println("[DEBUG] file not exists and not readable: failed to read file : " + filename);
        }
        return file;
    }

    public boolean exists(String filename) {
        return Files.exists(root.resolve(filename));
    }
}
