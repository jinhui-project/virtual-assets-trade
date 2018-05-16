package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class Wallet {
    /**
     * 表主键
     */
    private Long id;

    /**
     * 钱包名称
     */
    private String walletName;

    /**
     * 钱包地址
     */
    private String address;

    /**
     * 钱包类型
     */
    private String type;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 余额单位
     */
    private String balanceUnit;

    /**
     * 换算值
     */
    private BigDecimal convertBalance;

    /**
     * 换算单位
     */
    private String convertUnit;

    /**
     * 记录时间
     */
    private Date createTime;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取钱包名称
     *
     * @return wallet_name - 钱包名称
     */
    public String getWalletName() {
        return walletName;
    }

    /**
     * 设置钱包名称
     *
     * @param walletName 钱包名称
     */
    public void setWalletName(String walletName) {
        this.walletName = walletName == null ? null : walletName.trim();
    }

    /**
     * 获取钱包地址
     *
     * @return address - 钱包地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置钱包地址
     *
     * @param address 钱包地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取钱包类型
     *
     * @return type - 钱包类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置钱包类型
     *
     * @param type 钱包类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取余额单位
     *
     * @return balance_unit - 余额单位
     */
    public String getBalanceUnit() {
        return balanceUnit;
    }

    /**
     * 设置余额单位
     *
     * @param balanceUnit 余额单位
     */
    public void setBalanceUnit(String balanceUnit) {
        this.balanceUnit = balanceUnit == null ? null : balanceUnit.trim();
    }

    /**
     * 获取换算值
     *
     * @return convert_balance - 换算值
     */
    public BigDecimal getConvertBalance() {
        return convertBalance;
    }

    /**
     * 设置换算值
     *
     * @param convertBalance 换算值
     */
    public void setConvertBalance(BigDecimal convertBalance) {
        this.convertBalance = convertBalance;
    }

    /**
     * 获取换算单位
     *
     * @return convert_unit - 换算单位
     */
    public String getConvertUnit() {
        return convertUnit;
    }

    /**
     * 设置换算单位
     *
     * @param convertUnit 换算单位
     */
    public void setConvertUnit(String convertUnit) {
        this.convertUnit = convertUnit == null ? null : convertUnit.trim();
    }

    /**
     * 获取记录时间
     *
     * @return create_time - 记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录时间
     *
     * @param createTime 记录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}