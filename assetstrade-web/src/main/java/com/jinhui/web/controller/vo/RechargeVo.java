package com.jinhui.web.controller.vo;

/**
 * 充值Vo
 *
 * @autor wsc
 * @create 2018-03-27 11:13
 **/
public class RechargeVo {

    //充值币种类别
    private String accountType;

    //转入账号
 //   private String transferInAccount;

    //转出账号
    private String transferOutAccount;

    //充值金额,充值数量
    private String rechargeAmount;

    //交易密码
    private String tradePwd;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

   /*  public String getTransferInAccount() {
        return transferInAccount;
    }

    public void setTransferInAccount(String transferInAccount) {
        this.transferInAccount = transferInAccount;
    }*/

    public String getTransferOutAccount() {
        return transferOutAccount;
    }

    public void setTransferOutAccount(String transferOutAccount) {
        this.transferOutAccount = transferOutAccount;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }


    @Override
    public String toString() {
        return "RechargeVo{" +
                "accountType='" + accountType + '\'' +
                ", transferOutAccount='" + transferOutAccount + '\'' +
                ", rechargeAmount='" + rechargeAmount + '\'' +
                ", tradePwd='" + tradePwd + '\'' +
                '}';
    }
}
