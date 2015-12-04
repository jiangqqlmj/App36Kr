package com.cniao5.app36kr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cniao5.app36kr.R;

import org.w3c.dom.Text;

/**
 * 当前类注释:第一个Fragment
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.fragment
 * 作者：江清清 on 15/11/16 13:45
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class OneFragment  extends Fragment {
    private View mView;
    public static final String ARG_PAGE = "extra";
    private String extra;
    public static OneFragment newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, extra);
        OneFragment pageFragment = new OneFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extra = getArguments().getString(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.one_frag_layout,container,false);
            TextView tv_title=(TextView)mView.findViewById(R.id.tv_title);
            if(extra!=null){
                tv_title.setText(extra);
            }
        }
        return mView;
    }
}
