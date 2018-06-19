package com.olq.baseframe

import android.util.Log

class CrashHandler:Thread.UncaughtExceptionHandler{
    override fun uncaughtException(p0: Thread?, p1: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("ex","----"+p0)
        Log.e("ex","----"+p1)
    }

}