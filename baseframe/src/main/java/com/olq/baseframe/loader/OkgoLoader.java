//package com.olq.baseframe.loader;
//
//import android.app.Application;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.cache.CacheMode;
//import com.lzy.okgo.callback.FileCallback;
//import com.lzy.okgo.callback.StringCallback;
//import com.lzy.okgo.cookie.CookieJarImpl;
//import com.lzy.okgo.cookie.store.MemoryCookieStore;
//import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
//import com.lzy.okgo.model.HttpHeaders;
//import com.lzy.okgo.model.HttpParams;
//import com.lzy.okgo.model.Progress;
//import com.lzy.okgo.model.Response;
//import com.lzy.okgo.request.PostRequest;
//import com.olq.baseframe.base.BaseApp;
//import com.olq.baseframe.base.BaseBean;
//import com.olq.baseframe.base.BaseConfig;
//import com.olq.baseframe.callback.DisposeCallBack;
//import com.olq.baseframe.callback.FileCallBack;
//import com.olq.baseframe.callback.GsonCallBack;
//import com.olq.baseframe.callback.StringCallBack;
//import com.olq.baseframe.event.LoginEvent;
//import com.olq.baseframe.utils.GsonUtils;
//import com.olq.baseframe.utils.LogUtils;
//import com.olq.baseframe.utils.SharePrefUtil;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;
//
//import okhttp3.OkHttpClient;
//
///**
// * Created by Administrator on 2017/6/19.
// */
//
//public class OkgoLoader {
//    private static OkgoLoader mOkgoUtils;
//    private final DisposeCallBack disposeCallBack;
//    private final HttpHeaders httpHeaders;
//    private final OkHttpClient.Builder builder;
//    private final OkHttpLogInterceptor loggingInterceptor;
////    private final HttpLoggingInterceptor loggingInterceptor;
//    private static final long TIME = 30000;      //默认的超时时间
//    private final HttpParams params;
//
//    private OkgoLoader() {
//        disposeCallBack = new DisposeCallBack();
//        httpHeaders = new HttpHeaders();
//        params = new HttpParams();
//        builder = new OkHttpClient.Builder();
////        loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//        loggingInterceptor = new OkHttpLogInterceptor("OkGo");
//
//    }
//
//    public static OkgoLoader getInstance() {
//        if (mOkgoUtils == null) {
//            synchronized (OkgoLoader.class) {
//                if (mOkgoUtils == null) {
//                    mOkgoUtils = new OkgoLoader();
//                }
//            }
//        }
//        return mOkgoUtils;
//    }
//
//    public void init(Application app) {
//        getConfiguration();
//        setHttpHeaders();
//        //6. 配置OkGo
//        OkGo.getInstance().init(app)                       //必须调用初始化
//                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
//                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
////                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
//                .setCacheTime(TIME)
//                .setRetryCount(0)       ;                      //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
////                .addCommonHeaders(httpHeaders);                      //全局公共头
////                .addCommonParams(params);                       //全局公共参数
//    }
//
//
//
//    private void getConfiguration() {
//
//        //2. 配置log
//        //log打印级别，决定了log显示的详细程度
//        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//        //log颜色级别，决定了log在控制台显示的颜色
//        loggingInterceptor.setColorLevel(Level.WARNING);
//        builder.addInterceptor(loggingInterceptor);
//        //第三方的开源库，使用通知显示当前请求的log
////        builder.addInterceptor(new ChuckInterceptor(this));
//
//        //3. 配置超时时间
//        //全局的读取超时时间 OkGo.DEFAULT_MILLISECONDS
//        builder.readTimeout(TIME, TimeUnit.MILLISECONDS);
//        //全局的写入超时时间
//        builder.writeTimeout(TIME, TimeUnit.MILLISECONDS);
//        //全局的连接超时时间
//        builder.connectTimeout(TIME, TimeUnit.MILLISECONDS);
//
//        //4. 配置Cookie，以下几种任选其一就行
//        //使用sp保持cookie，如果cookie不过期，则一直有效
////        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
////        //使用数据库保持cookie，如果cookie不过期，则一直有效
////        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
//        //使用内存保持cookie，app退出后，cookie消失
//        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
//
//        //5. Https配置，以下几种方案根据需要自己设置
//        //方法一：信任所有证书,不安全有风险
////        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
////        //方法二：自定义信任规则，校验服务端证书
////        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
////        //方法三：使用预埋证书，校验服务端证书（自签名证书）
////        HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
////        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
////        HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
////        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
////        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
////        builder.hostnameVerifier(new SafeHostnameVerifier());
//
//    }
//
//
//    /**
//     * 设置全局公共头
//     */
//    private void setHttpHeaders() {
//
//    }
//
////    public HttpHeaders getCloudHttpHeaders() {
////        HttpHeaders headers = new HttpHeaders();
////        return headers;
////    }
////
////
////    /**
////     * 基本网络请求
////     *
////     * @param url
////     */
//    public void sendByGet(String url, final GsonCallBack<?> callBack) {
//        OkGo.<String>get(url)     // 请求方式和请求url
//                .tag(url)                       // 请求的 tag, 主要用于取消对应的请求
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        // s 即为所需要的结果
//                        Log.i("TEST", response.body());
//                        disposeCallBack.onSuccess(callBack,response);
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                    }
//                });
//    }
////
////
//    public void sendByGet(String url, final StringCallBack callBack) {
//        OkGo.<String>get(url)     // 请求方式和请求url
//                .tag(url)                       // 请求的 tag, 主要用于取消对应的请求
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        LogUtils.i(response.body());
//                        disposeCallBack.onSuccess(callBack, response);
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        Log.i("TEST", response.code() + ""+response.message()+"--"+response.body());
////                        disposeCallBack.onError(callBack,response);
//                    }
//
//                    @Override
//                    public void onCacheSuccess(Response<String> response) {
//                        Log.i("TEST", response.code() + ""+response.message()+"--"+response.body());
//                    }
//
//                });
//    }
////
////
////
////
////    public void sendByPut(String url,  final StringCallBack callBack) {
////        OkGo.<String>put(url)     // 请求方式和请求url
////                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
////                .execute(new StringCallback() {
////                    @Override
////                    public void onSuccess(Response<String> response) {
////                        LogUtils.i(response.body());
////                        disposeCallBack.onSuccess(callBack, response);
////                    }
////
////                    @Override
////                    public void onError(Response<String> response) {
////                        Log.i("TEST", response.code() + ""+response.message()+"--"+response.body());
//////                        disposeCallBack.onError(callBack,response);
////                    }
////
////                    @Override
////                    public void onCacheSuccess(Response<String> response) {
////                        Log.i("TEST", response.code() + ""+response.message()+"--"+response.body());
////                    }
////
////                });
////    }
////
////
////    /**
////     * 请求 Bitmap 对象
////     *
////     * @param url
////     */
////    public void sendByBitmap(String url, final BitmapCallBack callBack) {
////        OkGo.<Bitmap>get(url)//
////                .tag(this)//
////                .execute(new BitmapCallback() {
////                    @Override
////                    public void onSuccess(Response<Bitmap> response) {
////                        // bitmap 即为返回的图片数据
////                        Bitmap bitmap=response.body();
////                        disposeCallBack.onSuccess(callBack,bitmap,response);
////                    }
////                });
////    }
////
////
////    /**
////     * 请求 文件下载
////     *
////     * @param url FileCallback()：空参构造
////     *            FileCallback(String destFileName)：可以额外指定文件下载完成后的文件名
////     *            FileCallback(String destFileDir, String destFileName)：可以额外指定文件的下载目录和下载
////     */
//    public void sendByFile(String url, String fileDir, String fileName, final FileCallBack callBack) {
//        OkGo.<File>get(url)//
//                .tag(this)//
//                .execute(new FileCallback(fileDir,fileName) {
//                    @Override
//                    public void onSuccess(Response<File> response) {
//                        File file=response.body();
//                        disposeCallBack.onSuccess(callBack,file,response);
//                    }
//                });
//    }
//
//
//    /**
//     * 普通Post，直接上传String类型的文本
//     *
//     * @param url
//     */
////    public void sendByUploadingString(String url, String upString,String type ,final HttpCallBack callBack) {
////        OkGo.<String>post(url)//
////                .tag(this)//
////                .upString(upString, MediaType.parse(type))//指定请求头类型
////
//////  .params("param1", "paramValue1")//  这里不要使用params，upString 与 params 是互斥的，只有 upString 的数据会被上传
//////  .upString("这是要上传的长文本数据！", MediaType.parse("application/xml")) // 比如上传xml数据，这里就可以自己指定请求头
////                .execute(new StringCallback() {
////                    @Override
////                    public void onSuccess(Response<String> response) {
////                        String s=response.body();
////                        //上传成功
////                                 disposeCallBack.onSuccess(callBack,response);
////                        if (!TextUtils.isEmpty(s)){
////                            try {
////                                JSONObject object = new JSONObject(s);
////                                if (object.getBoolean("result")){
//////                                    LogUtils.e("onSuccess:"+s);
////                                    disposeCallBack.onSuccess(callBack,response);
////                                }else{
////                                    LogUtils.i("onUnusual:"+s);
////                                    if (object.getString("data").equals("请先登录")){
////                                        SharePrefUtil.clear(BaseApp.getInstance());
////                                        LoginEvent loginEvent=new LoginEvent();
////                                        loginEvent.setSendName(this.getClass().getName());
////                                        loginEvent.setReceiveName(BaseConfig.LOGIN_OUT);
////                                        EventBus.getDefault().post(loginEvent);
////                                    }else{
////                                        disposeCallBack.onUnusual(callBack,s);
////                                    }
////
////                                }
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
////
////                        }else{
////                            disposeCallBack.onUnusual(callBack,s);
////                        }
////                    }
////
////                    @Override
////                    public void downloadProgress(Progress progress) {
////                        super.downloadProgress(progress);
////                    }
////                });
////    }
//
//    /**
//     * 普通Post，直接上传Json类型的文本
//     *
//     * @param url
//     */
//    public void sendByPost(final View view, String url, Object object, HttpHeaders httpHeaders, final GsonCallBack<?> callBack) {
//        Log.e("test", GsonUtils.getBeanToJson(object));
//        OkGo.<String>post(url)//
//                .tag(url)//
//                .headers(httpHeaders)
//                .params("jsonText",GsonUtils.getBeanToJson(object))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String s=response.body();
//                        //上传成功
//                            LogUtils.e("GsonCallBack:"+s);
//                            if (!TextUtils.isEmpty(s)){
//                                BaseBean baseBean=GsonUtils.getBeanFromJson(s, BaseBean.class);
//                                if (baseBean.isResult()){
//                                    String token=baseBean.getToken();
//                                    if (token!=null){
//                                        SharePrefUtil.put(BaseApp.getInstance(), BaseConfig.SP_TOKEN, token);
//                                    }
//                                    disposeCallBack.onSuccess(callBack,response);
//                                }else{
//                                    LogUtils.i("onUnusual:"+s);
//                                    if (baseBean.getData().equals("请先登录")){
//                                        LoginEvent loginEvent=new LoginEvent();
//                                        loginEvent.setSendName(OkgoLoader.class.getName());
//                                        LogUtils.e("fasong",OkgoLoader.class.getName());
//                                        loginEvent.setReceiveName(BaseConfig.LOGIN_OUT);
//                                        EventBus.getDefault().post(loginEvent);
//                                    }else{
//                                        disposeCallBack.onUnusual(callBack, (String) baseBean.getData());
//                                    }
//                                }
//                            }else{
////                                disposeCallBack.onUnusual(callBack,s);
//                            }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        LogUtils.e("onError:"+response.body()+"----"+response.getException()+"---"+response.message());
//                        disposeCallBack.onError(callBack,response);
//                    }
//                });
//    }
//
//    public void sendByPost(final View view, String url, Object object, HttpHeaders httpHeaders, final StringCallBack callBack) {
//        Log.e("test", GsonUtils.getBeanToJson(object));
//        OkGo.<String>post(url)//
//                .tag(url)//
//                .headers(httpHeaders)
//                .params("jsonText",GsonUtils.getBeanToJson(object))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String s=response.body();
//                        //上传成功
////                        try {
//                        LogUtils.e("StringCallBack:"+s);
//                        if (!TextUtils.isEmpty(s)){
//                            if (s.contains("result")){
//                                BaseBean baseBean=GsonUtils.getBeanFromJson(s, BaseBean.class);
//                                if (baseBean.isResult()){
//                                    String token=baseBean.getToken();
//                                    if (token!=null){
//                                        SharePrefUtil.put(BaseApp.getInstance(), BaseConfig.SP_TOKEN, token);
//                                    }
//                                    disposeCallBack.onSuccess(callBack,response);
//                                }else{
//                                    LogUtils.i("onUnusual:"+s);
//                                    if (baseBean.getData().equals("请先登录")){
//                                        LoginEvent loginEvent=new LoginEvent();
//                                        loginEvent.setSendName(OkgoLoader.class.getName());
//                                        loginEvent.setReceiveName(BaseConfig.LOGIN_OUT);
//                                        EventBus.getDefault().post(loginEvent);
//                                    }else{
////                                        ToastUtil.showShort(baseBean.getData()+"");
//                                        disposeCallBack.onUnusual(callBack, (String) baseBean.getData());
//                                    }
//                                }
//                            }
//
//                        }else{
////                            disposeCallBack.onUnusual(callBack,s);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        LogUtils.e("onError:"+response.body()+"----"+response.getException()+"---"+response.message());
//                        disposeCallBack.onError(callBack,response);
//                    }
//                });
//    }
//
//
//    public void sendByPostUploadingFile(String url, HttpHeaders httpHeaders, Map<String,String> map, List<File> list, final StringCallBack callBack){
//       final PostRequest<String> request= OkGo.<String>post(url).tag(this)
//               .headers(httpHeaders)
//               .isMultipart(true)
//               .params(map);
//                //.isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
//                //.params("param1", "paramValue1")        // 这里可以上传参数
//                //.params("file1", new File("filepath1"))   // 可以添加文件上传
//                //.params("file2", new File("filepath2"))     // 支持多文件同时添加上传
////                .addFileParams(keyName, files)    // 这里支持一个key传多个文件
//        for (int i = 0; i < list.size(); i++) {
//            request.params("file_"+i,list.get(i));
//        }
//                request.execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        LogUtils.e(response.body());
//                        callBack.onSuccess(response.body());
//                    }
//
//                    @Override
//                    public void uploadProgress(Progress progress) {
//                        super.uploadProgress(progress);
//
//                    }
//                });
//    }
////
////
////
////    /**
////     * https请求(证书可以在全局初始化的时候设置,不用每次请求设置一遍)
////     *
////     * @param url
////     */
////    public void getHttps(String url) {
////        OkGo.get("https://kyfw.12306.cn/otn")//
////                .tag(this)//
////                .headers("Connection", "close")           //如果对于部分自签名的https访问不成功，需要加上该控制头
////                .headers("header1", "headerValue1")//
////                .params("param1", "paramValue1")//
//////      .setCertificates()                             //方法一：信任所有证书
//////      .setCertificates(getAssets().open("srca.cer")) //方法二：也可以设置https证书
////        //方法三：传入bks证书,密码,和cer证书,支持双向加密
//////      .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))
////
//////                .execute(new HttpsCallBack(this))
////        ;
////    }
////
////
//    public void cancelTag(String url) {
//        //根据 Tag 取消请求
//        OkGo.getInstance().cancelTag(url);
//    }
//
//
//
//
//
//
//
//
//
//}
//
