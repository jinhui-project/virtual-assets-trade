package com.jinhui.web.controller.vo;

/**
 * @autor wsc
 * @create 2018-03-23 15:51
 **/
public class UserRegist {

    private String mobileNo;

    private String smsCode;

    private String loginPwd;

    private String confirmLoginPwd;

    private String t;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getConfirmLoginPwd() {
        return confirmLoginPwd;
    }

    public void setConfirmLoginPwd(String confirmLoginPwd) {
        this.confirmLoginPwd = confirmLoginPwd;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "UserRegist{" +
                "mobileNo='" + mobileNo + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", confirmLoginPwd='" + confirmLoginPwd + '\'' +
                ", t='" + t + '\'' +
                '}';
    }
}
