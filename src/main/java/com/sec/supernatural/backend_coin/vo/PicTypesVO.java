package com.sec.supernatural.backend_coin.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author shenyichen
 * @date 2021/4/3
 */
@Data
public class PicTypesVO {
    Map<String, List<JSONObject>> fnodesMap;
    Map<String, List<JSONObject>> snodesMap;
}
