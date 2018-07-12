//package com.olq.baseframe.loader;
//
//import android.content.Context;
//
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.db.DownloadManager;
//import com.lzy.okgo.model.HttpHeaders;
//import com.lzy.okgo.model.HttpParams;
//import com.lzy.okgo.model.Progress;
//import com.lzy.okgo.request.GetRequest;
//import com.olq.baseframe.utils.FileUtils;
//import com.tencent.bugly.beta.download.DownloadTask;
//
//import java.io.File;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/6/19.
// */
//
//public class OkDownloadLoader implements XExecutor.OnAllTaskEndListener {
//    private static OkDownloadLoader mOkgoUtils;
//    private final OkDownload mOkDownload;
//    private final DownloadManager mDownloadManager;
//
//    private OkDownloadLoader() {
//        mOkDownload = OkDownload.getInstance();
//        mDownloadManager = DownloadManager.getInstance();
//    }
//
//    public void init(Context context) {
//        String path = FileUtils.createRootPath(context,"pdf");
//        mOkDownload.setFolder(path);//下载目录
//        mOkDownload.getThreadPool().setCorePoolSize(3);//设置同时下载数
//        mOkDownload.addOnAllTaskEndListener(this);//设置下载监听
//    }
//
//    public static OkDownloadLoader getInstance() {
//        if (mOkgoUtils == null) {
//            synchronized (OkDownloadLoader.class) {
//                if (mOkgoUtils == null) {
//                    mOkgoUtils = new OkDownloadLoader();
//                }
//            }
//        }
//        return mOkgoUtils;
//    }
//
//    public void removeOnAll() {
//        mOkDownload.removeOnAllTaskEndListener(this);//移除所有任务监听
//    }
//
//    @Override
//    public void onAllTaskEnd() {
//
//    }
//
//    public DownloadTask sendByDownloadFile(String url, String tag, HttpHeaders headers, HttpParams params, DownloadListener listener){
//        GetRequest<File> request= OkGo.<File>get(url)
//                .headers(headers)
//                .params(params);
////                    ;
//        DownloadTask task=OkDownload.request(tag,request)
////                .extra1() //扩展字段
//                .save()
//                .register(listener);
//
////                .register(new DownloadListener(tag) {
////                    @Override
////                    public void onStart(Progress progress) {
////
////                    }
////
////                    @Override
////                    public void onProgress(Progress progress) {
////
////                    }
////
////                    @Override
////                    public void onError(Progress progress) {
////
////                    }
////
////                    @Override
////                    public void onFinish(File file, Progress progress) {
////
////                    }
////
////                    @Override
////                    public void onRemove(Progress progress) {
////
////                    }
////                });
//        return task;
//    }
//
//    /**
//     * 全部的下载任务
//     * @return
//     */
//    public List<Progress> getDownAll(){
//        return mDownloadManager.getAll();
//    }
//
//    /**
//     * 下载完成的
//     * @return
//     */
//    public List<Progress> getDownFinished(){
//        return mDownloadManager.getFinished();
//    }
//
//    /**
//     * 正在下载的
//     * @return
//     */
//    public List<Progress> getDownloading(){
//        return mDownloadManager.getDownloading();
//    }
//
//    /**
//     * 获取下载任务
//     * @return
//     */
//    public Progress getProgress(String tag){
//        return mDownloadManager.get(tag);
//    }
//
//
//}