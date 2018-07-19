package com.olq.baseframe.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olq.baseframe.event.BaseEvent
import com.olq.baseframe.event.EventManage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment : Fragment() {

    ///view创建
    var isViewCreate = false
    //view可见
    var isUiVisibe = false
    var isFirsh = true

    //加载数据
    abstract fun loadData()

    //不可见
    abstract fun onInVisibe()

    //加载数据
    abstract fun init()

    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayout(), null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreate = true
        if (isEventBus()) {
            register()
        }
        init()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        isUiVisibe = isVisibleToUser
        if (isVisibleToUser) {
            lazyLoad()
        } else {
            onInVisibe()
        }
    }

    fun lazyLoad() {
        if (isUiVisibe && isViewCreate&&isFirsh) {
            loadData()
            isUiVisibe = false
            isViewCreate = false
            isFirsh=false
        }
    }

    fun getBaseActvity(): BaseActivity {
        return activity as BaseActivity
    }


    override fun onDestroy() {
        super.onDestroy()
        if (isEventBus()) {
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
    protected fun getEventBus(event: BaseEvent) {
        if (event != null) {
            getEventBus()
        }
    }

    protected fun getEventBus() {

    }

}