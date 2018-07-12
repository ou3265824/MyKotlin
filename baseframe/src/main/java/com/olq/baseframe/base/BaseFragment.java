//package com.olq.baseframe.base;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.olq.baseframe.event.BaseEvent;
//import com.olq.baseframe.utils.IntentUtils;
//import com.olq.baseframe.utils.ToastUtil;
//import com.olq.baseframe.widget.LoadDialog;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
///**
// * Created by dell on 2017/11/9.
// */
//
//public abstract class BaseFragment extends Fragment {
//
//
//
//    public abstract int getLayoutId();
//    public abstract void onCreateView(View view);
//
//    protected boolean isVisible;
//    private boolean isPrepared;
//    public boolean isFirst = true;
//
//    private Context mContext;
//    public Context getFragmentContext() {
//        return mContext;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view=inflater.inflate(getLayoutId(), container,false);
//        isPrepared = true;
//        onCreateView(view);
//        EventBus.getDefault().register(this);
//        return view;
//
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        EventBus.getDefault().unregister(this);
//    }
//    public  void Intent(Class<?> cs, boolean isFinish) {
//        IntentUtils.Intent(getActivity(),cs,isFinish);
//    }
//    public  void Intent(Class<?> cs, Bundle bundle, boolean isFinish) {
//        IntentUtils.Intent(getActivity(),cs,bundle,isFinish);
//    }
//    public  void Intent(Class<?> cs, Bundle bundle ) {
//        IntentUtils.Intent(getActivity(),cs,bundle,false);
//    }
//
//
//
//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
////        initPrepare();
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(getUserVisibleHint()){
//            isVisible = true;
//            lazyLoad();
//        }else{
//            isVisible = false;
//            onInvisible();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(getUserVisibleHint()){
//            setUserVisibleHint(true);
//        }
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getActivity();
//    }
//
//
//    /**
//     * 懒加载
//     */
//    protected void lazyLoad(){
//        if(!isPrepared || !isVisible || !isFirst){
//            return;
//        }
//        initData();
//        isFirst = false;
//    }
//
//    /**
//     * 在onActivityCreated中调用的方法，可以用来进行初始化操作。
//     */
////    protected abstract void initPrepare();
//
//    /**
//     * fragment被设置为不可见时调用
//     */
//    protected abstract void onInvisible();
//
//    /**
//     * 这里获取数据，刷新界面
//     */
//    protected abstract void initData();
//
//    public void LoadShow() {
//        LoadDialog.getInstance(getActivity()).show();
//    }
//
//    public void LoadCancel() {
//        LoadDialog.getInstance(getActivity()).cancel();
//    }
//    public void toastShort(String s) {
//        ToastUtil.showShort(getActivity(),s);
//    }
//    public void toastLong(String s) {
//        ToastUtil.showLong(getActivity(),s);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
//    public void onEvent(BaseEvent event) {
//    }
//
//}
