package com.sec.supernatural.backend_coin.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 图谱的所有信息
 * @author shenyichen
 * @date 2021/4/3
 */
@Getter
@Setter
@ToString
public class History {
    Integer userId;
    String keyword;
    String createTime;
}
