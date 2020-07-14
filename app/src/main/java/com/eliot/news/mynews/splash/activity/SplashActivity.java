package com.eliot.news.mynews.splash.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eliot.news.mynews.MainActivity;
import com.eliot.news.mynews.R;
import com.eliot.news.mynews.service.DownloadImageService;
import com.eliot.news.mynews.splash.OnTimeClickListener;
import com.eliot.news.mynews.splash.TimeView;
import com.eliot.news.mynews.splash.bean.Action;
import com.eliot.news.mynews.splash.bean.Ads;
import com.eliot.news.mynews.splash.bean.AdsDetail;
import com.eliot.news.mynews.util.Constant;
import com.eliot.news.mynews.util.HttpRespon;
import com.eliot.news.mynews.util.HttpUtil;
import com.eliot.news.mynews.util.ImageUtil;
import com.eliot.news.mynews.util.JsonUtil;
import com.eliot.news.mynews.util.Md5Helper;
import com.eliot.news.mynews.util.SharePrenceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.splash
 * @ClassName: SplashActivity
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 9:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 9:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SplashActivity extends Activity {
    ImageView ads_img;

    TimeView time;

    static final String JSON_CACHE = "ads_Json";
    static final String JSON_CACHE_TIME_OUT = "ads_Json_time_out";
    static final String JSON_CACHE_LAST_SUCCESS = "ads_Json_last";
    static final String LAST_IMAGE_INDEX = "img_index";

    int length = 2*1000;
    int space = 250;
    int now = 0;
    int total;

    MyHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        ads_img = findViewById(R.id.ads);
        mHandler = new MyHandler(this);
        time = (TimeView)findViewById(R.id.time);
        time.setListener(new OnTimeClickListener() {
            @Override
            public void onClickTime(View view) {
                mHandler.removeCallbacks(reshRing);
                gotoMain();
            }
        });

        total = length/space;
        mHandler.post(reshRing);

        getAds();
        showImage();
    }

    Runnable NoPhotoGotoMain = new Runnable() {
        @Override
        public void run() {
            gotoMain();
        }
    };

    Runnable reshRing = new Runnable()
    {
        @Override
        public void run() {
            Message message = mHandler.obtainMessage(0);
            message.arg1 = now;
            mHandler.sendMessage(message);
            mHandler.postDelayed(this, space);
            now++;
        }
    };

    @Override
    public void onBackPressed() {
        mHandler.removeCallbacks(NoPhotoGotoMain);
        super.onBackPressed();
    }

    public void showImage()
    {
        String cache = SharePrenceUtil.getString(this, JSON_CACHE);
        if (!TextUtils.isEmpty(cache))
        {
            int index = SharePrenceUtil.getInt(this, LAST_IMAGE_INDEX);
            Ads ads = JsonUtil.parseJson(cache, Ads.class);
            int size = ads.getAds().size();
            if (ads == null)
                return;
            List<AdsDetail> adsDetail = ads.getAds();
            if (adsDetail != null && adsDetail.size()>0)
            {
                final AdsDetail detail = adsDetail.get(index%size);
                List<String> urls = detail.getRes_url();
                if (urls!=null && !TextUtils.isEmpty(urls.get(0)))
                {
                    String url = urls.get(0);
                    String image_name = Md5Helper.toMD5(url);
                    File image = ImageUtil.getFileByName(image_name);
                    if (image.exists())
                    {
                        Bitmap bitmap = ImageUtil.getImageBitmapByFile(image);
                        ads_img.setImageBitmap(bitmap);
                        index++;
                        SharePrenceUtil.saveInt(this, LAST_IMAGE_INDEX, index);
                        ads_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Action action = detail.getAction_params();
                                if (action != null || TextUtils.isEmpty(action.getLink_url()))
                                {
                                    Intent intent = new Intent();
                                    intent.setClass(SplashActivity.this, WebViewActivity.class);
                                    intent.putExtra(WebViewActivity.ACTION_NAME, action);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
            }
        }else
            mHandler.postDelayed(NoPhotoGotoMain, 3000);
    }

    public void getAds()
    {
        String cache = SharePrenceUtil.getString(this, JSON_CACHE);
        if(TextUtils.isEmpty(cache))
            httpRequest();
        else
        {
            int time_out = SharePrenceUtil.getInt(this, JSON_CACHE_TIME_OUT);
            long now = System.currentTimeMillis();
            long last = SharePrenceUtil.getLong(this, JSON_CACHE_LAST_SUCCESS);
            if ((now-last)>time_out*60*10)
                httpRequest();
        }
    }

    public void httpRequest()
    {
        HttpUtil util = HttpUtil.getInstance();
        util.getDate(Constant.SPLASH_URL, new HttpRespon<String>(String.class){
            @Override
            public void onError(String msg)
            {
                Log.i("测试8","error msg" + msg);
            }

            @Override
            public void onSuccess(String s)
            {
                Log.i("测试8","onSuccess" + s.toString());
                Ads ads = JsonUtil.parseJson(s, Ads.class);
                if (ads != null){
                    //请求成功
                    Log.i("测试1:", "广告数据请求成功");
                    //Http成功后，缓存json
                    SharePrenceUtil.saveString(SplashActivity.this, JSON_CACHE, s);
                    ////Http成功后，缓存超时时间
                    SharePrenceUtil.saveInt(SplashActivity.this, JSON_CACHE_TIME_OUT, ads.getNext_req());
                    //Http成功后，缓存上次请求成功的时间
                    SharePrenceUtil.saveLong(SplashActivity.this, JSON_CACHE_LAST_SUCCESS, System.currentTimeMillis());

                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, DownloadImageService.class);
                    intent.putExtra(DownloadImageService.ADS_DATA, ads);
                    startService(intent);
                }
            }
        });
    }

    public void gotoMain()
    {
        Intent intent = new Intent();
        intent.setClass(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    static class MyHandler extends Handler{
        WeakReference<SplashActivity> activity;
        public MyHandler(SplashActivity act)
        {
            this.activity = new WeakReference<SplashActivity>(act);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            SplashActivity act = activity.get();
            if (act == null)
                return;
            switch (msg.what)
            {
                case 0:
                    int now = msg.arg1;
                    if (now <= act.total)
                        act.time.setProgress(act.total, now);
                    else {
                        this.removeCallbacks(act.reshRing);
                        act.gotoMain();
                    }
                    break;
            }
        }
    }
}
