package com.olq.baseframe.base.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.olq.baseframe.base.ActivityManager
import com.olq.baseframe.base.mvp.BaseView
import com.olq.baseframe.event.BaseEvent
import com.olq.baseframe.event.EventManage
import com.olq.baseframe.widget.FlexibleLayout
import com.zhy.changeskin.SkinManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity(), BaseView {

    companion object {
        lateinit var mContext:Context
    }


    private var mFlexibleLayout: FlexibleLayout? = null
    abstract fun getLayoutId(): Int
    abstract fun onLoadData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView())
        ActivityManager.pushActivity(this)
        SkinManager.getInstance().register(this)
        if (isEventBus()){
            register()
        }
        mContext =this

    }





    fun getView(): View {
        mFlexibleLayout = object : FlexibleLayout(this) {

          override fun loadData() {
              //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
              onLoadData()
          }

          override fun initNormalView(): ViewGroup {
              //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return  getLayoutNormalView()
          }
        }
        showStateLoading()
        return mFlexibleLayout as FlexibleLayout
    }

    private fun getLayoutNormalView(): ViewGroup {
        return View.inflate(this, getLayoutId(), null) as ViewGroup
    }




    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.popActivity(this)
        SkinManager.getInstance().unregister(this)
        if (isEventBus()){
            unregister()
        }
    }



    protected fun isEventBus(): Boolean {
        return false
    }

    protected fun register() {
        EventManage.register(this)
    }

    protected fun unregister() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    protected fun getEventBus(event:BaseEvent){
        if (event!=null){
            getEventBus()
        }
    }

    protected fun getEventBus() {

    }


    /**
     * 成功
     * @param code
     */
    fun showStateSucceed(){
        mFlexibleLayout?.showPageWithState(FlexibleLayout.State.Succeed)
    }
    /**
     * 加载
     * @param code
     */
    fun showStateLoading(){
        mFlexibleLayout?.showPageWithState(FlexibleLayout.State.Loading)
    }
    /**
     * 失败
     * @param code
     */
    fun showStateError(){
        mFlexibleLayout!!.showPageWithState(FlexibleLayout.State.Error)
    }
    /**
     * 空数据
     * @param code
     */
    fun showStateEmpty(){
        mFlexibleLayout!!.showPageWithState(FlexibleLayout.State.Empty)
    }
    /**
     * 无网络
     * @param code
     */
    fun showStateNetWorkError(){
        mFlexibleLayout!!.showPageWithState(FlexibleLayout.State.NetWorkError)
    }

}