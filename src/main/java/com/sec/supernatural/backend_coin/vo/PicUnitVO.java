package com.sec.supernatural.backend_coin.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shenyichen
 * @date 2021/4/14
 **/
@Data
public class PicUnitVO {
    Integer userId;
    String picId;
    String unitName;
    MultipartFile file;

    public PicUnitVO(){

    }

    public PicUnitVO(Integer userId,String picId, String picName, MultipartFile file){
        this.setUserId(userId);
        this.setPicId(picId);
        this.setUnitName(picName);
        this.setFile(file);
    }
}
