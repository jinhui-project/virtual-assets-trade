package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class PlatformAccount {
    private Long id;

    private String accountName;

    private String accountAddr;

    private String accountType;

    private String accountDesc;

    private Date createTime;

    private BigDecimal minWithdraw;

    private BigDecimal maxWithdraw;

    private BigDecimal minRecharge;

    private BigDecimal maxRecharge;

    private BigDecimal positionVol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountAddr() {
        return accountAddr;
    }

    public void setAccountAddr(String accountAddr) {
        this.accountAddr = accountAddr;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMinWithdraw() {
        return minWithdraw;
    }

    public void setMinWithdraw(BigDecimal minWithdraw) {
        this.minWithdraw = minWithdraw;
    }

    public BigDecimal getMaxWithdraw() {
        return maxWithdraw;
    }

    public void setMaxWithdraw(BigDecimal maxWithdraw) {
        this.maxWithdraw = maxWithdraw;
    }

    public BigDecimal getMinRecharge() {
        return minRecharge;
    }

    public void setMinRecharge(BigDecimal minRecharge) {
        this.minRecharge = minRecharge;
    }

    public BigDecimal getMaxRecharge() {
        return maxRecharge;
    }

    public void setMaxRecharge(BigDecimal maxRecharge) {
        this.maxRecharge = maxRecharge;
    }

    public BigDecimal getPositionVol() {
        return positionVol;
    }

    public void setPositionVol(BigDecimal positionVol) {
        this.positionVol = positionVol;
    }

    @Override
    public String toString() {
        return "PlatformAccount{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountAddr='" + accountAddr + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountDesc='" + accountDesc + '\'' +
                ", createTime=" + createTime +
                ", minWithdraw=" + minWithdraw +
                ", maxWithdraw=" + maxWithdraw +
                ", minRecharge=" + minRecharge +
                ", maxRecharge=" + maxRecharge +
                ", positionVol=" + positionVol +
                '}';
    }
}