package com.eliot.news.mynews.service;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.service
 * @ClassName: NetEaseApplication
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 11:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 11:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NetEaseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        File sd = Environment.getExternalStorageDirectory();
        File image_loader_cache= new File(sd,"xmg4");
        if(!image_loader_cache.exists())
            image_loader_cache.mkdirs();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .discCache(new UnlimitedDiskCache(image_loader_cache))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(config);
    }
}
