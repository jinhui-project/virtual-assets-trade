package com.jinhui.common.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.jinhui.common.exception.BizException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class EtherscanApiUtils {


    /**
     * 访问地址
     */
    private final static String API_URL = "https://api.etherscan.io";

    private final static String apiKeyToken = "5M4W1NWEYN7BAG5I5EYR8J57IQD1P5Y8WX";

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
            throw new RuntimeException("EtherscanApiUtil初始化失败");
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

        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append("/api?module=account&action=balance&address=")
                .append(address)
                .append("&tag=latest&apikey=")
                .append(apiKeyToken);
        try {
            return HttpClientUtil.get(config.url(sb.toString()));
        } catch (HttpProcessException e) {
            throw new BizException("访问https://api.etherscan.io,获取余额失败");
        }

    }


    /**
     * 获取交易记录
     * 倒序查看，最大10000条
     */
    public static String getTransactions(String address) {

        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append("/api?module=account&action=txlist&address=")
                .append(address)
                .append("&startblock=0&endblock=99999999&sort=desc&apikey=")
                .append(apiKeyToken);

        try {
            return HttpClientUtil.get(config.url(sb.toString()));
        } catch (HttpProcessException e) {
            throw new BizException("访问https://api.etherscan.io,获取交易记录失败");
        }

    }


    public static void main(String[] args) throws HttpProcessException {

        String address = "0xA41515521e8D137b3159c0502c375189816F8417";
//        StringBuilder sb = new StringBuilder();
//        sb.append(API_URL).append("/api?module=account&action=balance&address=")
//                .append(address)
//                .append("&tag=latest&apikey=")
//                .append(apiKeyToken);
//
//        System.out.println(sb.toString());
//        String s = HttpClientUtil.get(config.url(sb.toString()));
//        System.out.println(s);


        StringBuilder sb = new StringBuilder();
        sb.append(API_URL).append("/api?module=account&action=txlist&address=")
                .append(address)
                .append("&startblock=0&endblock=99999999&sort=desc&apikey=")
                .append(apiKeyToken);

        System.out.println(sb.toString());
        String s = HttpClientUtil.get(config.url(sb.toString()));
        System.out.println(s);


    }







}
