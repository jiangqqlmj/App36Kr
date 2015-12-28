package com.cniao5.app36kr.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.biz.ArticleDataManager;
import com.cniao5.app36kr.biz.HeadDataManager;
import com.cniao5.app36kr.biz.HomeNewsDataManager;
import com.cniao5.app36kr.common.Config;
import com.cniao5.app36kr.common.DefineView;
import com.cniao5.app36kr.entity.ArticleBean;
import com.cniao5.app36kr.entity.HomeNewsBean;
import com.cniao5.app36kr.ui.base.BaseActivity;
import com.cniao5.app36kr.widget.RoundAngleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
    private HomeNewsBean homeNewsBean; //新闻Item 实体类信息
    private ArticleBean articleBean; //新闻详情信息
    private ImageLoader mImageLoader;
    private Intent mIntent;
    private Request request;

    private TextView details_title;
    private RoundAngleImageView details_avatar;
    private TextView details_name;
    private TextView details_time;
    private ImageView details_ad;
    private WebView details_content;
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

        details_title=(TextView)this.findViewById(R.id.details_title);
        details_avatar=(RoundAngleImageView)this.findViewById(R.id.details_avatar);
        details_name=(TextView)this.findViewById(R.id.details_name);
        details_time=(TextView)this.findViewById(R.id.details_time);
        details_ad=(ImageView)this.findViewById(R.id.details_ad);
        details_content=(WebView)this.findViewById(R.id.details_content);
    }

    @Override
    public void initValidata() {
        mImageLoader=ImageLoader.getInstance();
        if(( mIntent=getIntent())!=null){
           Bundle bundle=mIntent.getExtras();
           homeNewsBean=(HomeNewsBean)bundle.getSerializable("news_bean");
           request=new Request.Builder().url(homeNewsBean.getHref()).build();
           client.newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(Request request, IOException e) {
               }
               @Override
               public void onResponse(Response response) throws IOException {
                   Document document = Jsoup.parse(response.body().byteStream(), "UTF-8", Config.CRAWLER_URL);
                   articleBean=new ArticleDataManager(homeNewsBean.gettId()).getArticleBean(document);
                   DetailsActivity.this.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           bindData();
                       }
                   });
               }
           });
       }

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
       if(articleBean!=null){
           details_title.setText(articleBean.getTitle());
           mImageLoader.displayImage(articleBean.getAuthorBean().getAvatar(), details_avatar);
           details_name.setText(articleBean.getAuthorBean().getName());
           details_time.setText(" 发表于 "+articleBean.getDatetext());
           mImageLoader.displayImage(articleBean.getHeadImage(), details_ad);
           //进行加载HTML 文章内容
           details_content.loadDataWithBaseURL(Config.CRAWLER_URL,articleBean.getContext(),"html/text","UTF-8","");
       }
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
