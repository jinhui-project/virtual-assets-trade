package com.jinhui.api.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class RegularProduct {
    /**
     * 表主键
     */
    private Long id;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品大类:01-CNY类交易对,02-USDT类交易对,03-BTC类交易对,04-ETH类交易对,05-固收类
     */
    private String productClass;

    /**
     * 5001-BTC定期类,5002-ETH定期类,5003-USDT定期类
     */
    private String productType;

    /**
     * 产品期限
     */
    private Integer productExpdate;

    /**
     * 结算货币
     */
    private String payUnit;

    /**
     * 期限单位：0-日，1-月，2-年
     */
    private String expdateUnit;

    /**
     * 产品收益率
     */
    private BigDecimal incomeRate;

    /**
     * 最小起购
     */
    private BigDecimal subsStartAmount;

    /**
     * 增减幅度
     */
    private BigDecimal subsRange;

    /**
     * 收益计算类型:1-固定收益
     */
    private String calIncomeWay;

    /**
     * 产品起息日
     */
    private Date rateDate;

    /**
     * 产品到期日
     */
    private Date termDate;

    /**
     * 风险级别：1-低，2-较低，3-中，4-较高，5-高
     */
    private String riskLevel;

    /**
     * 付息方式:0-到期一次性还本付息
     */
    private String interestType;

    /**
     * 创建时间
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
     * 获取产品大类:01-CNY类交易对,02-USDT类交易对,03-BTC类交易对,04-ETH类交易对,05-固收类
     *
     * @return product_class - 产品大类:01-CNY类交易对,02-USDT类交易对,03-BTC类交易对,04-ETH类交易对,05-固收类
     */
    public String getProductClass() {
        return productClass;
    }

    /**
     * 设置产品大类:01-CNY类交易对,02-USDT类交易对,03-BTC类交易对,04-ETH类交易对,05-固收类
     *
     * @param productClass 产品大类:01-CNY类交易对,02-USDT类交易对,03-BTC类交易对,04-ETH类交易对,05-固收类
     */
    public void setProductClass(String productClass) {
        this.productClass = productClass == null ? null : productClass.trim();
    }

    /**
     * 获取5001-BTC定期类,5002-ETH定期类,5003-USDT定期类
     *
     * @return product_type - 5001-BTC定期类,5002-ETH定期类,5003-USDT定期类
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置5001-BTC定期类,5002-ETH定期类,5003-USDT定期类
     *
     * @param productType 5001-BTC定期类,5002-ETH定期类,5003-USDT定期类
     */
    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    /**
     * 获取产品期限
     *
     * @return product_expdate - 产品期限
     */
    public Integer getProductExpdate() {
        return productExpdate;
    }

    /**
     * 设置产品期限
     *
     * @param productExpdate 产品期限
     */
    public void setProductExpdate(Integer productExpdate) {
        this.productExpdate = productExpdate;
    }

    /**
     * 获取结算货币
     *
     * @return pay_unit - 结算货币
     */
    public String getPayUnit() {
        return payUnit;
    }

    /**
     * 设置结算货币
     *
     * @param payUnit 结算货币
     */
    public void setPayUnit(String payUnit) {
        this.payUnit = payUnit == null ? null : payUnit.trim();
    }

    /**
     * 获取期限单位：0-日，1-月，2-年
     *
     * @return expdate_unit - 期限单位：0-日，1-月，2-年
     */
    public String getExpdateUnit() {
        return expdateUnit;
    }

    /**
     * 设置期限单位：0-日，1-月，2-年
     *
     * @param expdateUnit 期限单位：0-日，1-月，2-年
     */
    public void setExpdateUnit(String expdateUnit) {
        this.expdateUnit = expdateUnit == null ? null : expdateUnit.trim();
    }

    /**
     * 获取产品收益率
     *
     * @return income_rate - 产品收益率
     */
    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    /**
     * 设置产品收益率
     *
     * @param incomeRate 产品收益率
     */
    public void setIncomeRate(BigDecimal incomeRate) {
        this.incomeRate = incomeRate;
    }

    /**
     * 获取最小起购
     *
     * @return subs_start_amount - 最小起购
     */
    public BigDecimal getSubsStartAmount() {
        return subsStartAmount;
    }

    /**
     * 设置最小起购
     *
     * @param subsStartAmount 最小起购
     */
    public void setSubsStartAmount(BigDecimal subsStartAmount) {
        this.subsStartAmount = subsStartAmount;
    }

    /**
     * 获取增减幅度
     *
     * @return subs_range - 增减幅度
     */
    public BigDecimal getSubsRange() {
        return subsRange;
    }

    /**
     * 设置增减幅度
     *
     * @param subsRange 增减幅度
     */
    public void setSubsRange(BigDecimal subsRange) {
        this.subsRange = subsRange;
    }

    /**
     * 获取收益计算类型:1-固定收益
     *
     * @return cal_income_way - 收益计算类型:1-固定收益
     */
    public String getCalIncomeWay() {
        return calIncomeWay;
    }

    /**
     * 设置收益计算类型:1-固定收益
     *
     * @param calIncomeWay 收益计算类型:1-固定收益
     */
    public void setCalIncomeWay(String calIncomeWay) {
        this.calIncomeWay = calIncomeWay == null ? null : calIncomeWay.trim();
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
     * 获取风险级别：1-低，2-较低，3-中，4-较高，5-高
     *
     * @return risk_level - 风险级别：1-低，2-较低，3-中，4-较高，5-高
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置风险级别：1-低，2-较低，3-中，4-较高，5-高
     *
     * @param riskLevel 风险级别：1-低，2-较低，3-中，4-较高，5-高
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 获取付息方式:0-到期一次性还本付息
     *
     * @return interest_type - 付息方式:0-到期一次性还本付息
     */
    public String getInterestType() {
        return interestType;
    }

    /**
     * 设置付息方式:0-到期一次性还本付息
     *
     * @param interestType 付息方式:0-到期一次性还本付息
     */
    public void setInterestType(String interestType) {
        this.interestType = interestType == null ? null : interestType.trim();
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
}