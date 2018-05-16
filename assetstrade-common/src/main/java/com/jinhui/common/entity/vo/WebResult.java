package com.jinhui.common.entity.vo;


/**
 * Created by Administrator on 2017/9/28 0028.
 */
public class WebResult<T> {

    private String code;
    private String errorMessage;
    private T data;

    private WebResult(){

    }

    public static WebResult ok() {
        WebResult r = new WebResult();
        r.setCode("0");
        return r;
    }


    public  WebResult ok( T t) {
        WebResult r = new WebResult();
        r.setCode("0");
        r.setData(t);

        return r;
    }


    public static WebResult error() {
        return error("500", "未知异常，请联系管理员");
    }

    public static WebResult error(String msg) {
        return error("500", msg);
    }

    public static WebResult error(String code, String msg) {

        WebResult r = new WebResult();
        r.setCode(code);
        r.setErrorMessage(msg);

        return r;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebResult{" +
                "code=" + code +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
