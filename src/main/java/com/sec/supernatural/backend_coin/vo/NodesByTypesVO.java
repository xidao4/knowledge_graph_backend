package com.sec.supernatural.backend_coin.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author shenyichen
 * @date 2021/4/6
 **/
@Getter
@Setter
@ToString
public class NodesByTypesVO {
    Map<String, List<JSONObject>> fnodesMap;
    Map<String, List<JSONObject>> snodesMap;
}
