package com.sec.supernatural.backend_coin.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author shenyichen
 * @date 2021/4/14
 **/
@Getter
@Setter
@ToString
public class PicUnit {
    Integer picUnitId;
    Integer userId;
    String picId;
    String unitName;
    String url;
}
