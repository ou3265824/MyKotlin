package com.olq.baseframe.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by 67639 on 2016/06/07.
 * 本类用于倒计时类
 */
public class CountDownUtils {
    public void setResidueTime(int residueTime) {
        this.residueTime = residueTime;
    }

    //剩余时间
    private int residueTime;
    private TextView button;
    private String begainMsg = "再次获取";
    private String defaultMsg = "获取验证码";
    private int begainColor;
    private int defaultColor;
    private Context context;

    private boolean begainIsRes;
    private boolean defaultIsRes;
    //防止重复设置的
    private boolean isClickBle = true;
    //获取验证码倒计时时间
    private int Authcode_countDownTime = 60;

    private boolean isNext = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (isClickBle) {
                        setButtonClickBle(false);//设置不可点击
                        isClickBle = false;
                    }
                    if (isNext) {
                        button.setText(begainMsg + residueTime-- + "s");
                        if (residueTime > 0) {
                            //延时重复发送
                            handler.sendEmptyMessageDelayed(1, 1000);
                        } else {
                            handler.sendEmptyMessage(2);
                        }
                    } else {
                        handler.sendEmptyMessage(2);
                    }

                    break;
                case 2:
                    setButtonClickBle(true);//设置可点击
                    isClickBle = true;
                    button.setText(defaultMsg);
                    break;
                default:
                    break;

            }
        }
    };


    /**
     * 清除状态信息
     */

    public void resetInfo() {
        setButtonClickBle(true);
    }

    /**
     * 设置按钮是否可点击
     *
     * @param b
     */
    private void setButtonClickBle(Boolean b) {
        if (b) {
            button.setClickable(true);
            if (defaultIsRes) {
                button.setBackgroundResource(defaultColor);
            } else {
                button.setBackgroundColor(defaultColor);
            }
        } else {

            button.setClickable(false);
            if (begainIsRes) {
                button.setBackgroundResource(begainColor);
            } else {
                button.setBackgroundColor(begainColor);
            }
        }


    }

    /**
     * @param button       需要倒计时的按钮
     * @param begainMsg    倒计时显示的文字
     * @param defaultMsg   默认显示的文字
     * @param begainColor  倒计时的时候按钮的颜色
     * @param defaultColor 默认按钮的颜色
     * @param context      context
     * @param begainIsRes  开始倒计时的颜色是否是图片文件
     * @param defaultIsRes 默认的颜色是否是图片文件
     */

    public CountDownUtils(TextView button, String defaultMsg, String begainMsg, int defaultColor, int begainColor, Context context, boolean defaultIsRes, boolean begainIsRes) {
        this.button = button;
        this.begainMsg = begainMsg;
        this.defaultMsg = defaultMsg;
        this.begainColor = begainColor;
        this.defaultColor = defaultColor;
        this.context = context;
        this.begainIsRes = begainIsRes;
        this.defaultIsRes = defaultIsRes;
    }

    public void beginwork() {
        residueTime = getCountDownTime(context);
        if (residueTime <= 0){
            //stopCountDown();
            return;
        }
        handler.sendEmptyMessage(1);
    }

    public void stopCountDown() {
        isNext = false;
    }

    /**保存获取短信验证码的倒计时开始时间*/
    public void saveCountdownTime(){
        SharePrefUtil.put(context,SharePrefUtil.AUTHCODE_COUNTDOWN_BEGINTIME, String.valueOf(new Date().getTime()));
    }

    /**
     * 重新获取短信验证码的剩余倒计时时间
     */
    public int getCountDownTime(Context context) {
        if (SharePrefUtil.get(context, SharePrefUtil.AUTHCODE_COUNTDOWN_BEGINTIME) == null) {
            return 0;
        }
        long time = new Date().getTime();
        if (((time - Long.parseLong(SharePrefUtil.get(context, SharePrefUtil.AUTHCODE_COUNTDOWN_BEGINTIME))) / 1000) >= Authcode_countDownTime) {
            return 0;
        }
        return Authcode_countDownTime - (int) ((time - Long.parseLong(SharePrefUtil.get(context, SharePrefUtil.AUTHCODE_COUNTDOWN_BEGINTIME))) / 1000);
    }

}
