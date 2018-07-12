package com.olq.baseframe.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle

object IntentUtils{

    fun Intent(context: Context?, cs: Class<*>, bundle: Bundle?, isFinish: Boolean) {
        if (context == null) {
            return
        }
        val i = android.content.Intent(context, cs)
        if (bundle != null) {
            i.putExtras(bundle)
        }
        context.startActivity(i)
        if (isFinish) {
            val activity = context as Activity?
            activity!!.finish()
        }
    }






}