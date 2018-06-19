package com.olq.baseframe.utils

import android.util.Log

object LogUtils {

    val isLog: Boolean = true;
    val tag="test"

    fun v(tag: String,msg: String){
        log(tag,Log.VERBOSE,msg)
    }
    fun v(msg: String){
        log(tag,Log.VERBOSE,msg)
    }

    fun d(tag: String,msg: String){
        log(tag,Log.DEBUG,msg)
    }
    fun d(msg: String){
        log(tag,Log.DEBUG,msg)
    }

    fun i(tag: String,msg: String){
        log(tag,Log.INFO,msg)
    }
    fun i(msg: String){
        log(tag,Log.INFO,msg)
    }

    fun w(tag: String,msg: String){
        log(tag,Log.WARN,msg)
    }
    fun w(msg: String){
        log(tag,Log.WARN,msg)
    }

    fun e(tag: String,msg: String){
        log(tag,Log.ERROR,msg)
    }
    fun e(msg: String){
        log(tag,Log.ERROR,msg)
    }



    fun log(tag: String,level: Int,msg: String){
        log(tag,level,msg,null)
    }

    fun log(tag: String, level: Int, msg: String, tr: Throwable?) {
        if (!isLog) {
            return
        }
        when (level) {
            Log.VERBOSE ->
                if (tr == null) {
                    Log.v(tag, msg)
                } else {
                    Log.v(tag, msg, tr)
                }
            Log.DEBUG ->
                if (tr == null) {
                    Log.d(tag, msg)
                } else {
                    Log.d(tag, msg, tr)
                }
            Log.INFO ->
                if (tr == null) {
                    Log.i(tag, msg)
                } else {
                    Log.i(tag, msg, tr)
                }
            Log.WARN ->
                if (tr == null) {
                    Log.w(tag, msg)
                } else {
                    Log.w(tag, msg, tr)
                }
            Log.ERROR ->
                if (tr == null) {
                    Log.e(tag, msg)
                } else {
                    Log.e(tag, msg, tr)
                }


        }

    }

}