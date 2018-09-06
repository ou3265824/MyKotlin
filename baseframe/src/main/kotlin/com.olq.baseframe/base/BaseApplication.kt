package com.olq.baseframe.base

import android.app.Application
import android.content.Context
import com.olq.baseframe.loader.OkgoLoader
import com.olq.baseframe.utils.SharePrefUtils
import com.olq.baseframe.utils.ToastUtils
import com.zhy.changeskin.SkinManager

abstract class BaseApplication : Application() {

    lateinit var mApplicationContext:Context

    abstract fun onInit()

    override fun onCreate() {
        super.onCreate()
        mApplicationContext=this
        OkgoLoader.init(this)
        SharePrefUtils.init(this)
        ToastUtils.init(this)
        CrashHandler.init(this)
        SkinManager.getInstance().init(this)
//        Bugly.init(this, "e423995e12", false)

//        if (Build.isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog()     // 打印日志
//            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this) // 尽可能早，推荐在Application中初始化
        onInit()
    }




    /**
     * public class ScreenAdaptUtil {
    private static final int STANDER_SCREEN_WIDTH_IN_DP = 360;

    private static float sNonCompatDensity;
    private static float sNonCompatScaleDensity;

    public static void setCustomDensity(Activity activity, Application application) {
    final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

    if (sNonCompatDensity == 0) {
    sNonCompatDensity = appDisplayMetrics.density;
    sNonCompatScaleDensity = appDisplayMetrics.scaledDensity;

    application.registerComponentCallbacks(new ComponentCallbacks() {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    if (newConfig != null && newConfig.fontScale > 0) {
    sNonCompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
    }
    }

    @Override
    public void onLowMemory() {

    }
    });
    }

    final float targetDensity = (float) appDisplayMetrics.widthPixels / STANDER_SCREEN_WIDTH_IN_DP;
    final float targetScaleDensity = targetDensity * (sNonCompatScaleDensity / sNonCompatDensity);
    final int targetDensityDpi = (int) (160 * targetDensity);

    appDisplayMetrics.density = targetDensity;
    appDisplayMetrics.scaledDensity = targetScaleDensity;
    appDisplayMetrics.densityDpi = targetDensityDpi;

    final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
    activityDisplayMetrics.density = targetDensity;
    activityDisplayMetrics.scaledDensity = targetScaleDensity;
    activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
    }
     */
}