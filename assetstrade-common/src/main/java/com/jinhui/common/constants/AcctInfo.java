package com.jinhui.common.constants;

/**
 * 用户的账户类别枚举类
 */
public enum AcctInfo {
    cny("CNY", "cny", "0"),
    btc("BTC", "btc", "1"),
    eth("ETH", "eth", "2"),
    ltc("LTC", "ltc", "3"),
    usdt("USDT", "usdt", "4"),
    eos("EOS","eos","5");

    private String name;
    private String type;
    private String code;

    AcctInfo(String name, String type, String code) {
        this.name = name;
        this.type = type;
        this.code = code;
    }

    public static AcctInfo byType(String type) {

        AcctInfo[] values = AcctInfo.values();
        for (AcctInfo value : values) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        throw new RuntimeException("不支持的账户类型");

    }

    public static AcctInfo byCode(String code) {

        AcctInfo[] values = AcctInfo.values();
        for (AcctInfo value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new RuntimeException("不支持的账户类型");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AcctInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
