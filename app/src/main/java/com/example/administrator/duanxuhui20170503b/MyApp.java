package com.example.administrator.duanxuhui20170503b;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * data 2017/5/3  18:52.
 * author:段旭晖(Administrator)
 * function:
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Xutils
        x.Ext.setDebug(true);
        x.Ext.init(this);
        //初始化imageloader
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this).diskCacheSize(50*1024*1024).memoryCacheSize(50*1024*1024).build();
        ImageLoader.getInstance().init(configuration);
    }
}
