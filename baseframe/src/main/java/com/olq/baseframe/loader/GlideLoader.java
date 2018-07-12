package com.olq.baseframe.loader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shanghaizhida.octopusbase.base.BaseApp;

import java.io.File;

/**
 * Created by Administrator on 2017/1/23.
 */

public class GlideLoader {

    public static GlideLoader mGlideLoader;

    private GlideLoader() {
    }

    public static GlideLoader getInstance(){
        if (mGlideLoader ==null){
            synchronized (GlideLoader.class){
                if (mGlideLoader==null){
                    mGlideLoader=new GlideLoader();
                }
            }
        }
        return mGlideLoader;
    }

    public void init(String url, ImageView imageView){
        Glide.with(BaseApp.getInstance()).load(url).into(imageView);
    }
    public void init(File file, ImageView imageView){
        Glide.with(BaseApp.getInstance()).load(file).into(imageView);
    }
    public void init(int integer, ImageView imageView){
        Glide.with(BaseApp.getInstance()).load(integer).into(imageView);
    }
    public void init(Uri uri, ImageView imageView){
        Glide.with(BaseApp.getInstance()).load(uri).into(imageView);
    }

    /**
     * 不缓存图片
     * @param context
     * @param url
     * @param imageView
     */
    public void initNotCache(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imageView);
    }


}
