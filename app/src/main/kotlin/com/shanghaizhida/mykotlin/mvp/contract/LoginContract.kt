package com.shanghaizhida.mykotlin.mvp.contract

import com.olq.baseframe.base.mvp.BaseModel
import com.olq.baseframe.base.mvp.BasePresenter
import com.olq.baseframe.base.mvp.BaseView

class LoginContract{

    interface View : BaseView {
//        fun onSuccess(userBean: UserBean)
    }

    abstract class Presenter : BasePresenter<Model, View>() {


    }

    interface Model : BaseModel {

//        fun getLogin(session: String, callBack: GsonCallBack<UserBean>)
    }

}