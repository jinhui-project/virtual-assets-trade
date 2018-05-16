package com.jinhui.api.entity.vo;

import com.jinhui.api.entity.po.RegularProduct;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
public class RegularProductVo {

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品期限
     */
    private String expdate;


    /**
     * 产品收益率
     */
    private String incomeRate;

    /**
     * 起购数
     */
    private String subsStart;

    private final static BigDecimal hundred = new BigDecimal("100");

    public static RegularProductVo createRegularProductVo(RegularProduct regularProduct) {
        RegularProductVo regularProductVo = new RegularProductVo();
        regularProductVo.setProductName(regularProduct.getProductName());
        regularProductVo.setProductId(regularProduct.getProductId());

        BigDecimal incomeRate = regularProduct.getIncomeRate();
        String incomeRateStr = incomeRate.multiply(hundred).toString() + "%";
        regularProductVo.setIncomeRate(incomeRateStr);
        regularProductVo.setSubsStart(regularProduct.getSubsStartAmount() + "");

        Integer productExpdate = regularProduct.getProductExpdate();
        String expdateUnit = regularProduct.getExpdateUnit();

        regularProductVo.setExpdate(productExpdate + expdateUnit);
        return regularProductVo;
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

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(String incomeRate) {
        this.incomeRate = incomeRate;
    }

    public String getSubsStart() {
        return subsStart;
    }

    public void setSubsStart(String subsStart) {
        this.subsStart = subsStart;
    }

    public static BigDecimal getHundred() {
        return hundred;
    }

    @Override
    public String toString() {
        return "RegularProductVo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", expdate=" + expdate +
                ", incomeRate=" + incomeRate +
                ", subsStart=" + subsStart +
                '}';
    }
}
