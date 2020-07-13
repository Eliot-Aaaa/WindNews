package com.eliot.news.mynews.news_inner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eliot.news.mynews.R;
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
public class HotFragment extends Fragment
{
    ListView mListView;

    ArrayList<Banner> mBanners;
    ArrayList<HotDetail> mHotDetails;
    MyHandler mHandler;
    HotAdapter adapter;
    private final static int INIT_SUCCESS = 0;

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
        mBanners = new ArrayList<>();
        mHotDetails = new ArrayList<>();
        mHandler = new MyHandler(this);
        HttpUtil util = HttpUtil.getInstance();
        util.getDate(Constant.HOT_URL, new HttpRespon<Hot>(Hot.class) {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onSuccess(Hot hot) {

                //获取列表的数据
                if(null!=hot&&null!=hot.getT1348647909107()){
                    List<HotDetail> details = hot.getT1348647909107();
                    //取出第0位包含轮播图的数据
                    HotDetail tmp_baner = details.get(1);
                    List<Banner> banners = tmp_baner.getAds();
                    if (banners != null && banners.size() > 0)
                        mBanners.addAll(banners);
                    //获取轮播图片成功

                    //删除轮播图片数据
                    details.remove(1);
                    mHotDetails.addAll(details);
                    //列表数据加载完成

                    //获取数据完毕，发送消息更新UI(异步线程无法更改UI)
                    mHandler.sendEmptyMessage(INIT_SUCCESS);
                }
            }
        });
    }

    public  void initDate(){
        adapter = new HotAdapter(mHotDetails,getActivity());
        mListView.setAdapter(adapter);
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
                    break;

                default:
                    break;
            }
        }
    }
}
