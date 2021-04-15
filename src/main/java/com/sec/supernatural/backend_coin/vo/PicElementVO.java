package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

/**
 * @author shenyichen
 * @date 2021/4/16
 **/
@Data
public class PicElementVO {
    String customizeImgUrl;
    String customizeEntityName;

    public PicElementVO(){

    }

    public PicElementVO(String customizeImgUrl, String customizeEntityName){
        this.customizeImgUrl = customizeImgUrl;
        this.customizeEntityName = customizeEntityName;
    }
}
