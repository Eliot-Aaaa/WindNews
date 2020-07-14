package com.eliot.news.mynews.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.eliot.news.mynews.R;
import com.eliot.news.mynews.bean.Banner;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.adapter
 * @ClassName: BannerAdapter
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 17:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 17:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BannerAdapter extends PagerAdapter
{
    ArrayList<View> view;
    ArrayList<Banner> banners;
    DisplayImageOptions mOptions;
    int size;

    public BannerAdapter(ArrayList<View> view,ArrayList<Banner> banners)
    {
        this.view = view;
        this.banners = banners;
        size = view.size();

        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        int realPosition = position%size;
        View tmp = view.get(realPosition);
        ImageView image = (ImageView) tmp.findViewById(R.id.img);
        Banner banner =  banners.get(realPosition);
        ImageLoader.getInstance().displayImage(banner.getImgsrc(),image,mOptions);
        container.addView(tmp);
        return tmp;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View)object);
    }
}
