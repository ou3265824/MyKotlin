package com.shanghaizhida.mykotlin

import android.content.Intent
import android.os.Handler
import com.shanghaizhida.mykotlin.base.InitActivity


class LaunchActivity : InitActivity() {

    val handler= Handler()
    val runnable= Runnable {
        run {
//            Intent(MainActivity::class.java,true )
//            Router.build("main").go(this)
        }
    }

    override fun onCreate() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        handler.postDelayed(runnable,3000)
    }

    override fun getLayout(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return R.layout.activity_launch
    }

    override fun onDestroy() {
        super.onDestroy()
        if (handler!=null&&runnable!=null){
            handler.removeCallbacks(runnable)
        }
    }

}
