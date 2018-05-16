package com.jinhui.common.constants;

/**
 * 与账户类别相对应的产品行情枚举类
 */
public enum MarketAcctEnum {

    CNY("CNY","0"),
    BTC_CNY("BTC/CNY", "1"),
    ETH_CNY("ETH/CNY", "2"),
    LTC_CNY("LTC/CNY", "3"),
    USDT_CNY("USDT/CNY", "4"),
    BCH_CNY("BCH/CNY", "5"),
    ETC_CNY("ETC/CNY", "6"),
    EOS_CNY("EOS/CNY", "7");


    private String productName;
    private String acctCode;

    MarketAcctEnum(String productName, String acctCode) {
        this.productName = productName;
        this.acctCode = acctCode;
    }


    public static MarketAcctEnum byAcctCode(String acctCode){
        MarketAcctEnum[] values = MarketAcctEnum.values();
        for (MarketAcctEnum value : values) {
            if (value.getAcctCode().equals(acctCode)){
                return value;
            }
        }
        throw new RuntimeException("不支持的产品行情"+acctCode);

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAcctCode() {
        return acctCode;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
}
