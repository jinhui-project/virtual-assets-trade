package com.jinhui.api.entity.vo;


import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
public class ProductMarketVo {

    private String productName;

    private String productId;


    private String type;

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


    private String buyRate;//买入费率

    private String sellRate;//卖出费率

    private String buyPayUnit; //买入的支付单位

    private String sellPayUnit; //卖出的支付单位


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuyPayUnit() {
        return buyPayUnit;
    }

    public void setBuyPayUnit(String buyPayUnit) {
        this.buyPayUnit = buyPayUnit;
    }

    public String getSellPayUnit() {
        return sellPayUnit;
    }

    public void setSellPayUnit(String sellPayUnit) {
        this.sellPayUnit = sellPayUnit;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getSellRate() {
        return sellRate;
    }

    public void setSellRate(String sellRate) {
        this.sellRate = sellRate;
    }

    @Override
    public String toString() {
        return "ProductMarketVo{" +
                "productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", type='" + type + '\'' +
                ", lastPrice=" + lastPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
