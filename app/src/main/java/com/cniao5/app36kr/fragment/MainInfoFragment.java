package com.cniao5.app36kr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.adapter.FixedPagerAdapter;
import com.cniao5.app36kr.application.CNKApplication;
import com.cniao5.app36kr.fragment.base.BaseFragment;
import com.cniao5.app36kr.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class MainInfoFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    private View mView;
    ViewPager info_viewpager;
    //TabPageIndicator id_indicator;
    private List<Fragment> fragments;
    private FixedPagerAdapter mPagerAdater;
    private String[] titles=new String[]{"全部","氪TV","O2O","新硬件","Fun!!","企业服务","Fit&Health","在线教育","互联网金融","大公司","专栏","新产品"};
    /**
     * 当前选择的分类
     */
    private int mCurClassIndex=0;
    /**
     * 选择的分类字体颜色
     */
    private int mColorSelected;
    /**
     * 非选择的分类字体颜色
     */
    private int mColorUnSelected;
    /**
     * 水平滚动的Tab容器
     */
    private HorizontalScrollView mScrollBar;
    /**
     * 分类导航的容器
     */
    private ViewGroup mClassContainer;
    /**
     * 水平滚动X
     */
    private int mScrollX=0;

    private int mScreenWidth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.main_info_fragment_layout,container,false);
            info_viewpager=(ViewPager)mView.findViewById(R.id.info_viewpager);
            //id_indicator=(TabPageIndicator)mView.findViewById(R.id.id_indicator);
            initViews();
            initValidata();
            initDatas();
        }
        return mView;
    }

    /**
     * 初始化布局
     */
    private void initViews(){
        mScrollBar=(HorizontalScrollView)mView.findViewById(R.id.horizontal_info);
        mClassContainer=(ViewGroup)mView.findViewById(R.id.linearlayout_container);
    }

    /**
     * 初始化参数数据
     */
    private void initValidata(){
         DisplayMetrics dm=new DisplayMetrics();
         getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
         mScreenWidth=dm.widthPixels;

         mColorSelected= CNKApplication.getInstance().getResources().getColor(R.color.mask_tags_8);
         mColorUnSelected=CNKApplication.getInstance().getResources().getColor(R.color.color_tab_title);
         addScrollView(titles);
         mScrollBar.post(new Runnable() {
            @Override
            public void run() {
              mScrollBar.scrollTo(mScrollX,0);
            }
        });
    }
    public void initDatas(){
        fragments=new ArrayList<>();
        for(int i=0;i<12;i++){
            fragments.add(new PageFragment());
        }
        mPagerAdater=new FixedPagerAdapter(getChildFragmentManager());
        //mPagerAdater.setTitles(titles);
        mPagerAdater.setFragments(fragments);
        info_viewpager.setAdapter(mPagerAdater);
        //id_indicator.setViewPager(info_viewpager, 0);
        info_viewpager.setOnPageChangeListener(this);
    }

    /**
     * 动态添加顶部Tab滑动的标签
     * @param titles
     */
    private void addScrollView(String[] titles){
        LayoutInflater mLayoutInflater=LayoutInflater.from(CNKApplication.getInstance());
        final int count=titles.length;
        for(int i=0;i<count;i++){
            final String title=titles[i];
            final View view=mLayoutInflater.inflate(R.layout.horizontal_item_layout,null);
            final LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.horizontal_linearlayout_type);
            final ImageView img_type=(ImageView)view.findViewById(R.id.horizontal_img_type);
            final TextView type_name=(TextView)view.findViewById(R.id.horizontal_tv_type);
            type_name.setText(title);
            if(i==mCurClassIndex){
                //已经选中
                type_name.setTextColor(mColorSelected);
                img_type.setImageResource(R.drawable.bottom_line_blue);
            }else {
                //未选中
                type_name.setTextColor(mColorUnSelected);
                img_type.setImageResource(R.drawable.bottom_line_gray);
            }
            final int index=i;
            //点击顶部Tab标签，动态设置下面的ViewPager页面
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //首先设置当前的Item为正常状态
                    View currentItem=mClassContainer.getChildAt(mCurClassIndex);
                    ((TextView)(currentItem.findViewById(R.id.horizontal_tv_type))).setTextColor(mColorUnSelected);
                    ((ImageView)(currentItem.findViewById(R.id.horizontal_img_type))).setImageResource(R.drawable.bottom_line_gray);
                    mCurClassIndex=index;
                    //设置点击状态
                    img_type.setImageResource(R.drawable.bottom_line_blue);
                    type_name.setTextColor(mColorSelected);
                    //跳转到指定的ViewPager
                    info_viewpager.setCurrentItem(mCurClassIndex);
                }
            });
            mClassContainer.addView(view);
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        //id_indicator.setCurrentItem(position);
        Log.d("zttjiangqq", "当前为第" + position + "页...");
        if(position==0){
            ((MainActivity)getActivity()).getDrag_layout().setIsDrag(true);
        }else {
            ((MainActivity)getActivity()).getDrag_layout().setIsDrag(false);
        }

        //首先设置当前的Item为正常状态
        View preView=mClassContainer.getChildAt(mCurClassIndex);
        ((TextView)(preView.findViewById(R.id.horizontal_tv_type))).setTextColor(mColorUnSelected);
        ((ImageView)(preView.findViewById(R.id.horizontal_img_type))).setImageResource(R.drawable.bottom_line_gray);
        mCurClassIndex=position;
        //设置当前为选中状态
        View currentItem=mClassContainer.getChildAt(mCurClassIndex);
        ((ImageView)(currentItem.findViewById(R.id.horizontal_img_type))).setImageResource(R.drawable.bottom_line_blue);
        ((TextView)(currentItem.findViewById(R.id.horizontal_tv_type))).setTextColor(mColorSelected);
        //这边移动的距离 是经过计算粗略得出来的
        mScrollX=currentItem.getLeft()-300;
        Log.d("zttjiangqq", "mScrollX:" + mScrollX);
        mScrollBar.post(new Runnable() {
            @Override
            public void run() {
               mScrollBar.scrollTo(mScrollX,0);
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
