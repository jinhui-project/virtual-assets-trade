package com.jinhui.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.jinhui.common.exception.BizException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class BtcApiUtils {
    /**
     * 访问地址
     */
    private final static String API_URL = "https://chain.api.btc.com";

    private static HttpConfig config;

    static {

        Header[] headers = HttpHeader.custom()
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .contentType("application/json; charset=utf-8")
                .build();

        HCB hcb = null;
        try {
            hcb = HCB.custom()
                    .timeout(5000)      //超时
                    .pool(20, 5)        //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                    .ssl()              //https
                    .retry(3);//重试3次
        } catch (HttpProcessException e) {
            throw new RuntimeException("BtcApiUtils初始化失败");
        }

        HttpClient client = hcb.build();
        config = HttpConfig.custom()
                .headers(headers)   //设置headers，不需要时则无需设置
                .client(client)
                .encoding("utf-8");
    }

    /**
     * 根据地址获取ether余额
     */
    public static String getBalance(String address) {

        //   https://chain.api.btc.com/v3/address/15urYnyeJe3gwbGJ74wcX89Tz7ZtsFDVew

        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append("/v3/address/")
                .append(address);

        try {
            return HttpClientUtil.get(config.url(sb.toString()));
        } catch (HttpProcessException e) {
            throw new BizException("访问https://chain.api.btc.com,获取余额失败");
        }


    }


    /**
     * 获取交易记录
     */
    public static String getTransactions(String address, int page) {
        // https://chain.api.btc.com/v3/address/15urYnyeJe3gwbGJ74wcX89Tz7ZtsFDVew/tx

//        page，可选，默认为1，页码
//        pagesize，可选，默认为50，可选范围为1-50，分页大小
        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append("/v3/address/")
                .append(address)
                .append("/tx?verbose=2&page=")
                .append(page);

        try {
            return HttpClientUtil.get(config.url(sb.toString()));
        } catch (HttpProcessException e) {
            throw new BizException("访问https://chain.api.btc.com,获取交易记录失败");
        }
    }

    public static void main(String[] args) {
        String transactions = getTransactions("1FVA974Aczh114rUoAc33cBFMK3p1s8HbJ", 1);
        System.out.println(transactions);


    }

}
