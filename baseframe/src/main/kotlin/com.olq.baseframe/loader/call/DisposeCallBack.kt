package com.olq.baseframe.loader.call

import com.lzy.okgo.model.Response
import com.olq.baseframe.utils.GsonUtils

class DisposeCallBack{

    inline fun <T> onSucceed(clazz: Class<*>, callBack: HttpCallBack<T>, response: Response<String>?){
       if (callBack is GsonCallBack<*>){
           val gsonCallBack =callBack as GsonCallBack<T>
           gsonCallBack.onSucceed(GsonUtils.getBeanFromJson(response?.body().toString(),clazz) as T)
//           (callBack as GsonCallBack<V>).onSucceed(GsonUtils.getBeanFromJson(response.body().toString()),V)
//       }else if (callBack is StringCallBack){
//           (callBack as StringCallBack).onSucceed(response.body().toString())
       }
    }

    fun <T> onError(callBack: HttpCallBack<T>){
        callBack.onError()
    }

}