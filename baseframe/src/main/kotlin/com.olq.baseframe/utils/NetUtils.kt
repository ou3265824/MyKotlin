package com.olq.baseframe.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings

object NetUtils {

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    fun isConnected(context: Context): Boolean {

        val connectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (null != connectivity) {

            val info = connectivity.activeNetworkInfo
            if (null != info && info.isConnected) {
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 判断是否是wifi连接
     */
    fun isWifi(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return false

        return cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI

    }

    /**
     * 打开网络设置界面
     */
    fun openSetting1(activity: Activity) {
        val intent = Intent("/")
        val cm = ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings")
        intent.component = cm
        intent.action = "android.intent.action.VIEW"
        activity.startActivityForResult(intent, 0)
    }

    /**
     * 打开网络设置界面
     */
    fun openSetting(activity: Activity) {
        //2.3适用 4.0报错
        //		Intent intent = new Intent("/");
        //		ComponentName cm = new ComponentName("com.android.settings",
        //				"com.android.settings.WirelessSettings");
        //		intent.setComponent(cm);
        //		intent.setAction("android.intent.action.VIEW");
        //		activity.startActivityForResult(intent, 0);

        //4.0适用
        activity.startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0x00)

        //		activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }

}