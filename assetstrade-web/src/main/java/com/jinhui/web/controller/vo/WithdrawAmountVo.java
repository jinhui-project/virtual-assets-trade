package com.jinhui.web.controller.vo;

/**
 * 提现手续费
 *
 * @autor wsc
 * @create 2018-03-28 16:08
 **/
public class WithdrawAmountVo {
    //提现账户类别
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "WithdrawAmountVo{" +
                "accountType='" + accountType + '\'' +
                '}';
    }
}
