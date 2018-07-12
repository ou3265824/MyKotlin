package com.olq.baseframe.utils;

/**
 * Created by Administrator on 2017/9/27.
 * 按钮点击控制，防止用户短时间内多次点击同个按钮，导致出错
 */

public class ClickControlUtil {

//    private static Handler handler = new Handler();
//    public static boolean isCanClick = true;
//
//    /**
//     * 按钮点击控制，防止用户短时间内多次点击同个按钮，导致出错
//     * */
//    public static void afterClick(){
//        isCanClick = false;
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                isCanClick = true;
//            }
//        }, 3000);
//    }

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

}
