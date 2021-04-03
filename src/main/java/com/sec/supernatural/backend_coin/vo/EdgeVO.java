package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

/**
 * @author shenyichen
 * @date 2021/4/3
 **/
@Data
public class EdgeVO {
    public EdgeVO() {
    }
    public EdgeVO(String label,String source,String target,String type){
        this.label = label;
        this.source = source;
        this.target = target;
        this.type = type;
    }
    String label;
    String source;
    String target;
    String type;
}
