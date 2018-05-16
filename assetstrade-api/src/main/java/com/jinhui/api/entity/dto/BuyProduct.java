package com.jinhui.api.entity.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
public class BuyProduct {

    @NotNull(message = "产品id不能为空")
    private String productId;

    @NotNull(message = "产品名称不能为空")
    private String productName;

    @NotNull(message = "买入量不能为空")
    private String buyVol;

    @NotEmpty(message = "资金密码不能为空")
    private String passWord;

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

    public String getBuyVol() {
        return buyVol;
    }

    public void setBuyVol(String buyVol) {
        this.buyVol = buyVol;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "BuyProduct{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", buyVol='" + buyVol + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
