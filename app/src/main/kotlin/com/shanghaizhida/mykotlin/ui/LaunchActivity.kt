package com.shanghaizhida.mykotlin.ui

import android.content.Intent
import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Route
import com.shanghaizhida.mykotlin.R
import com.shanghaizhida.mykotlin.base.InitActivity
import com.shanghaizhida.mykotlin.config.RouterConfig
import kotlinx.android.synthetic.main.activity_launch.*

@Route(path = RouterConfig.LAUNCH)
class LaunchActivity : InitActivity() {
    override fun onLoadData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.activity_launch
    }

    val handler= Handler()
    val runnable= Runnable {
        run {
//            Intent(MainActivity::class.java,true )
            RouterConfig.getRouter(RouterConfig.MAIN)
        }
    }


    override fun onCreate() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showStateSucceed()
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        handler.postDelayed(runnable,1000)
        iv_launcher.setOnClickListener {
            RouterConfig.getRouter(RouterConfig.MAIN)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        if (handler!=null&&runnable!=null){
            handler.removeCallbacks(runnable)
        }
    }

}
