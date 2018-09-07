package com.shanghaizhida.mykotlin.base

import android.os.Bundle
import com.olq.baseframe.base.mvp.BaseModel
import com.olq.baseframe.base.mvp.BasePresenter
import com.olq.baseframe.base.mvp.BaseView
import com.olq.baseframe.base.ui.BaseActivity
import java.lang.reflect.ParameterizedType


abstract class InitActivity<P: BasePresenter<BaseModel, BaseView>,M:BaseModel> : BaseActivity(){

    private var mPresenter: P? = null

    abstract fun onCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter=getT<P>(this,0)
        var mModel=getT<M>(this,1)
        mPresenter!!.attachModelView(mModel,this)
        onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDettach()
    }


    fun <T>  getT( o:Any,  i:Int):T {
//        try {
//
//            return ((Class<T>) (ParameterizedType (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return ((o.javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[i] as Class<T>).newInstance()
    }

}