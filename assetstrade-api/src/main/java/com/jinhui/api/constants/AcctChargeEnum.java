package com.jinhui.api.constants;

import com.jinhui.common.exception.BizException;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
public enum AcctChargeEnum {

    btc_charge("平台BTC收入账户", "1B", "1"),
    etc_charge("平台ETH收入账户", "2E", "2"),
    ltc_charge("平台LTC收入账户", "3L", "3"),
    usdt_charge("平台USDT收入账户", "4U", "4");


    private String accountName;
    private String accountType;
    private String code;

    AcctChargeEnum(String accountName, String accountType, String code) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.code = code;
    }

    public static AcctChargeEnum byCode(String code) {

        AcctChargeEnum[] values = AcctChargeEnum.values();
        for (AcctChargeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new BizException("不支持的账户类型:" + code);
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
