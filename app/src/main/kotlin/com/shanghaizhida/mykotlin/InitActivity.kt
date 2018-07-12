package com.shanghaizhida.mykotlin

import com.olq.baseframe.base.BaseActivity
import com.olq.baseframe.utils.IntentUtils

abstract class InitActivity :BaseActivity(){

    fun Intent(clazz :Class<*>,isFirsh:Boolean){
        IntentUtils.Intent(this,clazz,null,isFirsh)
    }
    fun Intent(clazz :Class<*>){
        IntentUtils.Intent(this,clazz,null,false)
    }

}