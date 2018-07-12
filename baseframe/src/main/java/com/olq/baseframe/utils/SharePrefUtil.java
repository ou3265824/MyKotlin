//package com.olq.baseframe.utils;
//
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.text.TextUtils;
//
//import com.google.gson.Gson;
//import com.shanghaizhida.octopusbase.base.BaseApp;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.util.Map;
//
///**
// * 用共享参数操作数据类
// */
//public class SharePrefUtil {
//
//    /**
//     * 保存在手机里面的文件名
//     */
//    public static final String FILE_NAME = "newmtrader_sharedata";
//    public static final String FILE_PHONE_NAME = "newmtrader_phone";
//
//    /**保存在共享里的数据*/
//    public static final String APPLOGIN_ACCOUNT = "appLogin_account";
//    public static final String APPLOGIN_PASSWORD = "appLogin_password";
//    public static final String APPLOGIN_LOGINTIME = "appLogin_loginTime";
//    public static final String FUTURESLOGIN_IP_INDEX = "ipIndex";
//    public static final String STOCKLOGIN_IP_INDEX = "stockIpIndex";
//    public static final String AUTHCODE_COUNTDOWN_BEGINTIME = "authCode_countDown_beginTime";
//    public static final String REMIND_ENTRUST_SOUND = "remind_entrust_sound";
//    public static final String REMIND_ENTRUST_VIBRATE = "remind_entrust_vibrate";
//    public static final String REMIND_DEAL_SOUND = "remind_deal_sound";
//    public static final String REMIND_DEAL_VIBRATE = "remind_deal_vibrate";
//    public static final String REMIND_CANCEL_SOUND = "remind_cancel_sound";
//    public static final String REMIND_CANCEL_VIBRATE = "remind_cancel_vibrate";
//    public static final String REMIND_YUJING_SOUND = "remind_yujing_sound";
//    public static final String REMIND_YUJING_VIBRATE = "remind_yujing_vibrate";
//    public static final String SYSTEMSET_SCREENBRIGHT = "systemset_screenbright";
//    public static final String SYSTEMSET_ORDERCONFIRM = "systemset_orderconfirm";
//    public static final String YINGSUN_LOCAL_NO = "yingsunLocalNo";
//    public static final String TRANSACTION_SHOW = "transaction_show";
//
//    /**
//     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
//     *
//     * @param context
//     * @param key
//     * @param object
//     */
//    public static void put(Context context, String name, String key, Object object) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        if (context == null || key == null || object == null) {
//            return;
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(name,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//
//        if (object instanceof String) {
//            editor.putString(key, (String) object);
//        } else if (object instanceof Integer) {
//            editor.putInt(key, (Integer) object);
//        } else if (object instanceof Boolean) {
//            editor.putBoolean(key, (Boolean) object);
//        } else if (object instanceof Float) {
//            editor.putFloat(key, (Float) object);
//        } else if (object instanceof Long) {
//            editor.putLong(key, (Long) object);
//        } else {
//            editor.putString(key, object.toString());
//        }
//
//        SharedPreferencesCompat.apply(editor);
//    }
//    public static void put(Context context, String key, Object object) {
//        put( context,FILE_NAME,  key,  object);
//    }
//
//    /**
//     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
//     *
//     * @param context
//     * @param key
//     * @param
//     * @return
//     */
//    public static Object get(Context context, String key, Object defaultObject) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
//                Context.MODE_PRIVATE);
//
//        if (defaultObject instanceof String) {
//            return sp.getString(key, (String) defaultObject);
//        } else if (defaultObject instanceof Integer) {
//            return sp.getInt(key, (Integer) defaultObject);
//        } else if (defaultObject instanceof Boolean) {
//            return sp.getBoolean(key, (Boolean) defaultObject);
//        } else if (defaultObject instanceof Float) {
//            return sp.getFloat(key, (Float) defaultObject);
//        } else if (defaultObject instanceof Long) {
//            return sp.getLong(key, (Long) defaultObject);
//        }
//
//        return null;
//    }
//
//    public static String get(Context context, String name, String key) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        if (context == null || key == null) {
//            return null;
//        }
//        SharedPreferences sp = context.getSharedPreferences(name,
//                Context.MODE_PRIVATE);
//        return sp.getString(key, null);
//    }
//    public static String get(Context context, String key) {
//        return get(context,FILE_NAME,key);
//    }
//
//    public static Boolean getBoolean(Context context, String key) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
//                Context.MODE_PRIVATE);
//        return sp.getBoolean(key, false);
//    }
//
//    /**
//     * 移除某个key值已经对应的值
//     *
//     * @param context
//     * @param key
//     */
//    public static void remove(Context context, String key) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.remove(key);
//        SharedPreferencesCompat.apply(editor);
//    }
//
//    /**
//     * 清除所有数据
//     *
//     * @param context
//     */
//    public static void clear(Context context) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.clear();
//        SharedPreferencesCompat.apply(editor);
//    }
//
//    /**
//     * 查询某个key是否已经存在
//     *
//     * @param context
//     * @param key
//     * @return
//     */
//    public static boolean contains(Context context, String key) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
//                Context.MODE_PRIVATE);
//        return sp.contains(key);
//    }
//
//    /**
//     * 返回所有的键值对
//     *
//     * @param context
//     * @return
//     */
//    public static Map<String, ?> getAll(Context context) {
//        // 如果传入的context为null，试着获取app的context
//        if (context == null) {
//            context = BaseApp.getInstance();
//        }
//
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
//                Context.MODE_PRIVATE);
//        return sp.getAll();
//    }
//
//    /**
//     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
//     *
//     * @author zhy
//     */
//    private static class SharedPreferencesCompat {
//        private static final Method sApplyMethod = findApplyMethod();
//
//        /**
//         * 反射查找apply的方法
//         *
//         * @return
//         */
//        @SuppressWarnings({"unchecked", "rawtypes"})
//        private static Method findApplyMethod() {
//            try {
//                Class clz = SharedPreferences.Editor.class;
//                return clz.getMethod("apply");
//            } catch (NoSuchMethodException e) {
//            }
//
//            return null;
//        }
//
//        /**
//         * 如果找到则使用apply执行，否则使用commit
//         *
//         * @param editor
//         */
//        public static void apply(SharedPreferences.Editor editor) {
//            try {
//                if (sApplyMethod != null) {
//                    sApplyMethod.invoke(editor);
//                    return;
//                }
//            } catch (IllegalArgumentException e) {
//            } catch (IllegalAccessException e) {
//            } catch (InvocationTargetException e) {
//            }
//            editor.commit();
//        }
//    }
//
//
//
//
//
////
////    /**
////     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
////     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
////     *
////     * @param object 待加密的转换为String的对象
////     * @return String   加密后的String
////     */
////    private static String Object2String(Object object) {
////        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////        ObjectOutputStream objectOutputStream = null;
////        try {
////            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
////            objectOutputStream.writeObject(object);
////
////            String string = new String(android.util.Base64.encode(byteArrayOutputStream.toByteArray(), android.util.Base64.DEFAULT));
////            objectOutputStream.close();
////            return string;
////        } catch (IOException e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
////
////    /**
////     * 使用Base64解密String，返回Object对象
////     *
////     * @param objectString 待解密的String
////     * @return object      解密后的object
////     */
////    private static Object String2Object(String objectString) {
////        byte[] mobileBytes = android.util.Base64.decode(objectString.getBytes(), android.util.Base64.DEFAULT);
////        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
////        ObjectInputStream objectInputStream = null;
////        try {
////            objectInputStream = new ObjectInputStream(byteArrayInputStream);
////            Object object = objectInputStream.readObject();
////            objectInputStream.close();
////            return object;
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
////
////    }
//
////    /**
////     * 使用SharedPreference保存对象
////     *
////     * @param key        储存对象的key
////     * @param saveObject 储存的对象
////     */
////    public static void saveObject(Context context, String key, Object saveObject) {
////        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPreferences.edit();
////        String string = Object2String(saveObject);
////        editor.putString(key, string);
////        editor.commit();
////    }
////
////    /**
////     * 获取SharedPreference保存的对象
////     *
////     * @param key     储存对象的key
////     * @return object 返回根据key得到的对象
////     */
////    public static Object getObject(Context context, String key) {
////        SharedPreferences sharedPreferences = context .getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
////        String string = sharedPreferences.getString(key, null);
////        if (string != null) {
////            Object object = String2Object(string);
////            return object;
////        } else {
////            return null;
////        }
////    }
//
//
////    public static void saveObject(Context context, String key, Object saveObject) {
////        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPreferences.edit();
////        String string = GsonUtils.getBeanToJson(saveObject);
////        editor.putString(key, string);
////        editor.commit();
////    }
////
////    public static <T> T getObject(Context context, String key,Class<T> clazz) {
////        SharedPreferences sharedPreferences = context .getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
////        String string = sharedPreferences.getString(key, null);
////        if (string != null) {
////            LogUtils.e(string);
////            T object = GsonUtils.getBeanFromJson(string,clazz);
////            return object;
////        } else {
////            return null;
////        }
////    }
////    public static <T> List<T> getObjectList(Context context, String key) {
////        SharedPreferences sharedPreferences = context .getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
////        String string = sharedPreferences.getString(key, null);
////        if (string != null) {
////            LogUtils.e(string);
////            List<T> object = GsonUtils.getBeanFromJson(string,new TypeToken<List<T>>(){}.getType());
////            return object;
////        } else {
////            return null;
////        }
////    }
//
//
//    // 通过类名字去获取一个对象
//    public static <T> T getObject(Context context, Class<T> clazz) {
//        String key = getKey(clazz);
//        String json = getString(context, key, null);
//        if (TextUtils.isEmpty(json)) {
//            return null;
//        }
//        try {
//            Gson gson = new Gson();
//            return gson.fromJson(json, clazz);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    // 通过Type去获取一个泛型对象
//    public static <T> T getObject(Context context, Type type) {
//        return getObject(context,null,type);
//    }
//    public static <T> T getObject(Context context, String k, Type type) {
//        String key;
//        if (k!=null){
//            key=k;
//        }else{
//            key = getKey(type);
//        }
//        String json = getString(context, key, null);
//        if (TextUtils.isEmpty(json)) {
//            return null;
//        }
//        try {
//            Gson gson = new Gson();
//            return gson.fromJson(json, type);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 保存一个对象，object必须是普通类，而不是泛型，如果是泛型,请使用 {
//     * @link SpUtils#putObject(Context, Object, Type)}
//     *
//     * @param context
//     * @param object
//     */
//    public static void putObject(Context context, Object object) {
//        String key = getKey(object.getClass());
//        Gson gson = new Gson();
//        String json = gson.toJson(object);
//        putString(context, key, json);
//    }
//
//    /**
//     * 保存一个泛型对象
//     *
//     * @param context
//     * @param object
//     * @param type    如果你要保存 List<Person> 这个类, type应该 传入 new TypeToken<List<Person>>() {}.getType()
//     */
//    public static void putObject(Context context, Object object, Type type) {
//        putObject(context,null,object,type);
//    }
//
//    public static void putObject(Context context, String k, Object object, Type type) {
//        String key;
//        if (k!=null){
//            key=k;
//        }else{
//            key = getKey(type);
//        }
//        Gson gson = new Gson();
//        String json = gson.toJson(object);
//        putString(context, key, json);
//    }
//
//    public static void removeObject(Context context, Class<?> clazz) {
//        removes(context, getKey(clazz));
//    }
//
//    public static void removeObject(Context context, Type type) {
//        removes(context, getKey(type));
//    }
//
//    public static String getKey(Class<?> clazz) {
//        return clazz.getName();
//    }
//
//    public static String getKey(Type type) {
//        return type.toString();
//    }
//
//    public static void removes(Context context, String key) {
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.remove(key);
//        edit.commit();
//    }
//
//    public static void putString(Context context, String key, String value) {
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(key, value);
//        edit.commit();
//    }
//
//    public static String getString(Context context, String key, String defValue) {
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        return sp.getString(key, defValue);
//    }
//
//}
//
