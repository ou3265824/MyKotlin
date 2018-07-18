package com.olq.baseframe.loader.call

abstract class HttpCallBack<T>:BaseCallBack(){

    abstract fun onResponse(json:T)

    abstract fun onError(error:String)

}