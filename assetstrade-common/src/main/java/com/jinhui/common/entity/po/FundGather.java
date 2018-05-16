package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class FundGather {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 账户余额
     */
    private BigDecimal balanceAmount;

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;

    /**
     * 投资金额:用户的所有数字货币对应的金额
     */
    private BigDecimal investAmount;

    /**
     * 投资当前市值:根据最新价格折算
     */
    private BigDecimal lastInvestAmount;

    /**
     * 资金总盈亏
     */
    private BigDecimal profitAndLoss;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Integer id) {
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
     * 获取账户余额
     *
     * @return balance_amount - 账户余额
     */
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * 设置账户余额
     *
     * @param balanceAmount 账户余额
     */
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    /**
     * 获取充值金额
     *
     * @return recharge_amount - 充值金额
     */
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 设置充值金额
     *
     * @param rechargeAmount 充值金额
     */
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * 获取投资金额:用户的所有数字货币对应的金额
     *
     * @return invest_amount - 投资金额:用户的所有数字货币对应的金额
     */
    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    /**
     * 设置投资金额:用户的所有数字货币对应的金额
     *
     * @param investAmount 投资金额:用户的所有数字货币对应的金额
     */
    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    /**
     * 获取投资当前市值:根据最新价格折算
     *
     * @return last_invest_amount - 投资当前市值:根据最新价格折算
     */
    public BigDecimal getLastInvestAmount() {
        return lastInvestAmount;
    }

    /**
     * 设置投资当前市值:根据最新价格折算
     *
     * @param lastInvestAmount 投资当前市值:根据最新价格折算
     */
    public void setLastInvestAmount(BigDecimal lastInvestAmount) {
        this.lastInvestAmount = lastInvestAmount;
    }

    /**
     * 获取资金总盈亏
     *
     * @return profit_and_loss - 资金总盈亏
     */
    public BigDecimal getProfitAndLoss() {
        return profitAndLoss;
    }

    /**
     * 设置资金总盈亏
     *
     * @param profitAndLoss 资金总盈亏
     */
    public void setProfitAndLoss(BigDecimal profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}