package com.jinhui.api.controller;

import com.jinhui.api.entity.dto.BuyCoin;
import com.jinhui.api.entity.dto.SellCoin;
import com.jinhui.api.entity.vo.ProductMarketVo;
import com.jinhui.api.entity.vo.WebResult;
import com.jinhui.api.service.trans.TransService;
import com.jinhui.common.constants.ServiceRateConst;
import com.jinhui.common.entity.po.Market;
import com.jinhui.common.entity.po.ServiceRate;
import com.jinhui.common.exception.BizException;
import com.jinhui.common.service.account.ServiceRateService;
import com.jinhui.common.utils.validator.ValidatorUtils;
import org.bouncycastle.pqc.math.linearalgebra.BigEndianConversions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/6 0006.
 */
@RestController
@RequestMapping("/trans")
public class TransController {

    private static Logger logger = LoggerFactory.getLogger(TransController.class);

    @Autowired
    private TransService transService;

    @Autowired
    private ServiceRateService serviceRateService;

    @Autowired
    private static BigDecimal hundred =new BigDecimal("100");


    /**
     * 查询行情
     */
    @GetMapping("queryMarkets")
    WebResult queryMarkets() throws Exception {

        List<Market> markets = transService.queryMarkets();
        List productMarketVoList = new ArrayList<ProductMarketVo>();

        ServiceRate buyRate = serviceRateService.queryRateByType(ServiceRateConst.买入费率);
        ServiceRate sellRate = serviceRateService.queryRateByType(ServiceRateConst.卖出费率);


        for (Market market : markets) {
            ProductMarketVo vo = new ProductMarketVo();
            BeanUtils.copyProperties(market, vo);
            vo.setType(market.getBuyCalUnit().toUpperCase());
            vo.setBuyRate(buyRate.getRate().multiply(hundred).toString()+"%");
            vo.setSellRate(sellRate.getRate().multiply(hundred).toString()+"%");
            productMarketVoList.add(vo);
        }

        WebResult result = WebResult.ok();
        result.setData(productMarketVoList);

        return result;

    }

    /**
     * 根据产品名称查询行情
     */
    @GetMapping("queryMarket")
    WebResult queryMarket(@RequestParam("productName") String productName) throws Exception {

        ServiceRate buyRate = serviceRateService.queryRateByType(ServiceRateConst.买入费率);
        ServiceRate sellRate = serviceRateService.queryRateByType(ServiceRateConst.卖出费率);


        Market market = transService.queryMarket(productName);
        ProductMarketVo vo = new ProductMarketVo();
        BeanUtils.copyProperties(market, vo);
        vo.setType(market.getBuyCalUnit().toUpperCase());
        vo.setBuyRate(buyRate.getRate().multiply(hundred).toString()+"%");
        vo.setSellRate(sellRate.getRate().multiply(hundred).toString()+"%");

        WebResult result = WebResult.ok();

        result.setData(vo);

        return result;

    }


    @PostMapping("buyCoin")
    WebResult buyCoin(@RequestBody BuyCoin buyCoin) {

        //验证
        ValidatorUtils.validateEntity(buyCoin);

        //检查资金密码

        //购买
        BigDecimal buyNum = new BigDecimal(buyCoin.getBuyVol());
        String productId = buyCoin.getProductId();
        transService.buy(buyNum, productId);

        return WebResult.ok();

    }

    @PostMapping("sellCoin")
    WebResult sellCoin(@RequestBody SellCoin sellCoin) {

        //验证
        ValidatorUtils.validateEntity(sellCoin);

        //检查资金密码

        //出售
        BigDecimal sellNum = new BigDecimal(sellCoin.getSellVol());
        String productId = sellCoin.getProductId();
        transService.sell(sellNum, productId);

        return WebResult.ok();

    }



}
