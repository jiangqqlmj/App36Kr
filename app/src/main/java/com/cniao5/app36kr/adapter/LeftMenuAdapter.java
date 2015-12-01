package com.cniao5.app36kr.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.entity.LeftItemMenu;
import com.cniao5.app36kr.utils.MenuDataUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 当前类注释:左侧功能菜单自定义适配器
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class LeftMenuAdapter extends BaseAdapter {
    private List<LeftItemMenu> leftItemMenus;
    private LayoutInflater mInflater;
    private Context mContext;
    public LeftMenuAdapter(Context pContext){
          this.mContext=pContext;
          this.mInflater=LayoutInflater.from(mContext);
          this.leftItemMenus= MenuDataUtils.getItemMenus();
    }
    @Override
    public int getCount() {
        return leftItemMenus!=null?leftItemMenus.size():0;
    }
    @Override
    public Object getItem(int position) {
        return leftItemMenus.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _Holder=null;
        if(convertView==null){
            _Holder=new Holder();
            convertView=mInflater.inflate(R.layout.item_left_menu_layout,null);
            _Holder.item_left_view_img=(ImageView)convertView.findViewById(R.id.item_left_view_img);
            _Holder.item_left_view_title=(TextView)convertView.findViewById(R.id.item_left_view_title);
            convertView.setTag(_Holder);
        }else {
            _Holder=(Holder)convertView.getTag();
        }
        LeftItemMenu menu=leftItemMenus.get(position);
        _Holder.item_left_view_img.setImageResource(menu.getLeftIcon());
        _Holder.item_left_view_title.setText(menu.getTitle());
        return convertView;
    }

    private static class  Holder{
        ImageView item_left_view_img;
        TextView item_left_view_title;
    }
}
