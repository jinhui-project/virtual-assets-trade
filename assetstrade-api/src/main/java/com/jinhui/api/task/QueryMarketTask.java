package com.jinhui.api.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinhui.api.constants.CoinSymbol;
import com.jinhui.common.entity.po.Market;
import com.jinhui.common.mapper.MarketMapper;
import com.jinhui.common.utils.HuoBiAPIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 定时查询火币网上的货币聚合行情
 */

@Component
public class QueryMarketTask {

    @Autowired
    private MarketMapper marketMapper;

    /**
     * 每5分钟查询一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void queryMerged() {

        CoinSymbol[] coinSymbols = CoinSymbol.values();
        Market market = null;
        for (CoinSymbol symbol : coinSymbols) {

            if (!symbol.getHuoBiCode().equals("")) {
                String json = HuoBiAPIUtil.getMerged(symbol.getHuoBiCode());
                JSONObject jsonObject = JSON.parseObject(json);
                String status = jsonObject.getString("status");
                if (!status.equals("ok")) {
                    continue;
                }
                market = huobiToMarket(jsonObject);
            }

//            if (!symbol.getoKCoinCode().equals("")) {
//                String json = OkCoinApiUtil.getTicker(symbol.getoKCoinCode());
//                JSONObject jsonObject = JSON.parseObject(json);
//                market = okCoinToMarket(jsonObject);
//            }

            market.setProductId(symbol.getProductId());
            market.setProductName(symbol.getProductName());
            market.setProductType(symbol.getProductType());

            if (marketMapper.selectByProductId(symbol.getProductId()) == null) {
                marketMapper.insert(market);
            } else {
//                marketMapper.updateByProductId(market);
            }
        }

    }

    /**
     * 火币网的数据转换成行情类
     */
    private Market huobiToMarket(JSONObject resp) {

        Market market = new Market();
        long ts = resp.getLong("ts");//响应时间
        JSONObject tick = resp.getJSONObject("tick");//K线数据
        String close = tick.getString("close");//收盘价
        String high = tick.getString("high");//最高价
        String low = tick.getString("low");//最低价
        JSONArray bid = tick.getJSONArray("bid");//[买1价,买1量]
        String buy = bid.getString(0);//买一价
        JSONArray ask = tick.getJSONArray("ask");//[卖1价,卖1量]
        String sell = ask.getString(0);//卖一价

        market.setBuyPrice(new BigDecimal(buy));
        market.setSellPrice(new BigDecimal(sell));
        market.setHighPrice(new BigDecimal(high));
        market.setLastPrice(new BigDecimal(close));
        market.setLowPrice(new BigDecimal(low));
        market.setUpdateTime(new Date(ts));

        return market;
    }

    /**
     * okCoin的数据转换成行情类
     */
    private Market okCoinToMarket(JSONObject resp) {
        Market market = new Market();
        long ts = resp.getLong("date");//响应时间
        JSONObject tick = resp.getJSONObject("ticker");//K线数据
        String close = tick.getString("last");//收盘价
        String high = tick.getString("high");//最高价
        String low = tick.getString("low");//最低价
        String buy = tick.getString("buy");//买一价
        String sell = tick.getString("sell");//卖一价

        market.setBuyPrice(new BigDecimal(buy));
        market.setSellPrice(new BigDecimal(sell));
        market.setHighPrice(new BigDecimal(high));
        market.setLastPrice(new BigDecimal(close));
        market.setLowPrice(new BigDecimal(low));
        market.setUpdateTime(new Date(ts));

        return market;

    }


}
