package com.sec.supernatural.backend_coin.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/3
 **/
@Data
public class SearchResultVO {
    String picId;
    List<JSONObject> nodes;
    List<JSONObject> edges;
}
