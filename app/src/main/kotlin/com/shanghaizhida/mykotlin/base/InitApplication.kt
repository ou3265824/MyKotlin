package com.shanghaizhida.mykotlin.base

import com.alibaba.android.arouter.launcher.ARouter
import com.olq.baseframe.base.BaseApplication

class InitApplication : BaseApplication() {

    override fun onInit() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        ARouter.openDebug()
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

}