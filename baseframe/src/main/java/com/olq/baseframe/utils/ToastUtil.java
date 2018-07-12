package com.olq.baseframe.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shanghaizhida.octopusbase.R;
import com.shanghaizhida.octopusbase.base.BaseApp;
import com.shanghaizhida.octopusbase.widget.toast.Toast2;

//import android.widget.Toast;

/**
 * Toast工具类
 */
public final class ToastUtil {

//    public static void showShortPopup(String msg) {
//        if (areNotificationsEnabled()){
//            show(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT);
//        } else{
//            try {
//                Toast2.makeText(BaseActivity.getBaseActivity().getParent(), msg, LENGTH_SHORT).show();
//            }catch (Exception e){
//                LogUtils.e("toast异常："+e.toString());
//            }
//        }
//    }
    public static void showShortPopup(Context context, String msg) {
        if (areNotificationsEnabled()){
            show(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT);
        } else{
            try {
                Toast2.makeText(context, msg, LENGTH_SHORT).show();
            }catch (Exception e){
                LogUtils.e("toast异常："+e.toString());
            }
        }
    }
    public static void showShort(Context context, String msg) {
        if (areNotificationsEnabled()){
            show(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT);
        } else{
            makeText(context, msg, LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, String msg) {
        if (areNotificationsEnabled()){
            show(BaseApp.getInstance(), msg, Toast.LENGTH_LONG);
        } else {
            makeText(context, msg, LENGTH_LONG).show();
        }
    }

    public static void showShort(Context context, @StringRes int msg) {
        if (areNotificationsEnabled()) {
            Toast.makeText(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT).show();
        }else {
            makeText(context, msg, LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, @StringRes int msg) {
        if (areNotificationsEnabled()) {
            Toast.makeText(BaseApp.getInstance(), msg, Toast.LENGTH_LONG).show();
        }else {
            makeText(context, msg, LENGTH_LONG).show();
        }
    }

    //判断是否有通知权限
    public static boolean areNotificationsEnabled() {
        return NotificationManagerCompat.from(BaseApp.getInstance()).areNotificationsEnabled();
    }


    public static final int LENGTH_SHORT = 0x00;
    public static final int LENGTH_LONG = 0x01;
    private static ToastUtil mInstance;
    // 动画时间
    private final int ANIMATION_DURATION = 300;
    private static TextView mTextView;
    private ViewGroup container;
    private View mView;
    // 默认展示时间
    private int HIDE_DELAY = 2000;
    private LinearLayout mContainer;
    private AlphaAnimation mFadeOutAnimation;
    private AlphaAnimation mFadeInAnimation;
    private boolean isShow = false;
    private static Context mContext;
    private Handler mHandler = new Handler();

    private ToastUtil(Context context) {
        mContext = context;
        mContainer = (LinearLayout) ((Activity) context)
                .findViewById(R.id.mbContainer);
        if (mContainer == null) {
            container = (ViewGroup) ((Activity) context)
                    .findViewById(android.R.id.content);
            mView = ((Activity) context).getLayoutInflater().inflate(
                    R.layout.toast_layout, container);
            mContainer = (LinearLayout) mView.findViewById(R.id.mbContainer);
            mContainer.setVisibility(View.GONE);
            mTextView = (TextView) mView.findViewById(R.id.mbMessage);
        }

    }

    private static ToastUtil makeText(Context context, String message,
                                      int HIDE_DELAY) {
        if (mInstance == null) {
            mInstance = new ToastUtil(context);
        } else {
            // 考虑Activity切换时，Toast依然显示
            if (mContext != context) {
                mInstance = new ToastUtil(context);
            }

        }
        if (HIDE_DELAY == LENGTH_LONG) {
            mInstance.HIDE_DELAY = 2500;
        } else {
            mInstance.HIDE_DELAY = 1500;
        }
        if (mInstance.isShow) {
//            String showMessage = mTextView.getText().toString();
//            showMessage = showMessage + "\n\n" + message;
            mTextView.setText(message);
        } else {
            mTextView.setText(message);
        }

        return mInstance;
    }

    private static ToastUtil makeText(Context context, int resId, int HIDE_DELAY) {
        String mes = "";
        try {
            mes = context.getResources().getString(resId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return makeText(context, mes, HIDE_DELAY);
    }

    private void show() {

        // 显示动画
        mFadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        mFadeInAnimation.setDuration(ANIMATION_DURATION);
        // 消失动画
        mFadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        mFadeOutAnimation.setDuration(ANIMATION_DURATION);
        mFadeOutAnimation
                .setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // 消失动画后更改状态为 未显示
                        isShow = false;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // 隐藏布局，不使用remove方法为防止多次创建多个布局
                        mContainer.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

        if (isShow) {
            // 若已经显示，则不再次显示 但是结束动画要更新
            mHandler.removeCallbacks(mHideRunnable);
            mHandler.postDelayed(mHideRunnable, HIDE_DELAY);
        } else {
            isShow = true;
            mContainer.setVisibility(View.VISIBLE);
            mContainer.startAnimation(mFadeInAnimation);
            mHandler.postDelayed(mHideRunnable, HIDE_DELAY);
        }

    }

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mContainer.startAnimation(mFadeOutAnimation);
        }
    };

    private void cancel() {
        mContainer.setVisibility(View.GONE);
        if (isShow) {
            isShow = false;
            mHandler.removeCallbacks(mHideRunnable);
        }
    }

    /**
     * 此方法主要为了解决用户在重启页面后单例还会持有上一个context，
     * 且上面的mContext.getClass().getName()其实是一样的
     * 所以使用上还需要在BaseActivity的onDestroy()方法中调用
     */
    public static void reset() {
        if (mInstance != null) {
            mInstance = null;
        }

    }

    private void setText(CharSequence s) {
        if (mInstance == null) return;

        TextView mTextView = (TextView) mView.findViewById(R.id.mbMessage);
        if (mTextView == null) {
            throw new RuntimeException(
                    "This Toast was not created with Toast.makeText()"
            );
        }
        mTextView.setText(s);
    }

    private void setText(int resId) {
        setText(mContext.getText(resId));
    }



    private static Toast mToast;
    public static final int TIME = 10000;// 显示时间

    /**
     * 显示信息
     *
     * @param context  (上下文)
     * @param message  (显示内容)
     * @param duration (显示时间)
     * @param gravity  (显示位置)
     */
    public static final void show(final Context context, final String message,
                                  int duration, int gravity) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, message, duration);
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    /**
     * 显示信息(默认底部显示)
     *
     * @param context  (上下文)
     * @param message  (显示内容)
     * @param duration (显示时间)
     */
    public static final void show(final Context context, final String message, int duration) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, message, duration);
        mToast.show();
    }

    /**
     * 显示信息(默认底部显示,默认显示时间:Toast.LENGTH_SHORT)
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
    public static final void show(final Context context, final String message) {
        show(context, message, Toast.LENGTH_SHORT);
    }


    /**
     * 显示信息(默认底部显示,默认显示时间:Toast.LENGTH_LONG)
     *
     * @param context (上下文)
     * @param message (显示内容)
     */
//    public static final void showLong(final Context context, final String message) {
//        show(context, message, Toast.LENGTH_LONG);
//    }
//
//    /**
//     * 显示信息(默认中间显示,默认显示时间:Toast.LENGTH_SHORT)
//     *
//     * @param context
//     * @param message
//     */
//    public static final void showCenter(final Context context,
//                                        final String message) {
//        show(context, message, Toast.LENGTH_SHORT, Gravity.CENTER);
//    }
//
//    /**
//     * 显示信息(默认中间显示,默认显示时间:Toast.LENGTH_LONG)
//     *
//     * @param context
//     * @param message
//     */
//    public static final void showCenterLong(final Context context,
//                                            final String message) {
//        show(context, message, Toast.LENGTH_LONG, Gravity.CENTER);
//    }

}