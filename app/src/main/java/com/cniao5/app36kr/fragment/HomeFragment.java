package com.cniao5.app36kr.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.adapter.quick.BaseAdapterHelper;
import com.cniao5.app36kr.adapter.quick.QuickAdapter;
import com.cniao5.app36kr.biz.CategoryDataManager;
import com.cniao5.app36kr.biz.HeadDataManager;
import com.cniao5.app36kr.biz.HomeNewsDataManager;
import com.cniao5.app36kr.common.Config;
import com.cniao5.app36kr.common.DefineView;
import com.cniao5.app36kr.entity.AdHeadBean;
import com.cniao5.app36kr.entity.CategoriesBean;
import com.cniao5.app36kr.entity.HomeNewsBean;
import com.cniao5.app36kr.fragment.base.BaseFragment;
import com.cniao5.app36kr.widget.AutoGallery;
import com.cniao5.app36kr.widget.FlowIndicator;
import com.cniao5.app36kr.widget.PullToRefreshListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class HomeFragment extends BaseFragment implements DefineView{
    private static OkHttpClient client=null;
    static {
        client=new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(30,TimeUnit.SECONDS);
    }
    private  int[] mask_colors;
    private Resources res;
    private Request request;
    private LayoutInflater mInflater;
    private View mView;
    public static final String ARG_PAGE = "extra";
    private CategoriesBean extraBean;
    private PullToRefreshListView lv_pulltorefresh;
    private View headView;
    private AutoGallery headline_image_gallery;
    private FlowIndicator galleryFlowIndicator;
    private List<AdHeadBean> adHeadBeans;  //顶部广告轮播数据
    private List<HomeNewsBean> homeNewsBeans;   //新闻列表数据
    private int circleSelectedPosition = 0; // 默认指示器的圆圈的位置为第一项
    private int gallerySelectedPositon = 0; // 默认gallery的图片为第一张
    private QuickAdapter<HomeNewsBean> mAdapter;
    private ImageLoader mImageLoder;
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
            mView=inflater.inflate(R.layout.home_frag_layout, container, false);
            mInflater=LayoutInflater.from(getActivity());
            headView=mInflater.inflate(R.layout.gallery_indicator_layout, null);
            initView();
            initValidata();
            initListener();

        }
        return mView;
    }

    @Override
    public void initView() {
        lv_pulltorefresh=(PullToRefreshListView)mView.findViewById(R.id.lv_pulltorefresh);
        lv_pulltorefresh.addHeaderView(headView);
        headline_image_gallery=(AutoGallery)headView.findViewById(R.id.headline_image_gallery);
        galleryFlowIndicator=(FlowIndicator)headView.findViewById(R.id.headline_circle_indicator);

    }
    @Override
    public void initValidata() {
        mImageLoder=ImageLoader.getInstance();
        mask_colors=new int[]{R.color.mask_tags_1,R.color.mask_tags_2
                ,R.color.mask_tags_3,R.color.mask_tags_4,R.color.mask_tags_5,R.color.mask_tags_6
                ,R.color.mask_tags_7,R.color.mask_tags_8,R.color.mask_tags_9,R.color.mask_tags_10
                ,R.color.mask_tags_11,R.color.mask_tags_12};
        res=getActivity().getResources();
        //进行获取头部广告数据和底部列表数据
        request=new Request.Builder().url(extraBean.getHref()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                 Document document=Jsoup.parse(response.body().byteStream(), "UTF-8", Config.CRAWLER_URL);
                 adHeadBeans=new HeadDataManager().getHeadBeans(document);
                 homeNewsBeans=new HomeNewsDataManager().getHomeNewsBeans(document);
                 if(adHeadBeans!=null&&homeNewsBeans!=null){
                  getActivity().runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         bindData();
                         Log.d("zttjiangqq",homeNewsBeans.toString());
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
        int topSize = adHeadBeans.size();
        //设置指示器圆点的数量
        galleryFlowIndicator.setCount(topSize);
        //设置当前的位置
        galleryFlowIndicator.setSeletion(circleSelectedPosition);
        //设置画廊 图片的数量
        headline_image_gallery.setLength(topSize);
        headline_image_gallery.setAdapter(new GalleryIndicatorAdapter());

        gallerySelectedPositon = topSize * 20 + circleSelectedPosition;
        //设置画廊当前所指的位置 索引
        headline_image_gallery.setSelection(gallerySelectedPositon);
        headline_image_gallery.start();

        //gallery滚动选择监听
        headline_image_gallery
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        gallerySelectedPositon = position;
                        circleSelectedPosition = position
                                % headline_image_gallery.getLength();
                        galleryFlowIndicator
                                .setSeletion(circleSelectedPosition);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        //gallery点击选中事件
        headline_image_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index=position
                        % headline_image_gallery.getLength()+1;
            }
        });

        if(mAdapter==null) {
            mAdapter = new QuickAdapter<HomeNewsBean>(getActivity(), R.layout.item_home_news_layout,homeNewsBeans) {
                @Override
                protected void convert(BaseAdapterHelper helper, HomeNewsBean item) {
                    helper.setText(R.id.item_tv_author_name, item.getAuthorBean().getName())
                            .setText(R.id.item_tv_author_time, item.getDatetext())
                            .setText(R.id.item_tv_title, item.getTitle());
                    //分类标签视图
                    String mask=item.getMask();
                    if(mask.equals("全部")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[0]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[0]));
                        helper.setText(R.id.item_tv_author_mask,mask);
                    }else if(mask.equals("氪TV")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[1]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[1]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("O2O")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[2]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[2]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("新硬件")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[3]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow,res.getColor(mask_colors[3]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("Fun!!")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[4]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[4]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("企业服务")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[5]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow,res.getColor(mask_colors[5]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("Fit&Health")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[6]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[6]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("在线教育")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[7]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[7]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("互联网金融")){
                        helper.setTextColor(R.id.item_tv_author_mask, res.getColor(mask_colors[8]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[8]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("大公司")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[9]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[9]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("专栏")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[10]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[10]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }else if(mask.equals("新产品")){
                        helper.setTextColor(R.id.item_tv_author_mask,res.getColor(mask_colors[11]));
                        helper.setBackgroundColor(R.id.item_tv_author_arrow, res.getColor(mask_colors[11]));
                        helper.setText(R.id.item_tv_author_mask, mask);
                    }
                    mImageLoder.displayImage(item.getAuthorBean().getAvatar(), (ImageView) helper.getView(R.id.item_img_author_head));
                    mImageLoder.displayImage(item.getImgurl(),(ImageView)helper.getView(R.id.item_img_logo));
                }
            };
            lv_pulltorefresh.setAdapter(mAdapter);
        }
    }
    class GalleryIndicatorAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return adHeadBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Hondler _Hondler=null;
            if(convertView==null){
                _Hondler=new Hondler();
                convertView=mInflater.inflate(R.layout.headline_gallery_item,null);
                _Hondler.headline_gallery_imageview=(ImageView)convertView.findViewById(R.id.headline_gallery_imageview);
                convertView.setTag(_Hondler);
            }else
            {
                _Hondler=(Hondler)convertView.getTag();
            }
            int mPosition = position % adHeadBeans.size();
            mImageLoder.displayImage(adHeadBeans.get(mPosition).getImgurl(), _Hondler.headline_gallery_imageview);
            return convertView;
        }
    }

    static class Hondler{
        ImageView headline_gallery_imageview;
    }
}
