package com.eliot.news.mynews.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.eliot.news.mynews.splash.bean.Ads;
import com.eliot.news.mynews.splash.bean.AdsDetail;
import com.eliot.news.mynews.util.Constant;
import com.eliot.news.mynews.util.ImageUtil;
import com.eliot.news.mynews.util.Md5Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.service
 * @ClassName: DownloadImageService
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 9:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 9:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DownloadImageService extends IntentService {
    public static final String ADS_DATA = "ads";

    public DownloadImageService()
    {
        super("DownloadImageService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Ads ads = (Ads) intent.getSerializableExtra(ADS_DATA);
        List<AdsDetail> list = ads.getAds();
        for (int i = 0; i < list.size(); i++)
        {
            AdsDetail detail = list.get(i);
            List<String> imgs = detail.getRes_url();
            if (imgs != null)
            {
                String img_url = imgs.get(0);
                if (!TextUtils.isEmpty(img_url))
                {
                    String catch_name = Md5Helper.toMD5(img_url);
                    if (!ImageUtil.checkImageIsDownload(catch_name))
                        downloadImage(img_url, catch_name);
                }
            }
        }
    }

    public void downloadImage(String url, String MD5_name)
    {
        try {
            URL uri = new URL(url);
            URLConnection urlConnection = uri.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
            saveToSD(bitmap, MD5_name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToSD(Bitmap bitmap, String MD5_name)
    {
        if(bitmap == null)
            return;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            File SD = Environment.getExternalStorageDirectory();
            File cacheFile = new File(SD, Constant.CACHE);
            if (!cacheFile.exists())
                cacheFile.mkdirs();
            File image = new File(cacheFile, MD5_name + ".jpg");
            if (image.exists())
                return;
            FileOutputStream image_out = null;
            try {
                image_out = new FileOutputStream(image);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, image_out);
                image_out.flush();
                image_out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            this.getCacheDir();
    }
}
