package com.olq.baseframe.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object ToastUtils{

    var mToast: Toast? = null
    val TIME = 10000// 显示时间

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
    fun show(context: Context, message: String, duration: Int,gravity: Int?) {
        show(context,null,message,duration,gravity,0,0)
    }
    fun show(context: Context, view: View?, duration: Int,gravity: Int?) {
        show(context,view,null,duration,gravity,0,0)
    }

    /**
     * 显示短的
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    fun show(context: Context, message: String,gravity: Int?) {
        show(context, message, Toast.LENGTH_SHORT,gravity)
    }
    fun show(context: Context,  view: View?,gravity: Int?) {
        show(context, view, Toast.LENGTH_SHORT,gravity)
    }
    /**
     * 显示长的
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    fun showLong(context: Context, message: String,gravity: Int) {
        show(context, message, Toast.LENGTH_LONG,gravity)
    }
    fun showLong(context: Context,  view: View?,gravity: Int) {
        show(context, view, Toast.LENGTH_LONG,gravity)
    }


    /**
     * 显示信息(默认底部显示,默认显示时间:Toast.LENGTH_LONG)
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    fun show(context: Context, message: String) {
        show(context, message, null)
    }
    fun show(context: Context, view: View?) {
        show(context, view, null)
    }

    fun showLong(context: Context, message: String) {
        show(context, message, null)
    }
    fun showLong(context: Context,  view: View?) {
        show(context, view,null)
    }

}