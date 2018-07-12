package com.olq.baseframe.loader

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.DBCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.olq.baseframe.loader.call.DisposeCallBack
import com.olq.baseframe.loader.call.GsonCallBack
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level


object OkgoLoader {

    val APPID_KEY="X-Bmob-Application-Id"
    val APPID_VALUE="d0f1505957f1ade2ea27d30ee603d138"

    val API_KEY="X-Bmob-REST-API-Key"
    val API_VALUE="c1f62f2ea289dccb57bf36633cd6547a"

    val TYPE_KEY="Content-Type"
    val TYPE_VALUE="application/json"


    lateinit var context: Application
    val disposeCallBack=DisposeCallBack()

    fun init(app: Application) {
        this.context = app
        val builder = OkHttpClient.Builder();
        getHttpLog(builder)
        getTimeout(builder, app)
        OkGo.getInstance().init(app)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);
    }

    fun getHttpLog(builder: OkHttpClient.Builder) {
        val loggingInterceptor = HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //非必要情况，不建议使用，第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
//        builder.addInterceptor(ChuckInterceptor(this));
    }

    fun getTimeout(builder: OkHttpClient.Builder, app: Application) {
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar( CookieJarImpl( SPCookieStore(app)));
//使用数据库保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(CookieJarImpl(DBCookieStore(app)));
//使用内存保持cookie，app退出后，cookie消失
//        builder.cookieJar( CookieJarImpl( MemoryCookieStore()));

//        //方法一：信任所有证书,不安全有风险
//        val sslParams1 = HttpsUtils.getSslSocketFactory();
////方法二：自定义信任规则，校验服务端证书
//        val sslParams2 = HttpsUtils.getSslSocketFactory(SafeTrustManager());
////方法三：使用预埋证书，校验服务端证书（自签名证书）
//        val sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
////方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//        val sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
//        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
////配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//        builder.hostnameVerifier( SafeHostnameVerifier());

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        val headers = HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        val params =  HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
    }


    fun sendByGet( url: String, headers: HttpHeaders?, params: HttpParams?,clazz: Class<*>, callBack: GsonCallBack<*>) {
        val h= HttpHeaders()
        h.put(APPID_KEY , APPID_VALUE)
        h.put( API_KEY , API_VALUE)
        h.put(TYPE_KEY , TYPE_VALUE)
//        headers=h;
        OkGo.get<String>(url).tag(url)
                .headers(h)
//                .params(params)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>?) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            disposeCallBack.onSucceed(clazz,callBack,response)
                    }

                    override fun onError(response: Response<String>?) {
                        super.onError(response)
                        disposeCallBack.onError(callBack)
                    }

                 })
    }

    fun sendByPost(url: String,headers: HttpHeaders, params: HttpParams,clazz: Class<*>,callBack: GsonCallBack<*>){
        OkGo.post<String>(url).tag(url)
                .headers(headers)
                .params(params)
                .execute(object : StringCallback(){
                    override fun onSuccess(response: Response<String>?) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        disposeCallBack.onSucceed(clazz,callBack,response)
                    }

                })
    }
    fun sendByPost(url: String, params: HttpParams,callBack: StringCallback){
        OkGo.post<String>(url).tag(url)
//                .headers(headers)
                .params(params)
                .execute(callBack)
    }



}