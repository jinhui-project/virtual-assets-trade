package com.jinhui.api.entity.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
public class BuyCoin {

    @NotNull(message = "产品id不能为空")
    private String productId;

    private String productName;

    private String productType;

    /**
     * 买入价
     */
    private String buyPrice;

    /**
     * 买入量
     */
    @NotNull(message = "买入量不能为空")
    private String buyVol;

    /**
     * 交易额
     */
    private String amount;

    /**
     * 手续费
     */
    private String charge;

    /**
     * 资金密码
     */
    @NotEmpty(message = "资金密码不能为空")
    private String passWord;


    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyVol() {
        return buyVol;
    }

    public void setBuyVol(String buyVol) {
        this.buyVol = buyVol;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "BuyCoin{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", buyVol='" + buyVol + '\'' +
                ", amount='" + amount + '\'' +
                ", charge='" + charge + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
