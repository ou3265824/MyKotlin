package com.olq.baseframe.utils

import com.google.gson.Gson
import java.lang.reflect.Type

object GsonUtils {

    private val gson = Gson()

    fun <T> fromJson(s: String?, t: Class<T>?): T {
        return gson.fromJson(s, t)
    }
    fun <T> fromJson(s: String?, type:Type?): T {
        return gson.fromJson(s, type)
    }

    fun toJson(obj: Object):String{
       return gson.toJson(obj)
    }


    fun getlistToString(list: List<String>?): String? {
        if (list == null) {
            return null
        }
        val result = StringBuilder()
        var first = true
        //第一个前面不拼接","
        for (string in list) {
            if (first) {
                first = false
            } else {
                result.append(",")
            }
            result.append(string)
        }
        return result.toString()
    }


}