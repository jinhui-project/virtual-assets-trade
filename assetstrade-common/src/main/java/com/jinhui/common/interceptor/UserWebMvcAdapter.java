package com.jinhui.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @autor wsc
 * @create 2018-04-02 15:24
 **/
@Configuration
public class UserWebMvcAdapter extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns(
                                               "/user/improveUserInfo",  //完善客户信息
                                               "/account/recharge",    //充币申请
                                               "/account/withdraw",    //提币申请
                                               "/account/withdrawFee", //提币手续费
                                               "/account/sendSmsCode",//提币发送验证码
                                               "/account/canWithdrawAmount",  //可提额度
                                               "/public/queryWithdrawCoins", //获取用户可提现的币种列表
                                               "/account/queryAssetsList",  //账户资金明细
                "/trans/buyCoin", //买币
                "/trans/sellCoin", //卖币
                "/fund/queryTrans",//查询用户交易
                "/fund/queryFunds",//查询用户个人资产
                "/fund/queryInvestFunds",//查询在投资产
                "/fund/queryFundNames",//查询在投资产的名称列表
                "/fund/queryAcctBalance",//查询用户账户余额
                "/fund/queryFundGather",//查询用户金额汇总
                "/product/buyProduct"//购买定期产品
        );
    }
}
