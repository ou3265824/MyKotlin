package com.olq.baseframe.base

import android.app.Activity
import android.util.Log
import java.util.*

object ActivityManager {


        var activityStack: Stack<Activity>? = null



    /**
     * 获得现在栈内还有多少activity
     *
     * @return
     */
    fun count(): Int{
        if (activityStack != null) {
          return  activityStack!!.size
        }
        return 0
    }



    /**
     * 返回当前栈顶的activity
     *
     * @return
     */
    fun currentActivity(): Activity? {
        if (activityStack!!.size == 0) {
            return null
        }
        return activityStack!!.lastElement()
    }

    /**
     * 栈内是否包含此activity
     *
     * @param cls
     * @return
     */
    fun isContains(cls: Class<*>): Boolean {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 栈内是否包含此activity
     *
     * @param a
     * @return
     */
    fun isContains(a: Activity): Boolean {
        for (activity in activityStack!!) {
            if (activity == a) {
                return true
            }
        }
        return false
    }

    /**
     * activity入栈
     * 一般在baseActivity的onCreate里面加入
     *
     * @param activity
     */
    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }


    /**
     * 移除栈顶第一个activity
     */
    fun popTopActivity() {
        val activity = activityStack!!.lastElement()
        if (activity != null && !activity.isFinishing) {
            activity.finish()
        }
    }

    /**
     * activity出栈
     * 一般在baseActivity的onDestroy里面加入
     */
    fun popActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
        }
        if (!activity!!.isFinishing) {
            activity.finish()
            activity = null
        }
    }

    /**
     * activity出栈
     * 一般在baseActivity的onDestroy里面加入
     */
    fun popActivity(cls: Class<*>) {
        var deleteActivity: Activity? = null
        for (activity in activityStack!!) {
            if (activity.javaClass == cls && !activity.isFinishing) {
                deleteActivity = activity
                activity.finish()
            }
        }
        activityStack!!.remove(deleteActivity)
    }


    /**
     * 从栈顶往下移除 直到cls这个activity为止
     * 如： 现有ABCD popAllActivityUntillOne(B.class)
     * 则： 还有AB存在
     *
     *
     * 注意此方法 会把自身也finish掉
     *
     * @param cls
     */
    fun popAllActivityUntillOne(cls: Class<*>) {
        while (true) {
            val activity = currentActivity() ?: break
            if (activity.javaClass == cls) {
                break
            }
            popActivity(activity)
        }
    }

    /**
     * 所有的栈元素 除了 cls的留下 其他全部移除
     * 如： 现有ABCD popAllActivityUntillOne(B.class)
     * 则： 只有B存在
     * 注意此方法 会把自身也finish掉
     */
    fun popAllActivityExceptOne(cls: Class<*>) {
        //第一种  ConcurrentModificationException
        //        for (Activity activity : activityStack) {
        //            if (!activity.getClass().equals(cls) && !activity.isFinishing()) {
        //                activityStack.remove(activity);
        //                activity.finish();
        //            }
        //        }

        // 第四种 ConcurrentModificationException
        //        for (int i = 0; i < ; i++) {

        // 第三种 可行
        val iterator = activityStack!!.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next() as Activity
            if (activity.javaClass != cls && !activity.isFinishing) {
                //                activityStack.remove(activity);
                //               注意这里必须要用iterator的remove 上面的则错误
                iterator.remove()
                activity.finish()
            }
        }

        //第四种 可行 稍显复杂
        //        for (Activity activity : activityStack) {
        //            if (!activity.getClass().equals(cls) && !activity.isFinishing()) {
        //                deleteStack.add(activity);
        //                activity.finish();
        //            }
        //        }
        //        /**
        //         * 这里进行了特殊处理，如果直接在循环里面remove会报
        //         * concurrentmodificationexception 错误
        //         * 所以，这里用另一个栈加入进去，统一移除
        //         */
        //        activityStack.removeAll(deleteStack);
        //        deleteStack.clear();
        Log.d("debug", "dsfsaf size+:" + activityStack!!.size)
    }

    /**
     *
     * 移除所有的activity
     * 退出应用的时候可以调用
     * （非杀死进程）
     */
    fun popAllActivity() {
        for (i in activityStack!!.indices) {
            if (null != activityStack!![i] && !activityStack!![i].isFinishing) {
                activityStack!![i].finish()
            }
        }
        activityStack!!.clear()
    }




}