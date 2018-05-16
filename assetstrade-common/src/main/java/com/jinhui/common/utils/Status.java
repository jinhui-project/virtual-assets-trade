package com.jinhui.common.utils;

/**
 * 证联状态
 *
 * @autor wsc
 * @create 2018-01-30 15:12
 **/
public enum Status {

    //和前端交互的错误码
    SUCCESS(0, "SUCCESS"),
    FAIL(1, "FAIL"),
    HANDING(2, "HANDING"),
    EXCEPTION(3, "EXCEPTION"),
    OVERDUE(9, "OVERDUE"),;

    private int status;
    private String msg;

    private Status(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }
}