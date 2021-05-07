package com.sec.supernatural.backend_coin.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/3
 **/
@Getter
@Setter
@ToString
public class SearchResultVO {
    String picId;
    List<JSONObject> nodes;
    List<JSONObject> edges;
}
