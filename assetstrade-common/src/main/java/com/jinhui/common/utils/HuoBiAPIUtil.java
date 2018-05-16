package com.jinhui.common.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

/**
 * 火币网Api查询工具类
 */
public class HuoBiAPIUtil {

    /**
     * 访问地址
     */
    private final static String API_URL = "https://api.huobipro.com";

    /**
     * 火币网访问密钥
     */
    private final static String AccessKeyId = "f2998c3c-56427bdc-27d45f10-246ee";

    /**
     * 火币网私密密钥
     */
    private final static String SecretKeyId = "89cbbecb-fec4d65b-0c65ccdc-2d376";


    private static HttpConfig config;

    static {

        Header[] headers = HttpHeader.custom().userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36").build();
        HCB hcb = null;
        try {
            hcb = HCB.custom()
                    .timeout(5000)      //超时
                    .pool(20, 5)        //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                    .ssl()              //https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                    .retry(3)//重试3次
                    .proxy("127.0.0.1", 1080);
        } catch (HttpProcessException e) {
            throw new RuntimeException("HuoBiHttpUtil初始化失败");
        }

        HttpClient client = hcb.build();
        config = HttpConfig.custom()
                .headers(headers)    //设置headers，不需要时则无需设置
                .client(client)
                .encoding("utf-8");
    }


    // 行情API

    /**
     * GET /market/history/kline 获取K线数据
     * <p>
     * 请求参数:
     * 参数名称	是否必须	 类型	     描述	 默认值	  取值范围
     * symbol	true	  string	交易对		      btcusdt, bchbtc, rcneth ...
     * period	true	  string	K线类型		      1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year
     * size  	false	  integer	获取数量	 150	  [1,2000]
     * <p>
     * <p>
     * 响应数据:
     * 参数名称	是否必须  	数据类型	    描述	                        取值范围
     * status	true	    string	   请求处理结果	"ok" , "error"
     * ts	    true 	    number	   响应生成时间点，单位：毫秒
     * tick	    true	    object	   KLine 数据
     * ch	    true	    string	   数据所属的 channel，格式： market.$symbol.kline.$period
     */
    public static String getKline(String symbol, String period, String size) {

        StringBuilder sb = new StringBuilder();
        sb.append(API_URL)
                .append("/market/history/kline?")
                .append("period=")
                .append(period)
                .append("&size=")
                .append(size)
                .append("&symbol=")
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

    public static String getKline(String symbol, String period) {
        return getKline(symbol, period, "150");
    }

    /**
     * GET /market/detail/merged 获取聚合行情(Ticker)
     * 请求参数:
     * <p>
     * 参数名称	是否必须	类型	    描述	   默认值	取值范围
     * symbol	true	string	交易对		   btcusdt, bchbtc, rcneth ...
     * <p>
     * 响应数据:
     * <p>
     * 参数名称	是否必须	数据类型	   描述	       取值范围
     * status	true	string	   请求处理结果	"ok" , "error"
     * ts	    true	number	   响应生成时间点，单位：毫秒
     * tick	    true	object	   K线数据
     * ch	    true	string	   数据所属的 channel，格式： market.$symbol.detail.merged
     */
    public static String getMerged(String symbol) {

        StringBuilder sb = new StringBuilder();
        sb.append(API_URL)
                .append("/market/detail/merged?")
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
//        String resp = getKline("btcusdt", "5min","10");
        String resp = getMerged("btccny");
        System.out.println(resp);
    }


}