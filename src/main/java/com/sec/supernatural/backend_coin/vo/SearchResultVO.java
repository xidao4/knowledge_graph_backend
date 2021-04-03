package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/3
 **/
@Data
public class SearchResultVO {
    String picId;
    List<String> nodes;
    List<EdgeVO> edges;
}
