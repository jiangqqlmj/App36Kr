package com.cniao5.app36kr.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.adapter.HomeRecyclerAdapter;
import com.cniao5.app36kr.biz.HeadDataManager;
import com.cniao5.app36kr.biz.HomeNewsDataManager;
import com.cniao5.app36kr.common.Config;
import com.cniao5.app36kr.common.DefineView;
import com.cniao5.app36kr.entity.CategoriesBean;
import com.cniao5.app36kr.entity.HomeNewsBean;
import com.cniao5.app36kr.fragment.base.BaseFragment;
import com.cniao5.app36kr.widget.NewsDecoration;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
public class PageFragment extends BaseFragment implements DefineView{
    private View mView;
    private Request request;
    public static final String ARG_PAGE = "extra";
    private CategoriesBean extraBean;
    private List<HomeNewsBean> homeNewsBeans;   //新闻列表数据
    private RecyclerView home_recyclerview;
    private LinearLayoutManager linearLayoutManager;
    private HomeRecyclerAdapter adapter;
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
    }

    @Override
    public void initValidata() {
        linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        if(extraBean.getData_type().equals("tv")){
              adapter=new HomeRecyclerAdapter(getActivity(),0);
        }else {
              adapter=new HomeRecyclerAdapter(getActivity(),1);
        }
        //设置固定大小
        home_recyclerview.setHasFixedSize(true);
        home_recyclerview.setLayoutManager(linearLayoutManager);
        home_recyclerview.addItemDecoration(new NewsDecoration(getActivity(),OrientationHelper.VERTICAL));
        //进行获取头部广告数据和底部列表数据
        request=new Request.Builder().url(extraBean.getHref()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                Document document= Jsoup.parse(response.body().byteStream(), "UTF-8", Config.CRAWLER_URL);
                homeNewsBeans=new HomeNewsDataManager().getHomeNewsBeans(document);
                if(homeNewsBeans!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bindData();
                            Log.d("zttjiangqq", homeNewsBeans.toString());
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
        adapter.setHomeNewsBeans(homeNewsBeans);
        home_recyclerview.setAdapter(adapter);
    }
}
