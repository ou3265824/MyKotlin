package com.olq.baseframe.loader.call

abstract class HttpCallBack<T>:BaseCallBack(){

    abstract fun onSucceed(t:T)

    abstract fun onError()

}