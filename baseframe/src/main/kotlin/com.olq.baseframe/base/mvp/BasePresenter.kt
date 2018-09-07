package com.olq.baseframe.base.mvp

import java.lang.ref.WeakReference

abstract class BasePresenter<M : BaseModel,V : BaseView>{

     var mModel: M? =null

     var mView:V?=null

     var mViewRef: WeakReference<V>?=null


    public fun attachModelView( pModel:M,  pView:V) {

        mViewRef = WeakReference<V>(pView)

        mModel = pModel
    }


     fun  getView() : V? {
        if (isAttach()) {
            return mViewRef?.get()
        } else {
            return null
        }
    }

     fun isAttach():Boolean {
        return null != mViewRef && null != mViewRef!!.get()
    }


    public fun onDettach() {
        if (null != mViewRef) {
            mViewRef!!.clear()
            mViewRef = null
        }
    }

}