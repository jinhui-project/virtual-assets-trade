package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class Market {
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
     * 产品类型
     */
    private String productType;

    /**
     * 买入的计算价格单位
     */
    private String buyCalUnit;

    /**
     * 买入的成交资产单位
     */
    private String buyDoneUnit;

    /**
     * 卖出的计算价格单位
     */
    private String sellCalUnit;

    /**
     * 卖出的成交资产单位
     */
    private String sellDoneUnit;

    /**
     * 买入的支付单位
     */
    private String buyPayUnit;

    /**
     * 卖出的支付单位
     */
    private String sellPayUnit;

    /**
     * 买入的最小数量
     */
    private BigDecimal buyMinVol;

    /**
     * 卖出的最小数量
     */
    private BigDecimal sellMinVol;

    /**
     * 最新价
     */
    private BigDecimal lastPrice;

    /**
     * 最高价
     */
    private BigDecimal highPrice;

    /**
     * 最低价
     */
    private BigDecimal lowPrice;

    /**
     * 买一价
     */
    private BigDecimal buyPrice;

    /**
     * 卖一价
     */
    private BigDecimal sellPrice;

    /**
     * 浮动比率
     */
    private BigDecimal priceChange;

    /**
     * 行情更新时间
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
     * 获取买入的计算价格单位
     *
     * @return buy_cal_unit - 买入的计算价格单位
     */
    public String getBuyCalUnit() {
        return buyCalUnit;
    }

    /**
     * 设置买入的计算价格单位
     *
     * @param buyCalUnit 买入的计算价格单位
     */
    public void setBuyCalUnit(String buyCalUnit) {
        this.buyCalUnit = buyCalUnit == null ? null : buyCalUnit.trim();
    }

    /**
     * 获取买入的成交资产单位
     *
     * @return buy_done_unit - 买入的成交资产单位
     */
    public String getBuyDoneUnit() {
        return buyDoneUnit;
    }

    /**
     * 设置买入的成交资产单位
     *
     * @param buyDoneUnit 买入的成交资产单位
     */
    public void setBuyDoneUnit(String buyDoneUnit) {
        this.buyDoneUnit = buyDoneUnit == null ? null : buyDoneUnit.trim();
    }

    /**
     * 获取卖出的计算价格单位
     *
     * @return sell_cal_unit - 卖出的计算价格单位
     */
    public String getSellCalUnit() {
        return sellCalUnit;
    }

    /**
     * 设置卖出的计算价格单位
     *
     * @param sellCalUnit 卖出的计算价格单位
     */
    public void setSellCalUnit(String sellCalUnit) {
        this.sellCalUnit = sellCalUnit == null ? null : sellCalUnit.trim();
    }

    /**
     * 获取卖出的成交资产单位
     *
     * @return sell_done_unit - 卖出的成交资产单位
     */
    public String getSellDoneUnit() {
        return sellDoneUnit;
    }

    /**
     * 设置卖出的成交资产单位
     *
     * @param sellDoneUnit 卖出的成交资产单位
     */
    public void setSellDoneUnit(String sellDoneUnit) {
        this.sellDoneUnit = sellDoneUnit == null ? null : sellDoneUnit.trim();
    }

    /**
     * 获取买入的支付单位
     *
     * @return buy_pay_unit - 买入的支付单位
     */
    public String getBuyPayUnit() {
        return buyPayUnit;
    }

    /**
     * 设置买入的支付单位
     *
     * @param buyPayUnit 买入的支付单位
     */
    public void setBuyPayUnit(String buyPayUnit) {
        this.buyPayUnit = buyPayUnit == null ? null : buyPayUnit.trim();
    }

    /**
     * 获取卖出的支付单位
     *
     * @return sell_pay_unit - 卖出的支付单位
     */
    public String getSellPayUnit() {
        return sellPayUnit;
    }

    /**
     * 设置卖出的支付单位
     *
     * @param sellPayUnit 卖出的支付单位
     */
    public void setSellPayUnit(String sellPayUnit) {
        this.sellPayUnit = sellPayUnit == null ? null : sellPayUnit.trim();
    }

    /**
     * 获取买入的最小数量
     *
     * @return buy_min_vol - 买入的最小数量
     */
    public BigDecimal getBuyMinVol() {
        return buyMinVol;
    }

    /**
     * 设置买入的最小数量
     *
     * @param buyMinVol 买入的最小数量
     */
    public void setBuyMinVol(BigDecimal buyMinVol) {
        this.buyMinVol = buyMinVol;
    }

    /**
     * 获取卖出的最小数量
     *
     * @return sell_min_vol - 卖出的最小数量
     */
    public BigDecimal getSellMinVol() {
        return sellMinVol;
    }

    /**
     * 设置卖出的最小数量
     *
     * @param sellMinVol 卖出的最小数量
     */
    public void setSellMinVol(BigDecimal sellMinVol) {
        this.sellMinVol = sellMinVol;
    }

    /**
     * 获取最新价
     *
     * @return last_price - 最新价
     */
    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    /**
     * 设置最新价
     *
     * @param lastPrice 最新价
     */
    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    /**
     * 获取最高价
     *
     * @return high_price - 最高价
     */
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    /**
     * 设置最高价
     *
     * @param highPrice 最高价
     */
    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    /**
     * 获取最低价
     *
     * @return low_price - 最低价
     */
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    /**
     * 设置最低价
     *
     * @param lowPrice 最低价
     */
    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * 获取买一价
     *
     * @return buy_price - 买一价
     */
    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    /**
     * 设置买一价
     *
     * @param buyPrice 买一价
     */
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * 获取卖一价
     *
     * @return sell_price - 卖一价
     */
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    /**
     * 设置卖一价
     *
     * @param sellPrice 卖一价
     */
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * 获取浮动比率
     *
     * @return price_change - 浮动比率
     */
    public BigDecimal getPriceChange() {
        return priceChange;
    }

    /**
     * 设置浮动比率
     *
     * @param priceChange 浮动比率
     */
    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    /**
     * 获取行情更新时间
     *
     * @return update_time - 行情更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置行情更新时间
     *
     * @param updateTime 行情更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}