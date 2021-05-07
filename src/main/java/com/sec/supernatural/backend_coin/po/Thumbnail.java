package com.sec.supernatural.backend_coin.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author shenyichen
 * @date 2021/4/1
 */
@Getter
@Setter
@ToString
public class Thumbnail {
    Integer userId;
    String picId;
    String picName;
    String url;
}
