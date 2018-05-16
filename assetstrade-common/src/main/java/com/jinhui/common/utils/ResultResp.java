package com.jinhui.common.utils;

/**
 * 证联返回结果封装
 *
 * @autor wsc
 * @create 2018-01-30 15:12
 **/
public class ResultResp<T> {
    private int status;
    private String msg;
    private T data;
    private String token;

    public static <T> ResultResp<T> successData(T t) {
        ResultResp<T> result = new ResultResp<>();
        result.status = Status.SUCCESS.getStatus();
        result.msg = Status.SUCCESS.getMsg();
        result.data = t;
        return result;
    }

    public static ResultResp<Void> success(String msg) {
        ResultResp<Void> result = new ResultResp<>();
        result.status = Status.SUCCESS.getStatus();
        result.msg = msg;
        return result;
    }

    public static ResultResp<Void> success() {
        ResultResp<Void> result = new ResultResp<>();
        result.status = Status.SUCCESS.getStatus();
        result.msg = Status.SUCCESS.getMsg();
        return result;
    }

    public static ResultResp<Void> fail() {
        ResultResp<Void> result = new ResultResp<>();
        result.status = Status.FAIL.getStatus();
        result.msg = Status.FAIL.getMsg();
        return result;
    }

    public static ResultResp<Void> overdue() {
        ResultResp<Void> result = new ResultResp<>();
        result.status = Status.OVERDUE.getStatus();
        result.msg = Status.OVERDUE.getMsg();
        return result;
    }

    public static ResultResp<Void> overdue(String msg) {
        ResultResp<Void> result = new ResultResp<>();
        result.status = Status.OVERDUE.getStatus();
        result.msg = msg;
        return result;
    }

    public static ResultResp<Void> fail(String msg) {
        ResultResp<Void> result = new ResultResp<>();
        result.status = Status.FAIL.getStatus();
        result.msg = msg;
        return result;
    }

    public static <T> ResultResp<T> failData(T t) {
        ResultResp<T> result = new ResultResp<>();
        result.status = Status.FAIL.getStatus();
        result.msg = Status.FAIL.getMsg();
        result.data = t;
        return result;
    }

    public static <T> ResultResp<T> handingData(T t) {
        ResultResp<T> result = new ResultResp<>();
        result.status = Status.HANDING.getStatus();
        result.msg = Status.HANDING.getMsg();
        result.data = t;
        return result;
    }

    public static <T> ResultResp<T> exceptionData(T t) {
        ResultResp<T> result = new ResultResp<>();
        result.status = Status.EXCEPTION.getStatus();
        result.msg = t.toString();
        result.data = t;
        return result;
    }

    private ResultResp(){}

    public ResultResp(Status status){
        this.status = status.getStatus();
        this.msg = status.getMsg();
    }

    public void setError(Status status){
        this.status = status.getStatus();
        this.msg = status.getMsg();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS.getStatus();
    }
    public boolean isHanding() {
        return status == Status.HANDING.getStatus();
    }

    public boolean isException() {
        return status == Status.EXCEPTION.getStatus();
    }

    public boolean isFail() {
        return status == Status.FAIL.getStatus();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
