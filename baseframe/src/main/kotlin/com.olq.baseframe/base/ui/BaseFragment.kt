package com.olq.baseframe.base.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olq.baseframe.event.BaseEvent
import com.olq.baseframe.event.EventManage
import com.olq.baseframe.widget.FlexibleLayout
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment : Fragment() {

    private var mFlexibleLayout: FlexibleLayout? = null
    private var mContext: Context? = null
    ///view创建
    var isViewCreate = false
    //view可见
    var isUiVisibe = false
    var isFirsh = true

    //加载数据
    abstract fun onLoadData()

    //不可见
    abstract fun onInVisibe()

    //加载数据
    abstract fun init()

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext=activity
        val view =  getView(inflater,container)
//        val view = inflater.inflate(getLayoutId(), container, false)
        return view
    }

    private fun getView(inflater: LayoutInflater, container: ViewGroup?): ViewGroup {
         mFlexibleLayout=object :FlexibleLayout(mContext){
            override fun initNormalView(): ViewGroup {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                return getLayoutNormalView(inflater,container)
            }

            override fun loadData() {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                onLoadData()
            }

        }
        showStateLoading()
        return mFlexibleLayout as FlexibleLayout
    }

    private fun getLayoutNormalView(inflater: LayoutInflater, container: ViewGroup?): ViewGroup {
        return inflater.inflate(getLayoutId(), container, false) as ViewGroup
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
            onLoadData()
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