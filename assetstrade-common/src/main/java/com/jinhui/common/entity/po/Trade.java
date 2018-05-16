package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class Trade {
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
     * 用户姓名
     */
    private String userName;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 00-买入数字货币,01-卖出数字货币
     */
    private String bussType;

    /**
     * 交易数量
     */
    private BigDecimal transNum;

    /**
     * 计算价格单位:0-CNY,1-BTC
     */
    private String priceUnit;

    /**
     * 交易价格
     */
    private BigDecimal transPrice;

    /**
     * 交易金额:交易价格*数量，不包括交易费用
     */
    private BigDecimal transAmount;

    /**
     * 成交资产单位
     */
    private String doneUnit;

    /**
     * 成交资产数:记录交易成就的资产总数，包括现金和数字货币
     */
    private BigDecimal doneAmount;

    /**
     * 手续费,手续费按成交后获得的数字货币资产结算
     */
    private BigDecimal chargeAmount;

    /**
     * 交易状态:0-处理中,1-成功,2-失败
     */
    private String transStatus;

    /**
     * 操作人姓名
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
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * 获取产品类型
     *
     * @return product_type - 产品类型
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置产品类型
     *
     * @param productType 产品类型
     */
    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    /**
     * 获取00-买入数字货币,01-卖出数字货币
     *
     * @return buss_type - 00-买入数字货币,01-卖出数字货币
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * 设置00-买入数字货币,01-卖出数字货币
     *
     * @param bussType 00-买入数字货币,01-卖出数字货币
     */
    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
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
     * 获取计算价格单位:0-CNY,1-BTC
     *
     * @return price_unit - 计算价格单位:0-CNY,1-BTC
     */
    public String getPriceUnit() {
        return priceUnit;
    }

    /**
     * 设置计算价格单位:0-CNY,1-BTC
     *
     * @param priceUnit 计算价格单位:0-CNY,1-BTC
     */
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    /**
     * 获取交易价格
     *
     * @return trans_price - 交易价格
     */
    public BigDecimal getTransPrice() {
        return transPrice;
    }

    /**
     * 设置交易价格
     *
     * @param transPrice 交易价格
     */
    public void setTransPrice(BigDecimal transPrice) {
        this.transPrice = transPrice;
    }

    /**
     * 获取交易金额:交易价格*数量，不包括交易费用
     *
     * @return trans_amount - 交易金额:交易价格*数量，不包括交易费用
     */
    public BigDecimal getTransAmount() {
        return transAmount;
    }

    /**
     * 设置交易金额:交易价格*数量，不包括交易费用
     *
     * @param transAmount 交易金额:交易价格*数量，不包括交易费用
     */
    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    /**
     * 获取成交资产单位
     *
     * @return done_unit - 成交资产单位
     */
    public String getDoneUnit() {
        return doneUnit;
    }

    /**
     * 设置成交资产单位
     *
     * @param doneUnit 成交资产单位
     */
    public void setDoneUnit(String doneUnit) {
        this.doneUnit = doneUnit == null ? null : doneUnit.trim();
    }

    /**
     * 获取成交资产数:记录交易成就的资产总数，包括现金和数字货币
     *
     * @return done_amount - 成交资产数:记录交易成就的资产总数，包括现金和数字货币
     */
    public BigDecimal getDoneAmount() {
        return doneAmount;
    }

    /**
     * 设置成交资产数:记录交易成就的资产总数，包括现金和数字货币
     *
     * @param doneAmount 成交资产数:记录交易成就的资产总数，包括现金和数字货币
     */
    public void setDoneAmount(BigDecimal doneAmount) {
        this.doneAmount = doneAmount;
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
     * 获取操作人姓名
     *
     * @return operate_user_name - 操作人姓名
     */
    public String getOperateUserName() {
        return operateUserName;
    }

    /**
     * 设置操作人姓名
     *
     * @param operateUserName 操作人姓名
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