package com.jinhui.api.entity.vo;

import com.jinhui.api.entity.po.RegularTrade;
import com.jinhui.api.entity.po.Trade;
import com.jinhui.common.constants.AcctInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/29 0029.
 */
public class TradeRecordVo {

    /**
     * 交易时间
     */
    private Date transTime;

    /**
     * 交易流水号
     */
    private String serialNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品id
     */
    private String productId;


    /**
     * 买入,卖出
     */
    private String bussType;

    /**
     * 配置数量
     */
    private BigDecimal transNum;

    /**
     * 资产价格单位
     */
    private String priceUnit;

    /**
     * 资产价格
     */
    private BigDecimal transPrice;

    /**
     * 配置金额
     */
    private BigDecimal transAmount;

    /**
     * 成交资产单位
     */
    private String doneUnit;


    /**
     * 手续费,手续费按成交后获得的数字货币资产结算
     */
    private BigDecimal chargeAmount;

    /**
     * 交易状态:0-处理中,1-成功,2-失败
     */
    private String transStatus;


    public static TradeRecordVo toTradeRecordVo(Trade trade) {

        TradeRecordVo vo = new TradeRecordVo();
        vo.setTransTime(trade.getTransTime());
        vo.setSerialNo(trade.getSerialNo());
        vo.setProductName(trade.getProductName());
        vo.setProductId(trade.getProductId());
        vo.setBussType(trade.getBussType());
        vo.setTransNum(trade.getTransNum());
        vo.setPriceUnit(AcctInfo.byCode(trade.getPriceUnit()).getName());
        vo.setTransPrice(trade.getTransPrice());
        vo.setTransAmount(trade.getTransAmount());
        vo.setDoneUnit(AcctInfo.byCode(trade.getDoneUnit()).getName());
        vo.setChargeAmount(trade.getChargeAmount());
        vo.setTransStatus(trade.getTransStatus());
        return vo;

    }

    public static TradeRecordVo toTradeRecordVo(RegularTrade regularTrade) {

        TradeRecordVo vo = new TradeRecordVo();
        vo.setTransTime(regularTrade.getTransTime());
        vo.setSerialNo(regularTrade.getSerialNo());
        vo.setProductName(regularTrade.getProductName());
        vo.setProductId(regularTrade.getProductId());
        vo.setBussType(regularTrade.getBussType());
        vo.setTransNum(regularTrade.getTransNum());
        vo.setChargeAmount(regularTrade.getChargeAmount());
        vo.setTransStatus(regularTrade.getTransStatus());

        return vo;
    }


    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public BigDecimal getTransNum() {
        return transNum;
    }

    public void setTransNum(BigDecimal transNum) {
        this.transNum = transNum;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public BigDecimal getTransPrice() {
        return transPrice;
    }

    public void setTransPrice(BigDecimal transPrice) {
        this.transPrice = transPrice;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getDoneUnit() {
        return doneUnit;
    }

    public void setDoneUnit(String doneUnit) {
        this.doneUnit = doneUnit;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }
}
