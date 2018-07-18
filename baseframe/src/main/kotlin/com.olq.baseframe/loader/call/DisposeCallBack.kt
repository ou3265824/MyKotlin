package com.olq.baseframe.loader.call

class DisposeCallBack{

     fun <T> onSucceed(callBack: HttpCallBack<T>, body: String){
       if (callBack is GsonCallBack<*>){
           val gsonCallBack =callBack as GsonCallBack<T>
           gsonCallBack.onResponse(body)
       }else if (callBack is StringCallBack){
           (callBack as StringCallBack).onResponse(body)
       }
    }

    fun <T> onError(callBack: HttpCallBack<T>, body: String){
        if (callBack is GsonCallBack<*>){
            val gsonCallBack =callBack as GsonCallBack<T>
            gsonCallBack.onError(body)
        }else if (callBack is StringCallBack){
            (callBack as StringCallBack).onError(body)
        }
    }

}