package com.olq.baseframe.base;

/**
 * Created by dell on 2017/11/17.
 */

public interface BaseConfig {

    /**
     *  上线需要修改 url正式地址，
     *  必须修改环信key正式库
     *  必须修改环信key正式库
     *  必须修改环信key正式库
     *  url地址
     */

//    String url="http://192.168.1.186:8080/td/";//杨新
//    String url="http://www.wtrade365.com/sctest/";//正式测试，测试视频点播
//    String url="http://192.168.1.221:6080/sc/";//开发
    String url="http://192.168.1.212:7080/sc/";//测试
//    String url="http://www.wtrade365.com/sc/";//正式
//    String urlPay="http://www.wtrade365.com:7080/sctest/";//支付接口
    String urlPay="http://192.168.1.212:7080/sc/";//测试支付接口

//    String ENDPOINT = "https://oss-cn-shanghai.aliyuncs.com";
//    String BUCKET = "sctestbucket";

    /**
     * bugly的key
     */
//    String bugly="e423995e12";//test
    String bugly="f1f6d8254e";//测试
//    String bugly="17e40aa7c9";//正式

    /**
     * 打印log
     */
    boolean isDebug=true;

    /**
     * 支付宝支付
     */
    String PAY=urlPay+"pay/request/_ckt";



    String TYPE_JOIN="join";
    String SP_FIRST_LOGIN="firstLogin";
    String SP_MOBILE="mobile";
    String LOGIN_OUT="LoginOut";
    String SP_TOKEN="token";
    String SP_USER="user";
    String SP_EASE_NAME="EaseName";
    String SP_EASE_PASS="EasePass";

    String SP_USER_NAME="AppUserName";
    String SP_USER_IMG="UserImageUrl";
    String SP_CIRCLR_IMG="CircleImageUrl";
    String SP_CIRCLR_NAME="CircleName";
    String SP_GROUP_ID="GroupId";

    String SP_EASE_GROUP_ID="EaseGroupId";





    /**
     * 审核活动出现异常
     */
    String EXCEPTION=url+"operate/group/joinorquit/exception/";


}
