package com.olq.baseframe.base.mvp

interface MvpBack<T>{

    /**
     * 成功的时候回调
     *
     */
    fun onSuccess(t: T)

    /**
     * 失败的时候回调
     */
    fun onError()
}