package com.cniao5.app36kr.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.adapter.FixedPagerAdapter;
import com.cniao5.app36kr.entity.CategoriesBean;
import com.cniao5.app36kr.fragment.base.BaseFragment;
import com.cniao5.app36kr.ui.MainActivity;
import com.cniao5.app36kr.utils.CategoryDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:采用TabLayout进行打造顶部新闻标签Tab Item效果
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class TabInfoFragment  extends BaseFragment implements ViewPager.OnPageChangeListener{
    //初始化分类分类Tab信息
    private static List<CategoriesBean> categoriesBeans= CategoryDataUtils.getCategoryBeans();
    private View mView;
    private TabLayout tab_layout;
    private ViewPager info_viewpager;
    private List<Fragment> fragments;
    private FixedPagerAdapter mPagerAdater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.tab_info_fragment,container,false);
            initViews();
            initValidata();
        }
        return mView;
    }
    private void initViews(){
        tab_layout=(TabLayout)mView.findViewById(R.id.tab_layout);
        info_viewpager=(ViewPager)mView.findViewById(R.id.info_viewpager);
    }
    private void initValidata(){
        fragments=new ArrayList<>();
        for(int i=0;i<12;i++){
            BaseFragment fragment=null;
            if(i==0){
                //首页
                fragment= HomeFragment.newInstance(categoriesBeans.get(i));
            }else{
                fragment= PageFragment.newInstance(categoriesBeans.get(i));
            }
            fragments.add(fragment);
        }
        //创建Fragment的 ViewPager 自定义适配器
        mPagerAdater=new FixedPagerAdapter(getChildFragmentManager());
        //设置显示的标题
        mPagerAdater.setCategoriesBeans(categoriesBeans);
        //设置需要进行滑动的页面Fragment
        mPagerAdater.setFragments(fragments);

        info_viewpager.setAdapter(mPagerAdater);
        info_viewpager.setOnPageChangeListener(this);
        tab_layout.setupWithViewPager(info_viewpager);
        //设置Tablayout
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            ((MainActivity)getActivity()).getDrag_layout().setIsDrag(true);
        }else {
            ((MainActivity)getActivity()).getDrag_layout().setIsDrag(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
