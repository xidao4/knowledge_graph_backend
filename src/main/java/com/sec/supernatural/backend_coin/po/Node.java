package com.sec.supernatural.backend_coin.po;


import java.util.HashMap;
import java.util.Map;

import static com.sec.supernatural.backend_coin.util.StringTools.*;

//包含id(即为'x'id),picId,label,categories,info,value,(img,ending,reason等)
public class Node {
    //为了方便拓展，用map，而不是直接传参
    //pid,picId,label,categories,info,value，
    // 以及，JSON/CSV自带的字段(可拓展)
    private Map<String,Object> properties;//不包含id,因为觉得对推理没有用处。当然neo4j会自动生成一个neo4jId

    public Node(Map<String,Object> properties){
        this.properties=properties;
    }
    public Node(){
        this.properties=new HashMap<>();
    }
    public Node(Map<String,Object> properties,String picId){
        this.properties=properties;
        this.properties.put("picId",picId);
    }

    public String getPropertiesAsString(String var){
        StringBuilder propertiesStr=new StringBuilder();
        properties2Str(var,propertiesStr,properties);
        return deleteEndComma(propertiesStr);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
