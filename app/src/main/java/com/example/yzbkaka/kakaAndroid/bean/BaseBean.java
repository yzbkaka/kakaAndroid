package com.example.yzbkaka.kakaAndroid.bean;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class BaseBean<T> {

    /**
     * 服务器返回错误代码
     */
    public int errorCode;

    /**
     * 错误信息
     */
    public String errorMsg;

    /**
     * 具体数据
     */
    public T data;


    public BaseBean(int errorCode, String errorMsg, T data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }
}