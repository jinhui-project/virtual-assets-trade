package com.jinhui.web.controller.vo;

/**
 * 提现手续费
 *
 * @autor wsc
 * @create 2018-03-28 16:08
 **/
public class WithdrawFeeVo {
    //提现账户类别
    private String accountType;

    //提现金额,提现数量
    private String withdrawAmount;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }


    @Override
    public String toString() {
        return "WithdrawFeeVo{" +
                "accountType='" + accountType + '\'' +
                ", withdrawAmount='" + withdrawAmount + '\'' +
                '}';
    }
}
