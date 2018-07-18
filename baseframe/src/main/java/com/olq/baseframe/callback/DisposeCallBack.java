//package com.olq.baseframe.callback;
//
//import android.graphics.Bitmap;
//
//import com.lzy.okgo.model.Response;
//import com.olq.baseframe.loader.call.GsonCallBack;
//import com.olq.baseframe.loader.call.HttpCallBack;
//import com.olq.baseframe.loader.call.StringCallBack;
//import com.olq.baseframe.utils.GsonUtils;
//
//import java.io.File;
//
//
///**
// * Created by Administrator on 2017/1/23.
// */
//
//public class DisposeCallBack {
//
//    public <T> void onSuccess(HttpCallBack<T> callback, Response response){
//        if (callback!=null){
//            if (callback instanceof GsonCallBack){
//                GsonCallBack gsonCallBack=((GsonCallBack) callback);
//                gsonCallBack.onSuccess(GsonUtils.getBeanFromJson(String.valueOf(response.body()),gsonCallBack.getType()));
//            }else if(callback instanceof StringCallBack){
//                    ((StringCallBack) callback).onSuccess(response.body()+"");
//            }
//        }
//    }
//    public <T> void onSuccess(HttpCallBack<T> callback, Bitmap result, Response response){
//        if (callback!=null){
//            if (callback instanceof BitmapCallBack){
//                ((BitmapCallBack) callback).onSuccess(result);
//            }
//        }
//    }
//    public <T> void onSuccess(HttpCallBack<T> callback, File result, Response response){
//        if (callback!=null){
//            if (callback instanceof FileCallBack){
//                ((FileCallBack) callback).onSuccess(result);
//            }
//        }
//    }
//
//    public <T> void onError(HttpCallBack<T> callback,  Response response){
//        if(callback!=null)
//        {
//            if (callback instanceof GsonCallBack){
//                GsonCallBack gsonCallBack=((GsonCallBack) callback);
//                gsonCallBack.onError(response);
//            }else if(callback instanceof StringCallBack){
//                ((StringCallBack) callback).onError(response);
//            }
////            ErrorBean baseBean= null;
////            try {
////                baseBean = GsonUtils.getBeanFromJson(response.body().string(),ErrorBean.class);
////            } catch (IOException e1) {
////                e1.printStackTrace();
////            }
//        }
//
//    }
//
//    public <T> void onUnusual(HttpCallBack<T> callback, String response){
//        if (callback!=null){
//            if (callback instanceof GsonCallBack){
//                GsonCallBack gsonCallBack=((GsonCallBack) callback);
//                gsonCallBack.onUnusual(response);
//            }else if(callback instanceof StringCallBack){
//                ((StringCallBack) callback).onUnusual(response);
//            }
//        }
//    }
//
//}
