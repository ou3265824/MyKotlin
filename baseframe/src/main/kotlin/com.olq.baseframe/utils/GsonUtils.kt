package com.olq.baseframe.utils

import com.google.gson.Gson

object GsonUtils {

    private val gson = Gson()

    fun <T> getBeanFromJson(s: String, t: Class<T>): T {
        return gson.fromJson(s, t)
    }

    fun getStringFromJson(obj: Object):String{
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