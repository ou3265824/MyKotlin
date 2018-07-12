package com.olq.baseframe.base;

/**
 * Created by dell on 2017/11/14.
 */

public class BaseBean<T> {


    /**
     * result : true
     * data : 操作成功
     */

    private boolean result;
    private T data;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


//    @Override
//    public String toString() {
//        return "BaseBean{" +
//                "result=" + result +
//                ", data=" + data +
//                ", token='" + token + '\'' +
//                '}';
//    }
}
