package com.cniao5.app36kr.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.biz.CategoryDataManager;
import com.cniao5.app36kr.biz.HeadDataManager;
import com.cniao5.app36kr.common.Config;
import com.cniao5.app36kr.common.DefineView;
import com.cniao5.app36kr.entity.AdHeadBean;
import com.cniao5.app36kr.entity.CategoriesBean;
import com.cniao5.app36kr.fragment.base.BaseFragment;
import com.cniao5.app36kr.widget.AutoGallery;
import com.cniao5.app36kr.widget.FlowIndicator;
import com.cniao5.app36kr.widget.PullToRefreshListView;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class HomeFragment extends BaseFragment implements DefineView{
    private LayoutInflater mInflater;
    private View mView;
    public static final String ARG_PAGE = "extra";
    private CategoriesBean extraBean;
    private PullToRefreshListView lv_pulltorefresh;
    private View headView;
    private AutoGallery headline_image_gallery;
    private FlowIndicator headline_circle_indicator;

    public static HomeFragment newInstance(CategoriesBean extra) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PAGE,extra);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extraBean = (CategoriesBean)getArguments().getSerializable(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.home_frag_layout,container,false);
            mInflater=LayoutInflater.from(getActivity());
            headView=mInflater.inflate(R.layout.gallery_indicator_layout,null);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }

    @Override
    public void initView() {
        lv_pulltorefresh=(PullToRefreshListView)mView.findViewById(R.id.lv_pulltorefresh);
        lv_pulltorefresh.addHeaderView(headView);
        headline_image_gallery=(AutoGallery)headView.findViewById(R.id.headline_image_gallery);
        headline_circle_indicator=(FlowIndicator)headView.findViewById(R.id.headline_circle_indicator);

    }

    @Override
    public void initValidata() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(Config.CRAWLER_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                List<CategoriesBean> categoriesBeans = new CategoryDataManager().getCategoriesBeans(Jsoup.parse(response.body().string()));
                Log.d("zttjiangqq",new Gson().toJson(categoriesBeans));
            }
        });
    }

    @Override
    public void initListener() {
    }

    @Override
    public void bindData() {

    }
}
