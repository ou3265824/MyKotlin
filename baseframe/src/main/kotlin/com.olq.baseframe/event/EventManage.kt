package com.olq.baseframe.event

import org.greenrobot.eventbus.EventBus

object EventManage {

     fun register(obj:Any){
        EventBus.getDefault().register(obj)
    }
     fun unregister(obj:Any){
        EventBus.getDefault().unregister(obj)
    }


    fun post(sendName: String,receiveName:String,type:Int,obj:Any){
        val baseEvent=BaseEvent(sendName, receiveName, type,obj)
        EventBus.getDefault().post(baseEvent)
    }

}