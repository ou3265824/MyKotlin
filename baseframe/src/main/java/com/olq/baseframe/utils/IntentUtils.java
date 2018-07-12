//package com.olq.baseframe.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
///**
// * Created by dell on 2017/11/14.
// */
//
//public class IntentUtils {
//
//    public static void Intent(Context context, Class<?> cs, Bundle bundle, boolean isFinish) {
//        if (context==null){
//            return;
//        }
//        Intent i = new Intent(context, cs);
//        if (bundle != null){
//            i.putExtras(bundle);
//        }
//        context.startActivity(i);
//        if (isFinish){
//            Activity activity=(Activity)context;
//            activity.finish();
//        }
//    }
//
//    public static void Intent(Context context, Class<?> cs, Bundle bundle) {
//        Intent(context,cs,bundle,true);
//    }
//    public static void Intent(Context context, Class<?> cs, boolean isFinish) {
//        Intent(context,cs,null,isFinish);
//    }
//
//}
