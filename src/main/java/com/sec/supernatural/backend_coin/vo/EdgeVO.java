package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

/**
 * @author shenyichen
 * @date 2021/4/16
 **/
@Data
public class EdgeVO {
    String id;
    String label;
    String type;
    String source;
    String target;

    public EdgeVO(){

    }

    public EdgeVO(String id,String label,String type,String source,String target){
        this.id = id;
        this.label = label;
        this.type = type;
        this.source = source;
        this.target = target;
    }
}
