package com.shanghaizhida.mykotlin.base

import com.alibaba.android.arouter.launcher.ARouter
import com.olq.baseframe.base.BaseApplication


class InitApplication : BaseApplication() {

    override fun onInit() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        ARouter.openDebug()
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
//        LoadSir.beginBuilder()
//                .addCallback(ErrorCallback())//添加各种状态页
//                .addCallback(EmptyCallback())
//                .addCallback(LoadingCallback())
//                .addCallback(TimeoutCallback())
//                .addCallback(CustomCallback())
//                .setDefaultCallback(LoadingCallback::class.java!!)//设置默认状态页
//                .commit()


    }

}