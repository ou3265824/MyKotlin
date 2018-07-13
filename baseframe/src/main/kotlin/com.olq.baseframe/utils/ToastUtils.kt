package com.olq.baseframe.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object ToastUtils{

    var mToast: Toast? = null
    val TIME = 10000// 显示时间
    lateinit var mContext:Context

    fun init(context: Context) {
        mContext=context
    }

    /**
     * 显示信息
     *
     * @param context  (上下文)
     * @param message  (显示内容)
     * @param duration (显示时间)
     * @param gravity  (显示位置)
     */
    fun show(context: Context,view: View?, message: String?,
             duration: Int, gravity: Int?,x:Int,y:Int) {
        if (mToast != null) {
            mToast!!.cancel()
            mToast = null
        }
        if (view!=null){
            mToast!!.view=view
        }else{
            mToast = Toast.makeText(context, message, duration)
        }
        if (gravity != null) {
            mToast!!.setGravity(gravity, x, y)
        }
        mToast!!.show()
    }


    /**
     * 显示信息(默认显示)   自定义布局显示
     *
     * @param context  (上下文)
     * @param message  (显示内容)
     * @param duration (显示时间)
     */
    fun show( message: String, duration: Int,gravity: Int?) {
        show(mContext,null,message,duration,gravity,0,0)
    }
    fun show( view: View?, duration: Int,gravity: Int?) {
        show(mContext,view,null,duration,gravity,0,0)
    }

    /**
     * 显示短的
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    fun show( message: String,gravity: Int?) {
        show(message, Toast.LENGTH_SHORT,gravity)
    }
    fun show(  view: View?,gravity: Int?) {
        show( view, Toast.LENGTH_SHORT,gravity)
    }
    /**
     * 显示长的
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    fun showLong( message: String,gravity: Int) {
        show( message,Toast.LENGTH_LONG,gravity)
    }
    fun showLong(  view: View?,gravity: Int) {
        show( view, Toast.LENGTH_LONG,gravity)
    }


    /**
     * 显示信息(默认底部显示,默认显示时间:Toast.LENGTH_LONG)
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    fun show( message: String) {
        show( message, null)
    }
    fun show( view: View?) {
        show( view, null)
    }

    fun showLong( message: String) {
        show( message, null)
    }
    fun showLong(  view: View?) {
        show( view,null)
    }

}