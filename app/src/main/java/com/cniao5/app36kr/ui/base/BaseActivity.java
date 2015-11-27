package com.cniao5.app36kr.ui.base;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
/**
 * 当前类注释:基类Activity 继承自FragmentActivity
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void openActivity(Class<?> pClass){
        Intent mIntent=new Intent(this,pClass);
        this.startActivity(mIntent);
    }
}
