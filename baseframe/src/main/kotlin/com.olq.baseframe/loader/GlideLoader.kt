package com.olq.baseframe.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

object GlideLoader{

    fun getLoadUrl(context:Context,view:ImageView,url:String){
        Glide.with(context).load(url).into(view)
    }
    fun getLoadUrl(context:Context,view:ImageView,url:File){
        Glide.with(context).load(url).into(view)
    }
    fun getLoadUrl(context:Context,view:ImageView,url:Integer){
        Glide.with(context).load(url).into(view)
    }

}