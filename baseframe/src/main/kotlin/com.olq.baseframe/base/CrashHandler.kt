package com.olq.baseframe.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.os.Process
import android.util.Log
import com.olq.baseframe.utils.LogUtils
import com.olq.baseframe.utils.ToastUtils
import java.io.PrintWriter
import java.io.StringWriter


@SuppressLint("StaticFieldLeak")
object CrashHandler:Thread.UncaughtExceptionHandler{

    lateinit var mDefaultHandler : Thread.UncaughtExceptionHandler
    lateinit var mContext:Context

    val isShow=true

    fun init(context: Context){
        mContext=context
        mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }


    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    override fun uncaughtException(thread: Thread, ex: Throwable) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("ex","----"+thread)
        Log.e("ex","----"+ex)
        if (!handleException(ex) && mDefaultHandler!=null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex)
        }else{
            //自己处理
            Thread.sleep(3000)
            Process.killProcess(Process.myPid())
            System.exit(0)
        }
    }

//    fun restartApp(){
//        val intent = Intent(ActivityManager.currentActivity(),LaunchActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                or  Intent.FLAG_ACTIVITY_CLEAR_TASK
//                or  Intent.FLAG_ACTIVITY_NEW_TASK)
//        mContext.startActivity(intent);
//        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
//    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    fun handleException(ex: Throwable):Boolean{
        if (ex==null){
            return true
        }
        Thread{
            run {
                Looper.prepare()

                if(isShow){
                    LogUtils.e("异常信息->"+ errorMsg(ex))
                    ToastUtils.show("程序出错，即将退出:\r\n" + ex.message);

                    //保存错误报告文件
//                    LogToFile.w("my",msg);//这句话可以先注释掉，这是我单独写的一个log写入类,下面已提供了该类**
                }
//              MsgPrompt.showMsg(mContext, "程序出错啦", msg+"\n点确认退出");
                Looper.loop()
            }
        }.start()

        return true
    }

    fun errorMsg(ex: Throwable):String{
        val writer = StringWriter()
        val printWriter =  PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        return "↓↓↓↓exception↓↓↓↓\n" + writer.toString()
    }
}