package com.jinhui.common.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

/**
 * Created by Administrator on 2018/3/23 0023.
 */
public class OkCoinApiUtil {
    /**
     * 访问地址
     */
    private final static String API_URL = "https://www.okcoin.cn";

    private static HttpConfig config;

    static {

        Header[] headers = HttpHeader.custom()
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .contentType("application/x-www-form-urlencoded")
                .build();

        HCB hcb = null;
        try {
            hcb = HCB.custom()
                    .timeout(5000)      //超时
                    .pool(20, 5)        //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                    .ssl()              //https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                    .retry(3);//重试3次

        } catch (HttpProcessException e) {
            throw new RuntimeException("HuoBiHttpUtil初始化失败");
        }

        HttpClient client = hcb.build();
        config = HttpConfig.custom()
                .headers(headers)   //设置headers，不需要时则无需设置
                .client(client)
                .encoding("utf-8");
    }


    /**
     * 获取行情
     * GET https://www.okcoin.cn/api/v1/ticker.do?symbol=btc_cny
     */

    public static String getTicker(String symbol) {

        StringBuilder sb = new StringBuilder();
        sb.append(API_URL)
                .append("/api/v1/ticker.do?")
                .append("symbol=")
                .append(symbol);
        String resp = null;
        try {
            resp = HttpClientUtil.get(config.url(sb.toString()));
        } catch (HttpProcessException e) {
            e.printStackTrace();
            return "none";
        }
        return resp;
    }


    public static void main(String[] args) {

        System.out.println( getTicker("btc_cny"));

    }
}
