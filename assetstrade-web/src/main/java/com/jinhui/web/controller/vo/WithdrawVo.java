package com.jinhui.web.controller.vo;

/**
 * 提现Vo
 *
 * @autor wsc
 * @create 2018-03-27 11:13
 **/
public class WithdrawVo {

    //提现账户类别
    private String accountType;

    //转入账号
    private String transferInAccount;

    //转出账号
    //private String transferOutAccount;

    //提现金额,提现数量
    private String withdrawAmount;

    //交易密码
    private String tradePwd;


    private String smsCode;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTransferInAccount() {
        return transferInAccount;
    }

    public void setTransferInAccount(String transferInAccount) {
        this.transferInAccount = transferInAccount;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }


    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }


    @Override
    public String toString() {
        return "WithdrawVo{" +
                "accountType='" + accountType + '\'' +
                ", transferInAccount='" + transferInAccount + '\'' +
                ", withdrawAmount='" + withdrawAmount + '\'' +
                ", tradePwd='" + tradePwd + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}
