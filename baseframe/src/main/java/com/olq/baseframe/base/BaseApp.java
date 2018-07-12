package com.olq.baseframe.base;

import android.app.Application;
import android.content.Context;

import com.olq.baseframe.loader.OkDownloadLoader;
import com.olq.baseframe.loader.OkgoLoader;

/**
 * Created by Administrator on 2017/6/21.
 */

public class BaseApp extends Application {

   private static Context context;

    public static Context getInstance() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        OkgoLoader.getInstance().init(this);
        OkDownloadLoader.getInstance().init(this);
    }


}

