package com.igalaxy.boot.domain.dto;

import com.google.gson.annotations.Expose;

/**
 * ajax 请求的返回类型封装JSON结果
 */
public class BaseResult1<T> {

    @Expose
    private boolean success;
    @Expose
    private int code;
    @Expose
    private String msg;

    private T data;

    public BaseResult1(boolean success) {
        this(success, (String) null);
    }

    public BaseResult1(boolean success, String msg) {
        this(success, 0, msg);
    }

    public BaseResult1(boolean success, int code, String msg) {
        this(success, code, msg, null);
    }

    public BaseResult1(boolean success, T data) {
        this(success, null, data);
    }

    public BaseResult1(boolean success, String msg, T data) {
        this(success, 0, msg, data);
    }

    public BaseResult1(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
