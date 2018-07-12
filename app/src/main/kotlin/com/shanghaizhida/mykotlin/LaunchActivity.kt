package com.shanghaizhida.mykotlin

import android.os.Handler

class LaunchActivity : InitActivity() {

    override fun onCreate() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val handler= Handler()
        val runnable= Runnable {
            run {
                Intent(MainActivity::class.java )
            }
        }
        handler.postDelayed(runnable,3000)
    }

    override fun getLayout(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.activity_launch
    }

}
