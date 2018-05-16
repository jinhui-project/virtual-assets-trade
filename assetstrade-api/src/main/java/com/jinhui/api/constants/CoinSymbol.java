package com.jinhui.api.constants;

/**
 * 交易对
 */
public enum CoinSymbol {

//    BTC_CNY("比特币与人民币", "DC0004","004","","btc_cny"),
//    ETH_CNY("以太币与人民币", "DC0005","005","","eth_cny"),

    BTC_USDT("BTC/USDT", "DC0007","0007","btcusdt",""),
    ETH_USDT("ETH/USDT", "DC0008","0008","ethusdt",""),
    LTC_USDT("LTC/USDT","DC0009","0009", "ltcusdt",""),
    EOS_USDT("EOS/USDT","DC1012","1012", "eosusdt",""),

    ETH_BTC("ETH/BTC", "DC0010","0010","ethbtc",""),
    LTC_BTC("LTC/BTC","DC0011","0011", "ltcbtc",""),

    EOS_ETH("EOS/ETH","DC1011","1011", "eoseth","");


    private String productName;//产品名
    private String productId; //产品id
    private String productType;//产品类型
    private String huoBiCode; //火币Api的请求标识
    private String oKCoinCode; //OkCoin Api的请求标识

    CoinSymbol(String productName, String productId, String productType, String huoBiCode, String oKCoinCode) {
        this.productName = productName;
        this.productId = productId;
        this.productType = productType;
        this.huoBiCode = huoBiCode;
        this.oKCoinCode = oKCoinCode;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getHuoBiCode() {
        return huoBiCode;
    }

    public void setHuoBiCode(String huoBiCode) {
        this.huoBiCode = huoBiCode;
    }

    public String getoKCoinCode() {
        return oKCoinCode;
    }

    public void setoKCoinCode(String oKCoinCode) {
        this.oKCoinCode = oKCoinCode;
    }
}
