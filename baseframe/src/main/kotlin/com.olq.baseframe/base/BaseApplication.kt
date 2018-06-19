package com.olq.baseframe.base

import android.app.Application
import com.olq.baseframe.CrashHandler
import com.olq.baseframe.loader.OkgoLoader
import com.tencent.bugly.Bugly

class BaseApplication : Application() {



    override fun onCreate() {
        super.onCreate()
        OkgoLoader.init(this)
        CrashHandler();
        Bugly.init(getApplicationContext(), "e423995e12", false);
    }
}