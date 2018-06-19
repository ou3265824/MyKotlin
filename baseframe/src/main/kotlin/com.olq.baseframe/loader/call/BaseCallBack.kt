package com.olq.baseframe.loader.call

abstract class BaseCallBack<T>{

    abstract fun onSucceed(t:T)

    abstract fun onError()

}