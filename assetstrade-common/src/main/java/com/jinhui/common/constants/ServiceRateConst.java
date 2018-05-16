package com.jinhui.common.constants;

/**
 * 常量
 *
 * @autor wsc
 * @create 2018-03-27 16:26
 **/
public enum ServiceRateConst {
        提币费率("提币费率", "2"),     //提币费率
        买入费率("买入费率", "3"),     //买入费率
        卖出费率("卖出费率", "4");   //卖出费率

        private String name;
        private String rateType;

    ServiceRateConst(String name, String rateType) {
            this.name = name;
            this.rateType = rateType;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }
}
