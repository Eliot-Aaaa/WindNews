package com.eliot.news.mynews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eliot.news.mynews.R;
import com.eliot.news.mynews.bean.HotDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.adapter
 * @ClassName: HotAdapter
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 11:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 11:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HotAdapter extends BaseAdapter
{
    ArrayList<HotDetail> mHotDetails;
    LayoutInflater mInflater;
    DisplayImageOptions mOptions;

    public HotAdapter(ArrayList<HotDetail> hotDetails, Context context)
    {
        mHotDetails = hotDetails;
        mInflater = LayoutInflater.from(context);
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
    }

    @Override
    public int getCount()
    {
        return mHotDetails.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mHotDetails.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        HotDetail detail = mHotDetails.get(position);
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_hot, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.img);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.source = (TextView) convertView.findViewById(R.id.source);
            holder.reply_count = (TextView) convertView.findViewById(R.id.reply_count);
            holder.special = (TextView) convertView.findViewById(R.id.special);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        initViews(holder, detail);
        return convertView;
    }

    public void initViews(ViewHolder hoder, HotDetail detail)
    {
        hoder.title.setText(detail.getTitle());
        hoder.source.setText(detail.getSource());
        hoder.reply_count.setText(detail.getReplyCount() + "跟帖");
        ImageLoader.getInstance().displayImage(detail.getImgsrc(), hoder.icon, mOptions, new ImageLoadingListener(){
            @Override
            public void onLoadingStarted(String s, View view)
            {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason)
            {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap)
            {

            }

            @Override
            public void onLoadingCancelled(String s, View view)
            {

            }
        });
    }

    public void addData(List<HotDetail> add)
    {
        if (mHotDetails == null)
        {
            mHotDetails = new ArrayList<>();
        }
        mHotDetails.addAll(add);
        notifyDataSetChanged();
    }

    public HotDetail getDateByIndex(int index)
    {
        HotDetail detail = mHotDetails.get(index);
        return  detail;
    }

    class ViewHolder {
        ImageView icon;
        TextView title;
        TextView source;
        TextView reply_count;
        TextView special;
    }
}
