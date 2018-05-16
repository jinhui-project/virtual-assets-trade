package com.jinhui.common.service.account;


/**
 * 账户资产汇总服务层
 *
 * @autor wsc
 * @create 2018-03-29 14:12
 **/
public interface FundGatherService {

    //注册成功之后初始化账户资产信息
    int initFundGather(String userId);



    //用户每次交易后,更新资产汇总信息
    void updateFundGather(String userId);


}
