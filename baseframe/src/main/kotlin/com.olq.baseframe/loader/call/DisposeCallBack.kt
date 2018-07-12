package com.olq.baseframe.loader.call

import com.lzy.okgo.model.Response
import com.olq.baseframe.utils.GsonUtils

class DisposeCallBack{

     fun <T> onSucceed(clazz: Class<*>?, callBack: HttpCallBack<T>, response: Response<String>?){
       if (callBack is GsonCallBack<*>){
           val gsonCallBack =callBack as GsonCallBack<T>
           gsonCallBack.onSucceed(GsonUtils.fromJson(response?.body().toString(),clazz) as T)
       }else if (callBack is StringCallBack){
           (callBack as StringCallBack).onSucceed(response?.body().toString())
       }
    }

    fun <T> onError(callBack: HttpCallBack<T>){
        callBack.onError()
    }

}