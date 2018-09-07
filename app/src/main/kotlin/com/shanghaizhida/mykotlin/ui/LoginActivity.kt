package com.shanghaizhida.mykotlin.ui

import com.olq.baseframe.base.mvp.BaseModel
import com.olq.baseframe.base.mvp.BasePresenter
import com.olq.baseframe.base.mvp.BaseView
import com.shanghaizhida.mykotlin.R
import com.shanghaizhida.mykotlin.base.InitActivity
import kotlinx.android.synthetic.main.widget_title_bar.*

class LoginActivity : InitActivity<BasePresenter<BaseModel, BaseView>, BaseModel>() {
    override fun onLoadData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getLayoutId(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.activity_login
    }

    override fun onCreate() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showStateSucceed()
        tb_title.setText("登录")
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }




}
