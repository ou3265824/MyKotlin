//package com.olq.baseframe.base;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//
//import com.olq.baseframe.utils.IntentUtils;
//import com.olq.baseframe.utils.ScreenUtils;
//import com.olq.baseframe.utils.ToastUtil;
//import com.olq.baseframe.widget.LoadDialog;
//
//import org.greenrobot.eventbus.EventBus;
//
///**
// * Created by Administrator on 2017/6/19.
// */
//
//public abstract class BaseActivity extends AppCompatActivity {
//    protected String TAG = this.getClass().getSimpleName();
//
//    private AppManager mAppManager;
//
//    public abstract int getLayoutId();
//
//    public abstract void onCreate();
//
//    public static BaseActivity getBaseActivity() {
//        return mActivity;
//    }
//
//    private static BaseActivity mActivity;
//   public boolean isToutch=true;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mActivity=this;
//        setContentView(getLayoutId());
//        mAppManager= AppManager.getInstance();
//        mAppManager.pushActivity(this);
//        EventBus.getDefault().register(this);
//        onCreate();
//    }
//
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        LoadCancel();
//        ToastUtil.reset();
//        mAppManager.popActivity(this);
//        EventBus.getDefault().unregister(this);
//    }
//
//    /**
//     * 隐藏状态栏
//     */
//    public void immersion() {
//        ScreenUtils.immersion(this);
//    }
//
//    public <T extends View> T getViewId(int resId) {
//        return (T) super.findViewById(resId);
//    }
//
//    public <T extends View> T getViewId(View view, int resId) {
//        return (T) view.findViewById(resId);
//    }
//
//
//    public void LoadShow(long time) {
//        LoadDialog.getInstance(this).setTime(time);
//        LoadDialog.getInstance(this).show();
//    }
//    public void LoadShow() {
//        LoadDialog.getInstance(this).show();
//    }
//
//    public void LoadCancel() {
//        LoadDialog.getInstance(this).cancel();
//    }
//    public void toastShort(int s) {
//        ToastUtil.showShort(this,s);
//    }
//    public void toastLong(int s) {
//        ToastUtil.showLong(this,s);
//    }
//    public void toastShort(String s) {
//        ToastUtil.showShort(this,s);
//    }
//    public void toastLong(String s) {
//        ToastUtil.showLong(this,s);
//    }
//
//    public  void Intent(Class<?> cs, boolean isFinish) {
//        IntentUtils.Intent(this,cs,isFinish);
//    }
//    public  void Intent(Class<?> cs, Bundle bundle, boolean isFinish) {
//        IntentUtils.Intent(this,cs,bundle,isFinish);
//    }
//    public  void Intent(Class<?> cs, Bundle bundle) {
//        IntentUtils.Intent(this,cs,bundle,false);
//    }
//
//
//
//
//    private long time = 0;
//
//    public void exit() {
//        //如果在两秒大于2秒
//        if (System.currentTimeMillis() - time > 2000) {
//            //获得当前的时间
//            time = System.currentTimeMillis();
//            toastShort("再按一次退出程序");
//        } else {
//            mAppManager.popAllActivity();
//            System.exit(0);
//        }
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN&&isToutch) {
//            View v = getCurrentFocus();
//            if (isShouldHideKeyboard(v, ev)) {
//                hideKeyboard(v.getWindowToken());
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    /**
//     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
//     *
//     * @param v
//     * @param event
//     * @return
//     */
//    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = {0, 0};
//            v.getLocationInWindow(l);
//            int left = l[0],
//                    top = l[1],
//                    bottom = top + v.getHeight(),
//                    right = left + v.getWidth();
//            if (event.getX() > left && event.getX() < right
//                    && event.getY() > top && event.getY() < bottom) {
//                // 点击EditText的事件，忽略它。
//                return false;
//            } else {
//                return true;
//            }
//        }
//        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
//        return false;
//    }
//
//    /**
//     * 获取InputMethodManager，隐藏软键盘
//     * @param token
//     */
//    private void hideKeyboard(IBinder token) {
//        if (token != null) {
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (im != null) {
//                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//    }
//
//
//}
