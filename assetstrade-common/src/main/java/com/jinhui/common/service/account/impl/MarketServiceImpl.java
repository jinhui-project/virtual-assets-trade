package com.jinhui.common.service.account.impl;

import com.jinhui.common.entity.po.Market;
import com.jinhui.common.mapper.MarketMapper;
import com.jinhui.common.service.account.MarketServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @autor wsc
 * @create 2018-03-28 14:18
 **/
@Service("marketServcie")
public class MarketServiceImpl implements MarketServcie {

    @Autowired
    private MarketMapper marketMapper;

    @Override
    public Market selectByProductName(String productName) {
        return marketMapper.selectByProductName(productName);
    }
}
