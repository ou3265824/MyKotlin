package com.shanghaizhida.mykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.olq.baseframe.loader.OkgoLoader
import com.olq.baseframe.loader.call.GsonCallBack
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(View.OnClickListener {

            val url="https://api.bmob.cn/1/login?username=" + et_phone.text.toString().trim() + "&password=" + et_pass.text.toString().trim();
            OkgoLoader.sendByGet(UserBean::class.java,url,null,null,object : GsonCallBack<UserBean>() {
                override fun onError() {
                   // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSucceed(t: UserBean) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.e("test",t.username+"----"+t.toString())
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }

            })

        })
    }

}
