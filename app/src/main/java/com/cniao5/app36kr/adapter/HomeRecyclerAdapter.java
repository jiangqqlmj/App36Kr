package com.cniao5.app36kr.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.entity.HomeNewsBean;
import com.cniao5.app36kr.widget.RoundAngleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 0;     //普通Item View
    private static final int TYPE_TV = 1;       //TV列表
    private static final int TYPE_FOOTER = 2;   //顶部FootView

    //界面类型  TV页面或者普通列表  0表示TV ,1表示列表
    private int type=0;

    private Context mContext;
    private LayoutInflater mInflater;
    //新闻列表信息
    private List<HomeNewsBean> homeNewsBeans;
    private ImageLoader mImageLoder;
    private int[] mask_colors;
    private String[] masks;
    private Resources res;

    public void setHomeNewsBeans(List<HomeNewsBean> homeNewsBeans) {
        this.homeNewsBeans = homeNewsBeans;
    }

    public HomeRecyclerAdapter(Context pContext,int type){
        this.mContext=pContext;
        this.mImageLoder=ImageLoader.getInstance();
        this.type=type;
        this.mInflater=LayoutInflater.from(this.mContext);
        this.res=mContext.getResources();
        mask_colors=new int[]{R.color.mask_tags_1,R.color.mask_tags_2
                ,R.color.mask_tags_3,R.color.mask_tags_4,R.color.mask_tags_5,R.color.mask_tags_6
                ,R.color.mask_tags_7,R.color.mask_tags_8,R.color.mask_tags_9,R.color.mask_tags_10
                ,R.color.mask_tags_11,R.color.mask_tags_12};
        masks=new String[]{"全部","氪TV","O2O","新硬件","Fun!!","企业服务","Fit&Health","在线教育","互联网金融","大公司","专栏"};
    }

    @Override
    public int getItemCount() {
        return homeNewsBeans!=null?homeNewsBeans.size()+1:0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断TV还是普通列表，来创建返回不同的View
        if(type==0){
            if(viewType==TYPE_TV){
                View view=mInflater.inflate(R.layout.item_tv_news_layout,parent,false);
                return new TvItemViewHolder(view);
            }else if(viewType==TYPE_FOOTER){
                View foot_view=mInflater.inflate(R.layout.recycler_load_more_layout,parent,false);
                return new FootViewHolder(foot_view);
            }
        }else {
            if(viewType==TYPE_ITEM){
                View view=mInflater.inflate(R.layout.item_home_news_layout,parent,false);
                return new ItemViewHolder(view);
            }else if(viewType==TYPE_FOOTER){
                View foot_view=mInflater.inflate(R.layout.recycler_load_more_layout,parent,false);
                return new FootViewHolder(foot_view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
           if(holder instanceof  ItemViewHolder){
               HomeNewsBean bean=homeNewsBeans.get(position);
               mImageLoder.displayImage(bean.getAuthorBean().getAvatar(),((ItemViewHolder) holder).item_img_author_head);
               ((ItemViewHolder) holder).item_tv_author_name.setText(bean.getAuthorBean().getName());
               ((ItemViewHolder) holder).item_tv_author_time.setText(bean.getDatetext());
               ((ItemViewHolder) holder).item_tv_title.setText(bean.getTitle());
               mImageLoder.displayImage(bean.getImgurl(),((ItemViewHolder) holder).item_img_logo);
               //分类标签视图
               String mask=bean.getMask();
               ((ItemViewHolder) holder).item_tv_author_mask.setText(mask);
               for(int i=0;i<mask_colors.length;i++){
                   if(mask.equals(masks[i])){
                       setMaskColors_ItemView((ItemViewHolder)holder,i);
                       break;
                   }
               }
           }else if(holder instanceof TvItemViewHolder){
               //TV页面数据显示
               HomeNewsBean bean=homeNewsBeans.get(position);
               mImageLoder.displayImage(bean.getImgurl(),((TvItemViewHolder) holder).item_tv_img);
               ((TvItemViewHolder) holder).item_tv_title.setText(bean.getTitle());
               //分类标签视图
               ((TvItemViewHolder) holder).item_tv_mask.setText(bean.getMask());
           }else if(holder instanceof FootViewHolder){
           }
    }

    /**
     * 根据类型以及position进行返回布局类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
         if(type==0){
             if (position + 1 == getItemCount()) {
                     return TYPE_FOOTER;
                 } else {
                     return TYPE_TV;
             }
         }else{
             if (position + 1 == getItemCount()) {
                 return TYPE_FOOTER;
             } else {
                 return TYPE_ITEM;
             }
         }
    }

    /**
     * 普通Item viewholer
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
         RoundAngleImageView item_img_author_head;
         TextView item_tv_author_name;
         TextView item_tv_author_time;
         LinearLayout item_tv_author_arrow;
         TextView item_tv_author_mask;
         TextView item_tv_title;
         ImageView item_img_logo;
        public ItemViewHolder(View view) {
            super(view);
            item_img_author_head=(RoundAngleImageView)view.findViewById(R.id.item_img_author_head);
            item_tv_author_name=(TextView)view.findViewById(R.id.item_tv_author_name);
            item_tv_author_time=(TextView)view.findViewById(R.id.item_tv_author_time);
            item_tv_author_arrow=(LinearLayout)view.findViewById(R.id.item_tv_author_arrow);
            item_tv_author_mask=(TextView)view.findViewById(R.id.item_tv_author_mask);
            item_tv_title=(TextView)view.findViewById(R.id.item_tv_title);
            item_img_logo=(ImageView)view.findViewById(R.id.item_img_logo);
        }
    }

    /**
     * 视频列表 Item viewholder
     */
    public static class TvItemViewHolder extends  RecyclerView.ViewHolder{
        ImageView item_tv_img;
        TextView item_tv_title;
        TextView item_tv_mask;
        public TvItemViewHolder(View view) {
            super(view);
            item_tv_img=(ImageView)view.findViewById(R.id.item_tv_img);
            item_tv_title=(TextView)view.findViewById(R.id.item_tv_title);
            item_tv_mask=(TextView)view.findViewById(R.id.item_tv_mask);
        }
    }

    /**
     * 列表底部FootView viewHolder
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 进行设置相关颜色
     * @param holder
     * @param position
     */
    private void setMaskColors_ItemView(ItemViewHolder holder,int position){
        holder.item_tv_author_mask.setTextColor(res.getColor(mask_colors[position]));
        holder.item_tv_author_arrow.setBackgroundColor(res.getColor(mask_colors[position]));
    }
}