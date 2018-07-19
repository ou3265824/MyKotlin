package com.olq.baseframe.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import java.io.File

object AppUtils {

    /**
     * 获取应用程序名称
     */
    fun getAppName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称 name
     */
    fun getVersionName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            return packageInfo.versionName

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 取到系统当前版本号 code
     * @param context
     * @return
     */
    fun getsystemsion(context: Context): Int {
        var systemversion = 0
        // 取到系统当前版本号
//        try {
            systemversion = context.packageManager.getPackageInfo(
                    context.packageName, 0).versionCode
//        } catch (e: PackageManager.NameNotFoundException) {
//            // TODO Auto-generated catch block
//            e.printStackTrace()
//        }

        return systemversion
    }

    /**
     * 检测某程序是否安装
     * @param context
     * @param packageName
     * @return
     */

    fun isInstalledApp(context: Context, packageName: String): Boolean {
        var flag: Boolean? = false

//        try {
            val pm = context.packageManager
            val pkgs = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES)
            for (pkg in pkgs) {
                // 当找到了名字和该包名相同的时候，返回
                if (pkg.packageName == packageName) {
                     flag = true
                }
            }
//        } catch (e: Exception) {
//            // TODO Auto-generated catch block
//            e.printStackTrace()
//        }

        return flag!!
    }


    /**
     * 安装.apk文件
     *
     * @param context
     */
    fun install(context: Context?, fileName: String) {
        if (TextUtils.isEmpty(fileName) || context == null) {
            return
        }
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.action = android.content.Intent.ACTION_VIEW
            intent.setDataAndType(Uri.fromFile(File(fileName)), "application/vnd.android.package-archive")
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 安装.apk文件
     *
     * @param context
     */
    fun install(context: Context, file: File) {
//        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            context.startActivity(intent)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }

//    private static String DEVICEKEY = "";

    /**
     * 根据mac地址+deviceid
     * 获取设备唯一编码
     * @return
     */
//    public static String getDeviceKey(Context context)
//    {
//        if ("".equals(DEVICEKEY))
//        {
//            String macAddress = "";
//            WifiManager wifiMgr = (WifiManager)context.getIns().getSystemService(context.WIFI_SERVICE);
//            WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
//            if (null != info)
//            {
//                macAddress = info.getMacAddress();
//            }
//            TelephonyManager telephonyManager =
//                    (TelephonyManager)context.getIns().getSystemService(context.TELEPHONY_SERVICE);
//            String deviceId = telephonyManager.getDeviceId();
//            DEVICEKEY = MD5Util.toMD5("android" + Constant.APPKEY + Constant.APPPWD + macAddress + deviceId);
//        }
//        return DEVICEKEY;
//    }


    /**
     * 获取手机及SIM卡相关信息
     * @param context
     * @return
     */
//    fun getPhoneInfo(context: Context): Map<String, String> {
//        val map = HashMap<String, String>()
//        val tm = context
//                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        val imei = tm.deviceId
//        val imsi = tm.subscriberId
//        val phoneMode = android.os.Build.MODEL
//        val phoneSDk = android.os.Build.VERSION.RELEASE
//        map["imei"] = imei
//        map["imsi"] = imsi
//        map["phoneMode"] = "$phoneMode##$phoneSDk"
//        map["model"] = phoneMode
//        map["sdk"] = phoneSDk
//        return map
//    }

}