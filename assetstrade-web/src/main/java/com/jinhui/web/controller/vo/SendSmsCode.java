package com.jinhui.web.controller.vo;

/**
 * 发送短信验证码vo
 *
 * @autor wsc
 * @create 2018-03-23 15:25
 **/
public class SendSmsCode {

    private String mobileNo;

    private String imageCode;

    private String t;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "SendSmsCode{" +
                "mobileNo='" + mobileNo + '\'' +
                ", imageCode='" + imageCode + '\'' +
                ", t='" + t + '\'' +
                '}';
    }
}
