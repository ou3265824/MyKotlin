package com.shanghaizhida.mykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.olq.baseframe.loader.OkgoLoader
import com.olq.baseframe.loader.call.GsonCallBack
import com.olq.baseframe.utils.LogUtils
import com.olq.baseframe.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(View.OnClickListener {

            val url="https://api.bmob.cn/1/login?username=" + et_phone.text.toString().trim() + "&password=" + et_pass.text.toString().trim();
            OkgoLoader.sendByGet(url,null,null,UserBean::class.java,object : GsonCallBack<UserBean>() {
                override fun onError() {
                   // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSucceed(t: UserBean) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    LogUtils.e("T",t.username+"----"+t.toString())
                    LogUtils.e(t.username+"----"+t.toString())
                    ToastUtils.show(applicationContext, t.username)
                }

            })

        })
    }



}
