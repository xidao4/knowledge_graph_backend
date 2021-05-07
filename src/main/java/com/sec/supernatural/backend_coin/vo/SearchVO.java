package com.sec.supernatural.backend_coin.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author shenyichen
 * @date 2021/3/29
 */
@Getter
@Setter
@ToString
public class SearchVO {
    Integer userId;
    String picId;
    String keyWord;
}
