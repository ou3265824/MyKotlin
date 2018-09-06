package com.shanghaizhida.mykotlin.base

import android.os.Bundle
import com.olq.baseframe.base.BaseActivity


abstract class InitActivity :BaseActivity(){

//    fun Intent(clazz :Class<*>,isFirsh:Boolean){
//        IntentUtils.Intent(this,clazz,null,isFirsh)
//    }
//    fun Intent(clazz :Class<*>){
//        IntentUtils.Intent(this,clazz,null,false)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val loadService = LoadSir.getDefault().register(this, object : Callback.OnReloadListener {
//            override fun onReload(v: View?) {
//                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                // 重新加载逻辑
//                ToastUtils.show("重新加载逻辑")
//            }
//        })

    }

}