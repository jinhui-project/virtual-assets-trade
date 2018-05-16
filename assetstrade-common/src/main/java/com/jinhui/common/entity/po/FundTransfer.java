package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class FundTransfer {
    private Long id;

    private Date transTime;

    private String serialNo;

    private String userId;

    private String userName;

    private String accountType;

    private String bussType;

    private String transferOutAccount;

    private String transferInAccount;

    private BigDecimal transNum;

    private BigDecimal transPrice;

    private BigDecimal transAmount;

    private BigDecimal chargeAmount;

    private String transStatus;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

    public String getTransferOutAccount() {
        return transferOutAccount;
    }

    public void setTransferOutAccount(String transferOutAccount) {
        this.transferOutAccount = transferOutAccount == null ? null : transferOutAccount.trim();
    }

    public String getTransferInAccount() {
        return transferInAccount;
    }

    public void setTransferInAccount(String transferInAccount) {
        this.transferInAccount = transferInAccount == null ? null : transferInAccount.trim();
    }

    public BigDecimal getTransNum() {
        return transNum;
    }

    public void setTransNum(BigDecimal transNum) {
        this.transNum = transNum;
    }

    public BigDecimal getTransPrice() {
        return transPrice;
    }

    public void setTransPrice(BigDecimal transPrice) {
        this.transPrice = transPrice;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus == null ? null : transStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FundTransfer{" +
                "id=" + id +
                ", transTime=" + transTime +
                ", serialNo='" + serialNo + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", bussType='" + bussType + '\'' +
                ", transferOutAccount='" + transferOutAccount + '\'' +
                ", transferInAccount='" + transferInAccount + '\'' +
                ", transNum=" + transNum +
                ", transPrice=" + transPrice +
                ", transAmount=" + transAmount +
                ", chargeAmount=" + chargeAmount +
                ", transStatus='" + transStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}