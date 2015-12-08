package com.cniao5.app36kr.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.adapter.HomeRecyclerAdapter;
import com.cniao5.app36kr.biz.HeadDataManager;
import com.cniao5.app36kr.biz.HomeNewsDataManager;
import com.cniao5.app36kr.biz.RecentDataManager;
import com.cniao5.app36kr.common.Config;
import com.cniao5.app36kr.common.DefineView;
import com.cniao5.app36kr.common.RequestURL;
import com.cniao5.app36kr.entity.CategoriesBean;
import com.cniao5.app36kr.entity.HomeNewsBean;
import com.cniao5.app36kr.entity.RecentNewsBean;
import com.cniao5.app36kr.fragment.base.BaseFragment;
import com.cniao5.app36kr.utils.PathUtils;
import com.cniao5.app36kr.widget.NewsDecoration;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class PageFragment extends BaseFragment implements DefineView{
    private View mView;
    private Request request;
    public static final String ARG_PAGE = "extra";
    private CategoriesBean extraBean;
    private List<HomeNewsBean> homeNewsBeans;   //新闻列表数据
    private List<RecentNewsBean> recentNewsBeans;  //近期活动列表数据
    private RecyclerView home_recyclerview;
    private LinearLayoutManager linearLayoutManager;
    private HomeRecyclerAdapter adapter;

    //加载promt 提醒的布局相关
    private FrameLayout prompt_framelayout;
    private LinearLayout loading;
    private LinearLayout empty;
    private LinearLayout error;

    //进行分页效果--主要用于近期活动列表
    private int page=1;      //页码 默认为第一页
    private int pageSize=20;   //每页的item数量

    public static PageFragment newInstance(CategoriesBean extra) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PAGE, extra);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extraBean = (CategoriesBean)getArguments().getSerializable(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.one_frag_layout,container,false);
            initView();
            initValidata();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        home_recyclerview=(RecyclerView)mView.findViewById(R.id.home_recyclerview);

        prompt_framelayout=(FrameLayout)mView.findViewById(R.id.prompt_framelayout);
        loading=(LinearLayout)mView.findViewById(R.id.loading);
        empty=(LinearLayout)mView.findViewById(R.id.empty);
        error=(LinearLayout)mView.findViewById(R.id.error);
    }

    @Override
    public void initValidata() {

        //设置控件显示状态
        home_recyclerview.setVisibility(View.GONE);
        prompt_framelayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);

        linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        if(extraBean.getData_type().equals("tv")) {
            adapter = new HomeRecyclerAdapter(getActivity(), 0);
        }else if(extraBean.getData_type().equals("recent")){
            //近期活动
            adapter = new HomeRecyclerAdapter(getActivity(), 2);
        }else{
            adapter=new HomeRecyclerAdapter(getActivity(),1);
        }
        //设置固定大小
        home_recyclerview.setHasFixedSize(true);
        home_recyclerview.setLayoutManager(linearLayoutManager);
        home_recyclerview.addItemDecoration(new NewsDecoration(getActivity(),OrientationHelper.VERTICAL));
        //进行获取头部广告数据和底部列表数据
        if(!extraBean.getData_type().equals("recent")) {
            request=new Request.Builder().url(extraBean.getHref()).build();
        }else{
            Map<String,String> params=new HashMap<String,String>();
            params.put("page",String.valueOf(page));
            params.put("pageSize", String.valueOf(pageSize));
            String url=PathUtils.getRequestPath(RequestURL.RECENT_URL,params);
            request=new Request.Builder().url(url).build();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (!extraBean.getData_type().equals("recent")) {
                    Document document = Jsoup.parse(response.body().byteStream(), "UTF-8", Config.CRAWLER_URL);
                    homeNewsBeans = new HomeNewsDataManager().getHomeNewsBeans(document);
                    if (homeNewsBeans != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bindData();
                            }
                        });
                    }
                }else {
                    recentNewsBeans = RecentDataManager.getRecentDatas(response.body().string());
                    Log.d("zttjiangqq", recentNewsBeans.toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bindData();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        if(!extraBean.getData_type().equals("recent")) {
            if (homeNewsBeans != null) {
                //设置控件显示状态
                home_recyclerview.setVisibility(View.VISIBLE);
                prompt_framelayout.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                adapter.setHomeNewsBeans(homeNewsBeans);
                home_recyclerview.setAdapter(adapter);
            } else {
                setEmptyView();
            }
        }else{
            if(recentNewsBeans!=null) {
                home_recyclerview.setVisibility(View.VISIBLE);
                prompt_framelayout.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                adapter.setRecentNewsBeans(recentNewsBeans);
                home_recyclerview.setAdapter(adapter);
            }else {
                setEmptyView();
            }
        }
    }

    /**
     * 进行设置数据暂时为空  提示语
     */
    private void setEmptyView(){
        home_recyclerview.setVisibility(View.GONE);
        prompt_framelayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }
}
