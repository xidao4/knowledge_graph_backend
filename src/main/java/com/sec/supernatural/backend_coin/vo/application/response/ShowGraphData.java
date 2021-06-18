package com.sec.supernatural.backend_coin.vo.application.response;

import lombok.Data;

import java.util.List;

@Data
public class ShowGraphData {
    List<NodeDisplay> nodes;
    List<EdgeDisplay> edges;


}
