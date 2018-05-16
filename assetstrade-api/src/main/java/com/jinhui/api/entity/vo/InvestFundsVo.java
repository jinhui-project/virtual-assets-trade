package com.jinhui.api.entity.vo;

import com.jinhui.api.entity.po.RegularTrade;
import org.joda.time.DateTime;

/**
 * Created by Administrator on 2018/4/4 0004.
 */
public class InvestFundsVo {

    private String fundName; //资产名称
    private String transTime; //买入时间
    private String fundNum; // 在投资产
    private String rateDate; //起息日
    private String termDate; //到期日
    private String expectIncome; //预计收益


    public static InvestFundsVo toInvestFunsVo(RegularTrade trade) {
        InvestFundsVo vo = new InvestFundsVo();
        vo.setFundName(trade.getProductName());
        vo.setTransTime(new DateTime(trade.getTransTime()).toString("yyyy-MM-dd HH:mm:ss"));
        vo.setFundNum(trade.getTransNum().toString());
        vo.setRateDate(new DateTime(trade.getRateDate()).toString("yyyy-MM-dd"));
        vo.setTermDate(new DateTime(trade.getTermDate()).toString("yyyy-MM-dd"));
        vo.setExpectIncome(trade.getExpectIncome().toString());
        return vo;

    }


    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getFundNum() {
        return fundNum;
    }

    public void setFundNum(String fundNum) {
        this.fundNum = fundNum;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public String getExpectIncome() {
        return expectIncome;
    }

    public void setExpectIncome(String expectIncome) {
        this.expectIncome = expectIncome;
    }
}
