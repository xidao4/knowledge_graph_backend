package com.sec.supernatural.backend_coin.util;

import java.util.Map;

public class StringTools {


    public static void properties2Str(String var, StringBuilder sb,Map<String,Object> properties){
        for(Map.Entry<String, Object> entry: properties.entrySet()){
            sb.append(var).append(".").append(entry.getKey()).append("=");
            if(entry.getValue() instanceof Number){
                sb.append(entry.getValue());
            }else{
                sb.append("\"").append(entry.getValue()).append("\"");
            }
            sb.append(",");
        }
    }
    public static String deleteEndComma(StringBuilder sb){
        final char comma = ',';
        if(sb.charAt(sb.length() - 1) == comma){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
