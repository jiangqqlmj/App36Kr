package com.cniao5.app36kr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.cniao5.app36kr.entity.CategoriesBean;

import java.util.List;

/**
 * 当前类注释:Fragment自定义适配器
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.adapter.base
 * 作者：江清清 on 15/11/16 13:57
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class FixedPagerAdapter extends FragmentStatePagerAdapter {
    private List<CategoriesBean> categoriesBeans;

    public void setCategoriesBeans(List<CategoriesBean> categoriesBeans) {
        this.categoriesBeans = categoriesBeans;
    }
    private List<Fragment> fragments;
    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
           fragment=(Fragment)super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return categoriesBeans.get(position%categoriesBeans.size()).getTitle();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
