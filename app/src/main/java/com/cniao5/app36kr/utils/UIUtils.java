package com.cniao5.app36kr.utils;

import android.annotation.SuppressLint;

import com.cniao5.app36kr.application.CNKApplication;
import com.cniao5.app36kr.widget.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class UIUtils {

    public static  final  String DEMO_PULL_TIME_KEY="demo_pull_time_key";

    /**
     * 设置上次更新数据时间
     * @param listView
     * @param key
     */
    public static void setPullToRefreshLastUpdated(PullToRefreshListView listView, String key) {
        SharedPreferencesHelper spHelper = SharedPreferencesHelper.getInstance(CNKApplication.getInstance());
        long lastUpdateTimeStamp = spHelper.getLongValue(key);
        listView.setLastUpdated(getUpdateTimeString(lastUpdateTimeStamp));
    }

    /**
     * 保存更新数据时间
     * @param listView
     * @param key
     */
    public static void savePullToRefreshLastUpdateAt(PullToRefreshListView listView, String key) {
        listView.onRefreshComplete();
        SharedPreferencesHelper spHelper = SharedPreferencesHelper.getInstance(CNKApplication.getInstance());
        long lastUpdateTimeStamp=System.currentTimeMillis();
        spHelper.putLongValue(key, lastUpdateTimeStamp);
        listView.setLastUpdated(getUpdateTimeString(lastUpdateTimeStamp));
    }

    /**
     * 更新时间字符串
     * @param timestamp
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getUpdateTimeString(long timestamp) {
        if (timestamp <= 0) {
            return "上次更新时间:";
        } else {
            String textDate = "上次更新时间:";
            Calendar now = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp);
            if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                    && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                    && c.get(Calendar.DATE) == now.get(Calendar.DATE)) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                return textDate += sdf.format(c.getTime());
            } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
                return textDate += sdf.format(c.getTime());
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                return textDate += sdf.format(c.getTime());
            }
        }
    }
}
