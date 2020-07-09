package com.eliot.news.mynews.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.util
 * @ClassName: ImageUtil
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 10:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 10:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImageUtil {
    public static boolean checkImageIsDownload(String imageName)
    {
        File image = getFileByName(imageName);
        if (image.exists())
        {
            Bitmap bitmap = getImageBitmapByFile(image);
            if (bitmap != null)
                return true;
        }
        return false;
    }

    public static File getFileByName(String imageName)
    {
        File SD = Environment.getExternalStorageDirectory();
        File cacheFile = new File(SD, Constant.CACHE);
        File image = new File(cacheFile, imageName + ".jpg");
        return image;
    }

    public static Bitmap getImageBitmapByFile(File file)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }
}
