package com.sec.supernatural.backend_coin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/16
 **/
@Data
public class GraphVO {
    List<NodeVO> nodes;
    List<EdgeVO> edges;
}
