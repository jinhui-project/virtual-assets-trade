package com.jinhui.api.service.trans;


import com.jinhui.common.entity.po.Market;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public interface TransService {


    /**
     * 查询行情
     */
    List<Market> queryMarkets();

    /**
     * 根据产品ID查询行情
     */
    Market queryMarket(String productName);


    /**
     * @param buyNum    买入数量
     * @param productId 产品ID
     */
    void buy(BigDecimal buyNum, String productId);


    /**
     * @param sellNum   卖出数量
     * @param productId 产品ID
     */
    void sell(BigDecimal sellNum, String productId);


}
