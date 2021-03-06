package com.eliot.news.mynews.news_inner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eliot.news.mynews.R;
import com.eliot.news.mynews.activity.DetailActivity;
import com.eliot.news.mynews.adapter.BannerAdapter;
import com.eliot.news.mynews.adapter.HotAdapter;
import com.eliot.news.mynews.bean.Banner;
import com.eliot.news.mynews.bean.Hot;
import com.eliot.news.mynews.bean.HotDetail;
import com.eliot.news.mynews.util.Constant;
import com.eliot.news.mynews.util.HttpRespon;
import com.eliot.news.mynews.util.HttpUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.news_inner
 * @ClassName: HotFragment
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 10:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 10:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HotFragment extends Fragment implements ViewPager.OnPageChangeListener, AbsListView.OnScrollListener
{
    ListView mListView;

    ArrayList<Banner> mBanners;
    ArrayList<HotDetail> mHotDetails;
    ArrayList<View> views;
    ArrayList<ImageView> dot_imgs;
    MyHandler mHandler;
    HotAdapter adapter;
    LayoutInflater inflater;

    boolean isToEnd = false;
    boolean isHttpRequestIng = false;

    private final static int INIT_SUCCESS = 0;

    private final static int UPDATE_SUCCESS = 1;

    ViewPager viewpager;
    BannerAdapter bAdapter;
    TextView bannerTitle;
    LinearLayout dots;

    int startIndex = 0;
    int endIndex = 0;
    int pageSize = 20;
    int count = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        mListView = view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initCollection();
        initView();
        getData(true);
    }

    private void initCollection()
    {
        mBanners = new ArrayList<>();
        mHotDetails = new ArrayList<>();
        views = new ArrayList<>();
        dot_imgs = new ArrayList<>();
    }

    private void initView()
    {
        mHandler = new MyHandler(this);
        inflater = LayoutInflater.from(getActivity());
        View head = inflater.inflate(R.layout.include_banner, null);
        mListView.addHeaderView(head);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("测试16", "捕获ListView点击事件 ");
                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailActivity.class);
                HotDetail detail = adapter.getDateByIndex(position-mListView.getHeaderViewsCount());
                intent.putExtra(DetailActivity.DOCID, detail.getDocid());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
        viewpager = (ViewPager) head.findViewById(R.id.viewpager);
        viewpager.addOnPageChangeListener(this);
        bannerTitle = (TextView) head.findViewById(R.id.title);
        dots = (LinearLayout)head.findViewById(R.id.dots);
    }

    public  void initDate(){
        adapter = new HotAdapter(mHotDetails,getActivity());
        mListView.setAdapter(adapter);
    }

    private void getData(final boolean isInit)
    {
        if(isHttpRequestIng)
            return;
        isHttpRequestIng = true;
        HttpUtil util = HttpUtil.getInstance();
        calIndex();
        String url = Constant.getHotUrl(startIndex,endIndex);
        util.getDate(url, new HttpRespon<Hot>(Hot.class) {
            @Override
            public void onError(String msg) {
                isHttpRequestIng = false;
            }

            @Override
            public void onSuccess(Hot hot) {
                isHttpRequestIng = false;
                //获取列表的数据
                if(null!=hot&&null!=hot.getT1348647909107()){
                    count++;
                    List<HotDetail> details = hot.getT1348647909107();
                    if (isInit)
                    {//取出第0位包含轮播图的数据
                        HotDetail tmp_baner = details.get(0);
                        List<Banner> banners = tmp_baner.getAds();
                        if (banners != null && banners.size() > 0)
                        {
                            mBanners.addAll(banners);
                        }
                        //获取轮播图片成功

                        //删除轮播图片数据
                        details.remove(0);
                        mHotDetails.addAll(details);
                        //列表数据加载完成

                        //获取数据完毕，发送消息更新UI(异步线程无法更改UI)
                        mHandler.sendEmptyMessage(INIT_SUCCESS);
                    }
                    else
                    {
                        Message message = mHandler.obtainMessage(UPDATE_SUCCESS);
                        message.obj = details;
                        mHandler.sendMessage(message);
                    }
                }
            }
        });
    }

    public void calIndex(){
        if(count==0){
            endIndex = startIndex+20;
        }else{
            startIndex = endIndex;
            endIndex = startIndex+20;
        }

    }

    public void update(List<HotDetail> newDate)
    {
        if(null==adapter)
        {
            mHotDetails = new ArrayList<>();
            mHotDetails.addAll(newDate);
            adapter = new HotAdapter(mHotDetails, getActivity());
            mListView.setAdapter(adapter);
        }
        else
            adapter.addData(newDate);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        setImageDot(position);
        setBannerTitle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        if(scrollState == SCROLL_STATE_IDLE&&isToEnd)
            getData(false);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if(view.getLastVisiblePosition()==totalItemCount-1)
            isToEnd = true;
        else
            isToEnd = false;
    }

    static class MyHandler extends Handler
    {
        WeakReference<HotFragment> weak_fragment ;

        public MyHandler(HotFragment fragment) {
            this.weak_fragment = new WeakReference(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HotFragment hot = weak_fragment.get();
            if(hot==null){
                return;
            }
            switch (msg.what) {
                case INIT_SUCCESS:
                    hot.initDate();
                    hot.initBanner();
                    break;
                case UPDATE_SUCCESS:
                    List<HotDetail> date = (List<HotDetail>) msg.obj;
                    hot.update(date);
                    break;

                default:
                    break;
            }
        }
    }

    public void initBanner() {
        if (null != mBanners && mBanners.size() > 0) {
            for (int i = 0; i < mBanners.size(); i++) {
                View view = inflater.inflate(R.layout.item_banner, null);
                views.add(view);
                ImageView dot = new ImageView(getActivity());
                dot.setImageResource(R.drawable.gray_dot);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                p.setMargins(0,0,10,0);
                dots.addView(dot,p);
                dot_imgs.add(dot);
            }
            bAdapter = new BannerAdapter(views,mBanners);
            viewpager.setAdapter(bAdapter);
            int half = Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2)%mBanners.size();
            viewpager.setCurrentItem(half);

            //设置默认显示的数据
            setImageDot(0);
            setBannerTitle(0);
            //bannerTitle.setText(mBanners.get(0).getTitle());


        }
    }

    public void setImageDot(int index){
        int size = dot_imgs.size();
        int realPosition = index%size;
        for(int i = 0;i<size;i++){
            ImageView dot = dot_imgs.get(i);
            if(i== realPosition){
                dot.setImageResource(R.drawable.white_dot);
            }else{
                dot.setImageResource(R.drawable.gray_dot);
            }
        }
    }

    public void setBannerTitle(int index){
        int size = dot_imgs.size();
        int realPosition = index%size;
        //显示默认数据
        bannerTitle.setText(mBanners.get(realPosition).getTitle());
    }
}
