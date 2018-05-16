package com.jinhui.web.controller.vo;

/**
 * @autor wsc
 * @create 2018-04-03 18:20
 **/
public class ResultVo {

    private String token;

    private boolean isImprove;

    private String attorneyFlag;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isImprove() {
        return isImprove;
    }

    public void setImprove(boolean improve) {
        isImprove = improve;
    }

    public String getAttorneyFlag() {
        return attorneyFlag;
    }

    public void setAttorneyFlag(String attorneyFlag) {
        this.attorneyFlag = attorneyFlag;
    }
}
