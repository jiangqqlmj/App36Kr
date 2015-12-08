package com.cniao5.app36kr.utils;

import java.net.URLEncoder;
import java.util.Map;

/**
 * 当前类注释:地址处理类
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class PathUtils {
    public static String getRequestPath(String url,Map<String,String> params){
        if(url==null||url.equals("")){
            return "";
        }
        if(params!=null){
            StringBuffer sb=new StringBuffer(url);
            if(!url.contains("?")){
                sb.append("?");
            }
            for (Map.Entry<String,String> entry:params.entrySet()) {
                String key=entry.getKey().toString();
                String value=null;
                if(entry.getValue()==null){
                    value="";
                }else{
                    value=entry.getValue().toString();
                }
                sb.append(key);
                sb.append("=");
                try {
                    value= URLEncoder.encode(value,"UTF-8");
                    sb.append(value);
                }catch (Exception e){
                    e.printStackTrace();
                }
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);
            return  sb.toString();
        }
        return "";
    }
}
