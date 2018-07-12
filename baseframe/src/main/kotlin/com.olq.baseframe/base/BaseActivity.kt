package com.olq.baseframe.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.olq.baseframe.event.BaseEvent
import com.olq.baseframe.event.EventManage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext:Context

    abstract fun getLayout(): Int
    abstract fun onCreate()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        ActivityManager.pushActivity(this)
        if (isEventBus()){
            register()
        }
        mContext=this
        onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.popActivity(this)
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

}