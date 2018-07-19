package com.olq.baseframe.utils

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.provider.Settings
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager

object ScreenUtils {

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    fun getStatusHeight(context: Context): Int {

        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(`object`).toString())
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenWidth(activity)
        val height = getScreenHeight(activity)
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height)
        view.destroyDrawingCache()
        return bp

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top

        val width = getScreenWidth(activity)
        val height = getScreenHeight(activity)
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, (height - statusBarHeight))
        view.destroyDrawingCache()
        return bp

    }

    // 不改变控件位置，修改控件大小
    fun changeWH(v: View, W: Int, H: Int) {
        val params = v.layoutParams as ViewGroup.LayoutParams
        params.width = W
        params.height = H
        v.layoutParams = params
    }

    // 修改控件的高
    fun changeH(v: View, H: Int) {
        val params = v.layoutParams as ViewGroup.LayoutParams
        params.height = H
        v.layoutParams = params
    }

    // 修改控件的宽
    fun changeW(v: View, W: Int) {
        val params = v.layoutParams as ViewGroup.LayoutParams
        params.width = W
        v.layoutParams = params
    }

//    /**
//     * 获得当前屏幕亮度值
//     *
//     * @param mContext
//     * @return 0~100
//     */
//    public static float getScreenBrightness(Context mContext) {
//        int screenBrightness = 255;
//        try {
//            screenBrightness = Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return screenBrightness / 255.0F * 100;
//    }


    enum class EScreenDensity {
        XXHDPI, //超高分辨率    1080×1920
        XHDPI, //超高分辨率    720×1280
        HDPI, //高分辨率         480×800
        MDPI
        //中分辨率         320×480
    }

    fun getDisply(context: Context): EScreenDensity {
        val eScreenDensity: EScreenDensity
        //初始化屏幕密度
        val dm = context.applicationContext.resources.displayMetrics
        val densityDpi = dm.densityDpi

        if (densityDpi <= 160) {
            eScreenDensity = EScreenDensity.MDPI
        } else if (densityDpi <= 240) {
            eScreenDensity = EScreenDensity.HDPI
        } else if (densityDpi < 400) {
            eScreenDensity = EScreenDensity.XHDPI
        } else {
            eScreenDensity = EScreenDensity.XXHDPI
        }
        return eScreenDensity
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    fun dp2px(mContext: Context, dp: Int): Int {
        return (dp * mContext.resources.displayMetrics.density + 0.5f).toInt()
    }

    /**
     * 将px转换成dp
     *
     * @param px
     * @return
     */
    fun pxToDpInt(mContext: Context, px: Float): Int {
        return (px / mContext.resources.displayMetrics.density + 0.5f).toInt()
    }

    fun getActionBarSize(context: Context): Int {
        val tv = TypedValue()
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return 0
    }

    /**
     * 获取titleBar的高度
     * @param mContext
     * @return
     */
    fun getTitleBarHeight(mContext: Context): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        if (mContext.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mContext.resources.displayMetrics)
        }
        return actionBarHeight
    }

    /**
     * 获取statusBar高度
     * @param mContext
     * @return
     */
    fun getStatusBarHeight(mContext: Context): Int {
        var height = 0
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = Integer.parseInt(field.get(obj).toString())
            height = mContext.resources.getDimensionPixelSize(x)
        } catch (e: Exception) {
            //            logger.log(Level.WARNING, e.getMessage());
        }

        return height
    }

    /**
     * 当前是否是横屏
     *
     * @param context context
     * @return boolean
     */
    fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    /**
     * 当前是否是竖屏
     *
     * @param context context
     * @return boolean
     */
    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    /**
     * 调整窗口的透明度  1.0f,0.5f 变暗
     *
     * @param from    from>=0&&from<=1.0f
     * @param to      to>=0&&to<=1.0f
     * @param context 当前的activity
     */
    fun dimBackground(from: Float, to: Float, context: Activity) {
        val window = context.window
        val valueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener { animation ->
            val params = window.attributes
            params.alpha = animation.animatedValue as Float
            window.attributes = params
        }
        valueAnimator.start()
    }

    /**
     * 判断是否开启了自动亮度调节
     *
     * @param activity
     * @return
     */
    fun isAutoBrightness(activity: Activity): Boolean {
        var isAutoAdjustBright = false
        try {
            isAutoAdjustBright = (Settings.System.getInt(
                    activity.contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }

        return isAutoAdjustBright
    }

    /**
     * 关闭亮度自动调节
     *
     * @param activity
     */
    fun stopAutoBrightness(activity: Activity) {
        Settings.System.putInt(activity.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
    }

    /**
     * 开启亮度自动调节
     *
     * @param activity
     */

    fun startAutoBrightness(activity: Activity) {
        Settings.System.putInt(activity.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
    }

    /**
     * 获得当前屏幕亮度值
     *
     * @param mContext
     * @return 0~100
     */
    fun getScreenBrightness(mContext: Context): Float {
        var screenBrightness = 255
        try {
            screenBrightness = Settings.System.getInt(mContext.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return screenBrightness / 255.0f * 100
    }

    /**
     * 设置当前屏幕亮度值
     *
     * @param paramInt 0~100
     * @param mContext
     */
    fun saveScreenBrightness(paramInt: Int, mContext: Context) {
        var paramInt = paramInt
        if (paramInt <= 5) {
            paramInt = 5
        }
        try {
            val f = paramInt / 100.0f * 255
            Settings.System.putInt(mContext.contentResolver, Settings.System.SCREEN_BRIGHTNESS, f.toInt())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 设置Activity的亮度
     *
     * @param paramInt
     * @param mActivity
     */
    fun setScreenBrightness(paramInt: Int, mActivity: Activity) {
        var paramInt = paramInt
        if (paramInt <= 5) {
            paramInt = 5
        }
        val localWindow = mActivity.window
        val localLayoutParams = localWindow.attributes
        val f = paramInt / 100.0f
        localLayoutParams.screenBrightness = f
        localWindow.attributes = localLayoutParams
    }

    fun immersion(activity: Activity) {
        //启动页隐藏状态栏
        activity.window.requestFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.clearFlags((WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION))
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }

}