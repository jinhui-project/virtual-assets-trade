package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    /**
     * 表主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户资金账号
     */
    private String userAccount;

    /**
     * 用户资金账户名称
     */
    private String accountName;

    /**
     * 账户类别：0-CNY账户 1-BTC账户 2-ETH账户 3-LTC账户 4-USDT账户 5-BCH账户 6-ETC账户
     */
    private String accountType;

    /**
     * 持有份数,如果是现金账户是指持有金额
     */
    private BigDecimal positionVol;

    /**
     * 注册时间
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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户资金账号
     *
     * @return user_account - 用户资金账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户资金账号
     *
     * @param userAccount 用户资金账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * 获取用户资金账户名称
     *
     * @return account_name - 用户资金账户名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置用户资金账户名称
     *
     * @param accountName 用户资金账户名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 获取账户类别：0-CNY账户 1-BTC账户 2-ETH账户 3-LTC账户 4-USDT账户 5-BCH账户 6-ETC账户
     *
     * @return account_type - 账户类别：0-CNY账户 1-BTC账户 2-ETH账户 3-LTC账户 4-USDT账户 5-BCH账户 6-ETC账户
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 设置账户类别：0-CNY账户 1-BTC账户 2-ETH账户 3-LTC账户 4-USDT账户 5-BCH账户 6-ETC账户
     *
     * @param accountType 账户类别：0-CNY账户 1-BTC账户 2-ETH账户 3-LTC账户 4-USDT账户 5-BCH账户 6-ETC账户
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
     * 获取持有份数,如果是现金账户是指持有金额
     *
     * @return position_vol - 持有份数,如果是现金账户是指持有金额
     */
    public BigDecimal getPositionVol() {
        return positionVol;
    }

    /**
     * 设置持有份数,如果是现金账户是指持有金额
     *
     * @param positionVol 持有份数,如果是现金账户是指持有金额
     */
    public void setPositionVol(BigDecimal positionVol) {
        this.positionVol = positionVol;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}