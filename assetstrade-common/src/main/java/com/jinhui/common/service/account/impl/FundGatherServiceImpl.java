package com.jinhui.common.service.account.impl;

import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.Account;
import com.jinhui.common.entity.po.FundGather;
import com.jinhui.common.entity.po.Market;
import com.jinhui.common.mapper.FundGatherMapper;
import com.jinhui.common.mapper.FundTransferMapper;
import com.jinhui.common.mapper.MarketMapper;
import com.jinhui.common.mapper.ProductPositionMapper;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.account.FundGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @autor wsc
 * @create 2018-03-29 14:13
 **/
@Service
public class FundGatherServiceImpl implements FundGatherService {

    @Autowired
    private FundGatherMapper fundGatherMapper;

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductPositionMapper productPositionMapper;

    @Autowired
    private FundTransferMapper fundTransferMapper;


    @Override
    public int initFundGather(String userId) {
        FundGather fundGather = new FundGather();
        fundGather.setUserId(userId);
        return fundGatherMapper.insertSelective(fundGather);
    }


    @Override
    public void updateFundGather(String userId) {
        FundGather fundGather = new FundGather();
        fundGather.setUserId(userId);
        List<Account> accounts = accountService.queryAllByUserId(userId);
        if (accounts == null) {
            return;
        }

        //统计用户的充值成功后的总金额
        BigDecimal recharge = fundTransferMapper.statistics("02", "1", userId);

        //统计用户的提现成功后的总金额
        BigDecimal 	withdraw = fundTransferMapper.statistics("03", "1", userId);

        fundGather.setRechargeAmount(recharge.subtract(withdraw));

        BigDecimal productInvestAmount = new BigDecimal("0");//定期产品折合人名币
        BigDecimal balanceAmount = new BigDecimal("0");//数字货币资产市值折合人名币最新价格
        BigDecimal totalAmount = new BigDecimal("0");//总资产市值
        for (Account account : accounts) {

            AcctInfo acctInfo = AcctInfo.byCode(account.getAccountType());

            //数字货币对人名币的行情
            Market market = marketMapper.selectByBuyPayAndSellPay(AcctInfo.cny.getType(), acctInfo.getType());

            //账户余额
            BigDecimal sellPrice = market.getSellPrice();
            balanceAmount = balanceAmount.add(account.getPositionVol().multiply(sellPrice));

            //产品持仓
            BigDecimal position = productPositionMapper.selectTotalPosition(userId, account.getAccountName());


            productInvestAmount = productInvestAmount.add(position.multiply(sellPrice));

            BigDecimal lastPrice = market.getLastPrice();

            totalAmount = totalAmount.add(account.getPositionVol().multiply(lastPrice));
            totalAmount = totalAmount.add(position.multiply(lastPrice));
        }


        fundGather.setBalanceAmount(balanceAmount);//可用资金的卖出价格
        fundGather.setLastInvestAmount(totalAmount);
        fundGather.setInvestAmount(productInvestAmount);
        fundGather.setUpdateTime(new Date());

        fundGatherMapper.updateByUserId(fundGather);
    }


}
