package com.cniao5.app36kr.biz;

import com.cniao5.app36kr.entity.RecentNewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:近期获取数据解析工具类
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class RecentDataManager {
    public static List<RecentNewsBean> getRecentDatas(String data){
        List<RecentNewsBean> recentNewsBeans=null;
        try {
            recentNewsBeans=new ArrayList<RecentNewsBean>();
            JSONObject result_object=new JSONObject(data);
            JSONObject data_object=result_object.getJSONObject("data");
            JSONArray datas_array= data_object.getJSONArray("data");
            Gson gson=new Gson();
            recentNewsBeans = gson.fromJson(datas_array.toString(), new TypeToken<List<RecentNewsBean>>() {
            }.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recentNewsBeans;
    }
}
