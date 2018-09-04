package com.shanghaizhida.mykotlin.config

import com.alibaba.android.arouter.launcher.ARouter
import com.olq.baseframe.base.BaseActivity

abstract class  RouterConfig{

    companion object {
        //首页类
        const val  HOME="/home/"
        //启动页
        const val  LAUNCH=HOME+"launch"
        //首页
        const val  MAIN=HOME+"main"


        fun  getRouter(url: String){
            getRouter(url,true)
        }

        fun  getRouter(url: String,isFinish:Boolean){
            ARouter.getInstance().build(url).navigation()
            if (isFinish){
                val activity= BaseActivity.mContext as BaseActivity
                activity.finish()
            }
        }
    }





}