package com.olq.baseframe.utils

import android.content.Context
import android.util.TypedValue

object DensityUtils {

    /**
     * dp转px
     *
     * @param context
     * @param
     * @return
     */
    fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.resources.displayMetrics).toInt()
    }

    /**
     * sp转px
     *
     * @param context
     * @param
     * @return
     */
    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.resources.displayMetrics).toInt()
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    fun px2dp(context: Context, pxVal: Float): Float {
        val scale = context.resources.displayMetrics.density
        return pxVal / scale
    }

    /**
     * px转sp
     *
     * @param
     * @param pxVal
     * @return
     */
    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }

    /**
     * dip转px
     */
    fun dipToPx(context: Context, dip: Float): Int {
        return (dip * context.resources.displayMetrics.density + 0.5f).toInt()
    }


}