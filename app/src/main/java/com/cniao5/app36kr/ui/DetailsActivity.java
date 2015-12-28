package com.cniao5.app36kr.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.common.DefineView;
import com.cniao5.app36kr.ui.base.BaseActivity;

/**
 * 当前类注释:文章详情界面
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class DetailsActivity extends BaseActivity implements DefineView{
    private Button btn_back,btn_share,btn_font,btn_night;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        setStatusBar();
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        //回退
        btn_back=(Button)this.findViewById(R.id.btn_back);
        //分享
        btn_share=(Button)this.findViewById(R.id.btn_share);
        //字体
        btn_font=(Button)this.findViewById(R.id.btn_font);
        //白天/黑夜切换
        btn_night=(Button)this.findViewById(R.id.btn_night);
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        btn_back.setOnClickListener(new CustomOnClickListener());
        btn_share.setOnClickListener(new CustomOnClickListener());
        btn_font.setOnClickListener(new CustomOnClickListener());
        btn_night.setOnClickListener(new CustomOnClickListener());

    }
    @Override
    public void bindData() {

    }
    /**
     *  按钮点击自定义监听器
     */
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
          switch (v.getId()){
              case  R.id.btn_back:
                  finishActivity();
                  break;
              case  R.id.btn_share:
                  showShortToast("点击了分享...");
                  break;
              case  R.id.btn_font:
                  showShortToast("点击了字体...");
                  break;
              case  R.id.btn_night:
                  showShortToast("点击了白天/黑夜切换...");
                  break;
          }
        }
    }
}
