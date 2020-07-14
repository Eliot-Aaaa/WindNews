package com.eliot.news.mynews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.eliot.news.mynews.R;
import com.eliot.news.mynews.adapter.DetailImageAdapter;
import com.eliot.news.mynews.bean.DetailWebImage;

import java.util.ArrayList;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.activity
 * @ClassName: DetailImageActivity
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/14 17:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 17:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DetailImageActivity extends Activity
{
    ViewPager viewpager;
    ArrayList<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);
        views = new ArrayList<>();

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<DetailWebImage> images = (ArrayList<DetailWebImage>) getIntent().getSerializableExtra("image");
        if(null!=images){
            for(DetailWebImage tmp:images){
                View view =  View.inflate(this,R.layout.item_detail_img,null);
                views.add(view);
            }
        }

        DetailImageAdapter adapter = new DetailImageAdapter(images,this,views);
        viewpager.setAdapter(adapter);

    }
}
