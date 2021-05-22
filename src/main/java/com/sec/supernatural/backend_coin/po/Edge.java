package com.sec.supernatural.backend_coin.po;

import java.util.HashMap;
import java.util.Map;

import static com.sec.supernatural.backend_coin.util.StringTools.deleteEndComma;
import static com.sec.supernatural.backend_coin.util.StringTools.properties2Str;

public class Edge {
    private String picId;
    private String source;
    private String target;
    //以上三者单独列出，因为插入要靠三者来定位，target和source就没必要加入properties
    private Map<String,Object> properties;
    //label,type
    //picId(insert的时候，需要向properties中增加edge的picId)

    public Edge(String source, String target, String picId,Map<String,Object> properties){
        this.picId = picId;
        this.source = source;
        this.target = target;
        this.properties = properties;
        //attention：insert的时候，需要向properties中增加edge的picId
        properties.put("picId",this.picId);
    }
    public Edge(){properties=new HashMap<>();}



    public String getPropertiesAsString(String var){
        StringBuilder propertiesStr=new StringBuilder();
//        Map<String,Object> edgePropertiesForInsert=new HashMap<>();
//        for(Map.Entry<String,Object> entry:properties.entrySet()){
//            edgePropertiesForInsert.put(entry.getKey(),entry.getValue());
//        }
//        edgePropertiesForInsert.put("picId",this.picId);
//        properties2Str(var,propertiesStr,edgePropertiesForInsert);
        properties2Str(var,propertiesStr,properties);
        return deleteEndComma(propertiesStr);
    }





    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
