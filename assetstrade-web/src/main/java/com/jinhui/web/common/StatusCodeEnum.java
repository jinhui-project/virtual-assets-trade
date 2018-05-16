package com.jinhui.web.common;

/**
 * 状态码
 *
 * @autor wsc
 * @create 2018-03-02 10:46
 **/
public class StatusCodeEnum {

    public static enum StatusCode {
        SUCCESS("0000", "1"),     //成功
        FAIL("0010", "2");   //失败

        private String code;
        private String exchangeCode;

        StatusCode(String code, String exchangeCode) {
            this.code = code;
            this.exchangeCode = exchangeCode;
        }

        public String getCode() {
            return code;
        }

        public String getExchangeCode() {
            return exchangeCode;
        }

        public static String getExchCode(String code) {
            for (StatusCode chn : StatusCode.values()) {
                if (chn.getCode().equals(code)) {
                    return chn.getExchangeCode();
                }
            }
            return null;
        }
    }
}
