package com.sec.supernatural.backend_coin.po;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 图谱的所有信息
 * @author shenyichen
 * @date 2021/3/31
 */
@Getter
@Setter
@ToString
@Document(collection = "Graph")
public class Graph {
    @Id
    String picId;
    JSONArray fnodes;
    JSONArray fedges;
    JSONArray snodes;
    JSONArray sedges;
}
