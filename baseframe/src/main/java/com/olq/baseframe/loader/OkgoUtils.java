//package com.olq.baseframe.loader;
//
//import android.view.View;
//
//import com.lzy.okgo.model.HttpHeaders;
//import com.lzy.okserver.download.DownloadListener;
//import com.lzy.okserver.download.DownloadTask;
//import com.shanghaizhida.octopusbase.callback.GsonCallBack;
//import com.shanghaizhida.octopusbase.callback.StringCallBack;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by dell on 2017/11/14.
// */
//
//public class OkgoUtils {
//
//
//    public static void sendByGet(String url, final StringCallBack callBack){
//        OkgoLoader.getInstance().sendByGet(url,callBack);
//    }
//
////    public static void sendByFile(String url, String fileDir,String fileName, final FileCallBack callBack){
////        OkgoLoader.getInstance().sendByFile(url,fileDir,fileName,callBack);
////    }
//
//    public static void sendByPost(String url, final StringCallBack callBack){
//        OkgoLoader.getInstance().sendByPost(null,url,null,null,callBack);
//    }
//    public static void sendByPost(String url, final GsonCallBack<?> callBack){
//        OkgoLoader.getInstance().sendByPost(null,url,null,null,callBack);
//    }
//    public static void sendByPost(String url, Object object , final GsonCallBack<?> callBack){
//        OkgoLoader.getInstance().sendByPost(null,url,object,null,callBack);
//    }
//    public static void sendByPost(String url, Object object , String token, String mobile, final GsonCallBack<?> callBack){
//        HttpHeaders headers=new HttpHeaders();
//        headers.put("token",token);
//        headers.put("mobile",mobile);
//        OkgoLoader.getInstance().sendByPost(null,url,object,headers,callBack);
//    }
//    public static void sendByPost(String url, Object object , final StringCallBack callBack){
//        OkgoLoader.getInstance().sendByPost(null,url,object,null,callBack);
//    }
//    public static void sendByPost(String url, Object object , String token, String mobile, final StringCallBack callBack){
//        HttpHeaders headers=new HttpHeaders();
//        headers.put("token",token);
//        headers.put("mobile",mobile);
//        OkgoLoader.getInstance().sendByPost(null,url,object,headers,callBack);
//    }
//    public static void sendByPost(String url , String token, String mobile, final StringCallBack callBack){
//        HttpHeaders headers=new HttpHeaders();
//        headers.put("token",token);
//        headers.put("mobile",mobile);
//        OkgoLoader.getInstance().sendByPost(null,url,null,headers,callBack);
//    }
//    public static void sendByPost(View view, String url , String token, String mobile, final GsonCallBack<?> callBack){
//        HttpHeaders headers=new HttpHeaders();
//        headers.put("token",token);
//        headers.put("mobile",mobile);
//        OkgoLoader.getInstance().sendByPost(view,url,null,headers,callBack);
//    }
//    public static void sendByPostFile(String url , String token, String mobile, Map map, List<File> list, final StringCallBack callBack){
//        HttpHeaders headers=new HttpHeaders();
//        headers.put("token",token);
//        headers.put("mobile",mobile);
//
//        OkgoLoader.getInstance().sendByPostUploadingFile(url, headers,map,list,callBack);
//    }
//
//    public static DownloadTask sendByDownloadFile(String url, String tag, DownloadListener listener){
//
//       return OkDownloadLoader.getInstance().sendByDownloadFile(url,tag,null,null,listener);
//    }
//
//
//
//}
