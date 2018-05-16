package com.jinhui.api.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class RegularTrade {
    /**
     * 表主键
     */
    private Long id;

    /**
     * 交易时间
     */
    private Date transTime;

    /**
     * 交易流水号
     */
    private String serialNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户资金账户ID
     */
    private String fundAccount;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 交易数量
     */
    private BigDecimal transNum;

    /**
     * 手续费,手续费按成交后获得的数字货币资产结算
     */
    private BigDecimal chargeAmount;

    /**
     *  交易单位
     */
    private String transUnit;

    /**
     *  业务类型 :00-买入
     */
    private String bussType;

    /**
     * 产品起息日
     */
    private Date rateDate;

    /**
     * 产品到期日
     */
    private Date termDate;

    /**
     *  预计收益
     */
    private BigDecimal expectIncome;

    /**
     * 交易状态:0-处理中,1-成功,2-失败
     */
    private String transStatus;

    /**
     * 操作人名称
     */
    private String operateUserName;

    /**
     * 操作人用户id
     */
    private String operateUserId;

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
     * 获取交易时间
     *
     * @return trans_time - 交易时间
     */
    public Date getTransTime() {
        return transTime;
    }

    /**
     * 设置交易时间
     *
     * @param transTime 交易时间
     */
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    /**
     * 获取交易流水号
     *
     * @return serial_no - 交易流水号
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 设置交易流水号
     *
     * @param serialNo 交易流水号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
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
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户资金账户ID
     *
     * @return fund_account - 用户资金账户ID
     */
    public String getFundAccount() {
        return fundAccount;
    }

    /**
     * 设置用户资金账户ID
     *
     * @param fundAccount 用户资金账户ID
     */
    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount == null ? null : fundAccount.trim();
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取交易数量
     *
     * @return trans_num - 交易数量
     */
    public BigDecimal getTransNum() {
        return transNum;
    }

    /**
     * 设置交易数量
     *
     * @param transNum 交易数量
     */
    public void setTransNum(BigDecimal transNum) {
        this.transNum = transNum;
    }

    /**
     * 获取手续费,手续费按成交后获得的数字货币资产结算
     *
     * @return charge_amount - 手续费,手续费按成交后获得的数字货币资产结算
     */
    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    /**
     * 设置手续费,手续费按成交后获得的数字货币资产结算
     *
     * @param chargeAmount 手续费,手续费按成交后获得的数字货币资产结算
     */
    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    /**
     * 获取 交易单位
     *
     * @return trans_unit -  交易单位
     */
    public String getTransUnit() {
        return transUnit;
    }

    /**
     * 设置 交易单位
     *
     * @param transUnit  交易单位
     */
    public void setTransUnit(String transUnit) {
        this.transUnit = transUnit == null ? null : transUnit.trim();
    }

    /**
     * 获取 业务类型 :00-买入
     *
     * @return buss_type -  业务类型 :00-买入
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * 设置 业务类型 :00-买入
     *
     * @param bussType  业务类型 :00-买入
     */
    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

    /**
     * 获取产品起息日
     *
     * @return rate_date - 产品起息日
     */
    public Date getRateDate() {
        return rateDate;
    }

    /**
     * 设置产品起息日
     *
     * @param rateDate 产品起息日
     */
    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    /**
     * 获取产品到期日
     *
     * @return term_date - 产品到期日
     */
    public Date getTermDate() {
        return termDate;
    }

    /**
     * 设置产品到期日
     *
     * @param termDate 产品到期日
     */
    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    /**
     * 获取 预计收益
     *
     * @return expect_income -  预计收益
     */
    public BigDecimal getExpectIncome() {
        return expectIncome;
    }

    /**
     * 设置 预计收益
     *
     * @param expectIncome  预计收益
     */
    public void setExpectIncome(BigDecimal expectIncome) {
        this.expectIncome = expectIncome;
    }

    /**
     * 获取交易状态:0-处理中,1-成功,2-失败
     *
     * @return trans_status - 交易状态:0-处理中,1-成功,2-失败
     */
    public String getTransStatus() {
        return transStatus;
    }

    /**
     * 设置交易状态:0-处理中,1-成功,2-失败
     *
     * @param transStatus 交易状态:0-处理中,1-成功,2-失败
     */
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus == null ? null : transStatus.trim();
    }

    /**
     * 获取操作人名称
     *
     * @return operate_user_name - 操作人名称
     */
    public String getOperateUserName() {
        return operateUserName;
    }

    /**
     * 设置操作人名称
     *
     * @param operateUserName 操作人名称
     */
    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName == null ? null : operateUserName.trim();
    }

    /**
     * 获取操作人用户id
     *
     * @return operate_user_id - 操作人用户id
     */
    public String getOperateUserId() {
        return operateUserId;
    }

    /**
     * 设置操作人用户id
     *
     * @param operateUserId 操作人用户id
     */
    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId == null ? null : operateUserId.trim();
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