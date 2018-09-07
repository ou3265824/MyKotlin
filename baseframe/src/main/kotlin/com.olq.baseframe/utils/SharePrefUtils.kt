package com.olq.baseframe.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.olq.baseframe.base.ui.BaseApplication
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Type

object SharePrefUtils {

    /**
     * 保存在手机里面的文件名
     */
    val FILE_NAME = "newmtrader_sharedata"

    lateinit var context: BaseApplication
    
    fun init(base: BaseApplication){
       this.context=base
    }

    fun  getEdit():SharedPreferences.Editor{
        return  getSharedPreferences().edit()
    }

    fun  getSharedPreferences():SharedPreferences{
        return  context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    fun put( key: String?, obj: Any?) {
        if ( key == null || obj == null) {
            return
        }
        val edit= getEdit()
        if (obj is String) {
            edit.putString(key, obj as String?)
        } else if (obj is Int) {
            edit.putInt(key, (obj as Int?)!!)
        } else if (obj is Boolean) {
            edit.putBoolean(key, (obj as Boolean?)!!)
        } else if (obj is Float) {
            edit.putFloat(key, (obj as Float?)!!)
        } else if (obj is Long) {
            edit.putLong(key, (obj as Long?)!!)
        } else {
            edit.putString(key, obj.toString())
        }

        SharedPreferencesCompat.apply(edit)
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param
     * @return
     */
    operator fun get( key: String, defaultObject: Any): Any? {
        val sp = getSharedPreferences()

        if (defaultObject is String) {
            return sp.getString(key, defaultObject)
        } else if (defaultObject is Int) {
            return sp.getInt(key, defaultObject)
        } else if (defaultObject is Boolean) {
            return sp.getBoolean(key, defaultObject)
        } else if (defaultObject is Float) {
            return sp.getFloat(key, defaultObject)
        } else if (defaultObject is Long) {
            return sp.getLong(key, defaultObject)
        }

        return null
    }


    operator fun get( key: String): Any? {
        return get(key,"")
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    fun remove( key: String) {
        val editor = getEdit()
        editor.remove(key)
        SharedPreferencesCompat.apply(editor)
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    fun clear() {
        val editor = getEdit()
        editor.clear()
        SharedPreferencesCompat.apply(editor)
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    fun contains(key: String): Boolean {

        val sp = getSharedPreferences()
        return sp.contains(key)
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    fun getAll(): Map<String, *> {

        val sp = getSharedPreferences()
        return sp.all
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private object SharedPreferencesCompat {
        private val sApplyMethod = findApplyMethod()

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        private fun findApplyMethod(): Method? {
            try {
                val clz = SharedPreferences.Editor::class.java
                return clz.getMethod("apply")
            } catch (e: NoSuchMethodException) {
            }

            return null
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        fun apply(editor: SharedPreferences.Editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor)
                    return
                }
            } catch (e: IllegalArgumentException) {
            } catch (e: IllegalAccessException) {
            } catch (e: InvocationTargetException) {
            }

            editor.commit()
        }
    }


    // 通过类名字去获取一个对象
    fun <T> getObject(context: Context, clazz: Class<T>): T? {
        val key = getKey(clazz)
        val json = getString(context, key, null)
        if (TextUtils.isEmpty(json)) {
            return null
        }
        try {
            return GsonUtils.fromJson(json!!, clazz)
        } catch (e: Exception) {
            return null
        }

    }

    // 通过Type去获取一个泛型对象
    fun <T> getObject(context: Context, type: Type): T? {
        return getObject<T>(context, null, type)
    }

    fun <T> getObject(context: Context, k: String?, type: Type): T? {
        val key: String
        if (k != null) {
            key = k
        } else {
            key = getKey(type)
        }
        val json = getString(context, key, null)
        if (TextUtils.isEmpty(json)) {
            return null
        }
        try {
            val gson = Gson()
            return gson.fromJson(json, type)
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * 保存一个对象，object必须是普通类，而不是泛型，如果是泛型,请使用 {
     * @link SpUtils#putObject(Context, Object, Type)}
     *
     * @param context
     * @param object
     */
    fun putObject(context: Context, `object`: Any) {
        val key = getKey(`object`.javaClass)
        val gson = Gson()
        val json = gson.toJson(`object`)
        putString(context, key, json)
    }

    /**
     * 保存一个泛型对象
     *
     * @param context
     * @param object
     * @param type    如果你要保存 List<Person> 这个类, type应该 传入 new TypeToken<List></List><Person>>() {}.getType()
    </Person></Person> */
    fun putObject(context: Context, `object`: Any, type: Type) {
        putObject(context, null, `object`, type)
    }

    fun putObject(context: Context, k: String?, `object`: Any, type: Type) {
        val key: String
        if (k != null) {
            key = k
        } else {
            key = getKey(type)
        }
        val gson = Gson()
        val json = gson.toJson(`object`)
        putString(context, key, json)
    }

    fun removeObject(context: Context, clazz: Class<*>) {
        removes(context, getKey(clazz))
    }

    fun removeObject(context: Context, type: Type) {
        removes(context, getKey(type))
    }

    fun getKey(clazz: Class<*>): String {
        return clazz.name
    }

    fun getKey(type: Type): String {
        return type.toString()
    }

    fun removes(context: Context, key: String) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.remove(key)
        edit.commit()
    }

    fun putString(context: Context, key: String, value: String) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString(key, value)
        edit.commit()
    }

    fun getString(context: Context, key: String, defValue: String?): String? {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sp.getString(key, defValue)
    }


}