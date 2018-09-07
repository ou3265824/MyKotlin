package com.olq.baseframe.loader.call

import com.olq.baseframe.base.ui.BaseBean
import com.olq.baseframe.utils.GsonUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*


abstract class GsonCallBack<T>: StringCallBack(){

    lateinit var type:Type


    init{
        val genericSuperclass = javaClass.getGenericSuperclass()
        if (genericSuperclass is ParameterizedType) {
            this.type = (genericSuperclass as ParameterizedType).actualTypeArguments[0]
        } else {
            this.type = Any::class.java
        }
        print(type.toString())
    }

    override fun onResponse(json: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (type is Class<*>) {
            val name = (type as Class<*>).name
            if (name == String::class.java.name || name == Objects::class.java.name) {
                onSuccess(json as T)
                return
            }
        }
        getJson(json)

    }

    fun getJson(json: String){
        var baseBean = GsonUtils.fromJson(json, BaseBean::class.java)
        if (baseBean.code==200&&baseBean.data != null) {
            val data= GsonUtils.fromJson<T>(baseBean.data.toString(),type)
            onSuccess(data)
        } else {
            onError(baseBean.message)
        }
    }

    abstract fun onSuccess(t: T)

}