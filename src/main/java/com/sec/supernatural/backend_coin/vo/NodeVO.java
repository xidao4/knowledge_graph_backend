package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

/**
 * @author shenyichen
 * @date 2021/4/16
 **/
@Data
public class NodeVO {
    String id;
    String label;
    String type;

    public NodeVO(){

    }

    public NodeVO(String id, String label,String type){
        this.id = id;
        this.label = label;
        this.type = type;
    }
}
