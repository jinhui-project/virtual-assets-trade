package com.jinhui.api.service.trans.impl;


import com.jinhui.api.constants.AcctChargeEnum;
import com.jinhui.api.constants.TransConst;
import com.jinhui.api.entity.po.Trade;
import com.jinhui.api.mapper.TradeMapper;
import com.jinhui.api.service.trans.TransService;
import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.constants.MarketAcctEnum;
import com.jinhui.common.constants.ServiceRateConst;
import com.jinhui.common.entity.po.Account;
import com.jinhui.common.entity.po.Market;
import com.jinhui.common.entity.po.ServiceRate;
import com.jinhui.common.entity.po.User;
import com.jinhui.common.exception.BizException;
import com.jinhui.common.mapper.MarketMapper;
import com.jinhui.common.mapper.ServiceRateMapper;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.account.FundGatherService;
import com.jinhui.common.service.account.PlatformAccountService;
import com.jinhui.common.service.id.IdService;
import com.jinhui.common.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
@Service
public class TransServiceImpl implements TransService {

    private static Logger logger = LoggerFactory.getLogger(TransServiceImpl.class);

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private IdService idService;

    @Autowired
    private ServiceRateMapper serviceRateMapper;

    @Autowired
    private PlatformAccountService platformAccountService;

    @Autowired
    private FundGatherService fundGatherService;


    @Override
    public List<Market> queryMarkets() {
        return marketMapper.selectAll();
    }

    @Override
    public Market queryMarket(String productName) {
        return marketMapper.selectByProductName(productName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//失败要回滚
    public void buy(BigDecimal buyVol, String productId) {


        User user = UserUtils.getUser();
        String userId = user.getUserId();
        String userName = user.getUserName();
//        userId = "M990000001";
//        userName = "测试1";

        ServiceRate serviceRate = serviceRateMapper.selectByRateType(ServiceRateConst.买入费率.getRateType());
        BigDecimal charge = serviceRate.getRate();

        Market productMarket = marketMapper.selectByProductId(productId);

        //检查购买的数量
        BigDecimal buyMinVol = productMarket.getBuyMinVol();
        if (buyVol.compareTo(buyMinVol) < 0) {
            throw new BizException("购买数量不能小于:" + buyMinVol);
        }


        BigDecimal lastPrice = productMarket.getBuyPrice();
        BigDecimal buyAmount = lastPrice.multiply(buyVol);
        String buyPayUnit = productMarket.getBuyPayUnit();//购买的支付账户的类型

        //检查相应的支付账户是否存在
        if (!accountService.isExist(userId, AcctInfo.byType(buyPayUnit))) {
            throw new BizException(buyPayUnit.toUpperCase() + "账户余额不足,购买失败");
        }


        //减去平台支付账户的持仓
        String sellPayUnit = productMarket.getSellPayUnit();
        AcctInfo PayAccount = AcctInfo.byType(sellPayUnit);
        int i = platformAccountService.subtractPosition(MarketAcctEnum.byAcctCode(PayAccount.getCode()), buyVol);
        if (i == 0) {
            throw new BizException(productMarket.getProductName() + "产品库存不足,购买失败");
        }


        AcctInfo buyPayAcctInfo = AcctInfo.byType(buyPayUnit);

        //增加平台收入账户的持仓
        platformAccountService.addPosition(MarketAcctEnum.byAcctCode(buyPayAcctInfo.getCode()), buyAmount);


        //减去支付账户的持仓
        Account purchaseAccount = accountService.queryAccount(userId, AcctInfo.byType(buyPayUnit));
        boolean purchaseFlag = accountService.subtractPosition(purchaseAccount.getUserAccount(), buyAmount);
        if (purchaseFlag == false) {
            throw new BizException(buyPayUnit.toUpperCase() + "账户余额不足,购买失败");
        }

        //如果没有相应的成交账户,则新增一个
        String buyDoneUnit = productMarket.getBuyDoneUnit();//成交的货币类型
        boolean exist = accountService.isExist(userId, AcctInfo.byType(buyDoneUnit));
        if (exist == false) {
            accountService.addAccount(userId, userName, AcctInfo.byType(buyDoneUnit));
        }

        //手续费按成交后获得的数字货币资产结算
        BigDecimal chargeAmount = buyVol.multiply(charge);
        BigDecimal doneAmount = buyVol.subtract(chargeAmount);//成交后的资产

        Account account = accountService.queryAccount(userId, AcctInfo.byType(buyDoneUnit));

        //增加成交账户的资产
        accountService.addPosition(account.getUserAccount(), doneAmount);

        //增加平台手续费账户的持仓
        AcctInfo buyDoneAccount = AcctInfo.byType(buyDoneUnit);
        AcctChargeEnum acctCharge = AcctChargeEnum.byCode(buyDoneAccount.getCode());
        platformAccountService.addCharge(acctCharge.getAccountType(), chargeAmount);

        //保存交易记录
        Trade trade = new Trade();
        trade.setSerialNo(idService.generateTransNo());
        trade.setTransTime(new Date());
        trade.setUserId(userId);
        trade.setUserName(userName);
        trade.setProductId(productMarket.getProductId());
        trade.setProductName(productMarket.getProductName());
        trade.setTransPrice(lastPrice);//结算价格
        trade.setTransNum(buyVol);//交易数量
        trade.setPriceUnit(AcctInfo.byType(productMarket.getBuyCalUnit()).getCode());//买入的计算价格单位
        trade.setTransAmount(buyAmount);//交易金额
        trade.setChargeAmount(chargeAmount);//手续费
        trade.setDoneUnit(AcctInfo.byType(productMarket.getBuyDoneUnit()).getCode());//成交资产单位
        trade.setDoneAmount(doneAmount);//成交资产数
        trade.setTransStatus(TransConst.TransStatus.success);
        trade.setBussType(TransConst.BussType.buy);
        trade.setUpdateTime(new Date());
        trade.setCreateTime(new Date());
        trade.setProductType(productMarket.getProductType());

        tradeMapper.insert(trade);

        // 更新账户资金汇总
        fundGatherService.updateFundGather(userId);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)//失败要回滚
    public void sell(BigDecimal sellVol, String productId) {


        User user = UserUtils.getUser();
        String userId = user.getUserId();
        String userName = user.getUserName();
//        userId = "M990000001";
//        userName = "测试1";

        ServiceRate serviceRate = serviceRateMapper.selectByRateType(ServiceRateConst.卖出费率.getRateType());
        BigDecimal charge = serviceRate.getRate();

        Market productMarket = marketMapper.selectByProductId(productId);


        //检查卖出的数量
        BigDecimal sellMinVol = productMarket.getBuyMinVol();
        if (sellVol.compareTo(sellMinVol) < 0) {
            throw new BizException("出售数量不能小于:" + sellMinVol);
        }


        String sellPayUnit = productMarket.getSellPayUnit();//卖出的支付账户的类型

        //检查卖出账户是否存在
        if (!accountService.isExist(userId, AcctInfo.byType(sellPayUnit))) {
            throw new BizException(sellPayUnit.toUpperCase() + "账户余额不足,卖出失败");
        }

        //减去卖出账户的持仓
        Account purchaseAccount = accountService.queryAccount(userId, AcctInfo.byType(sellPayUnit));
        boolean purchaseFlag = accountService.subtractPosition(purchaseAccount.getUserAccount(), sellVol);
        if (purchaseFlag == false) {
            throw new BizException(sellPayUnit.toUpperCase() + "账户余额不足,卖出失败");
        }

        //增加平台账户的持仓
        AcctInfo acctInfo = AcctInfo.byType(sellPayUnit);
        platformAccountService.addPosition(MarketAcctEnum.byAcctCode(acctInfo.getCode()), sellVol);


        //如果没有相应的成交账户,则新增一个
        String sellDoneUnit = productMarket.getSellDoneUnit();
        boolean exist = accountService.isExist(userId, AcctInfo.byType(sellDoneUnit));
        if (exist == false) {
            accountService.addAccount(userId, userName, AcctInfo.byType(sellDoneUnit));
        }

        //手续费按成交后获得的数字货币资产结算
        BigDecimal lastPrice = productMarket.getSellPrice();
        BigDecimal sellAmount = lastPrice.multiply(sellVol);

        BigDecimal chargeAmount = sellAmount.multiply(charge);
        BigDecimal amount = sellAmount.subtract(chargeAmount);//转入份额

        Account account = accountService.queryAccount(userId, AcctInfo.byType(sellDoneUnit));
        //增加转入账户的持仓
        accountService.addPosition(account.getUserAccount(), amount);


        //增加平台手续费账户的持仓
        AcctInfo buyDoneAccount = AcctInfo.byType(sellDoneUnit);
        AcctChargeEnum acctCharge = AcctChargeEnum.byCode(buyDoneAccount.getCode());
        platformAccountService.addCharge(acctCharge.getAccountType(), chargeAmount);


        //保存交易记录
        Trade trade = new Trade();
        trade.setSerialNo(idService.generateTransNo());
        trade.setTransTime(new Date());
        trade.setUserId(userId);
        trade.setUserName(userName);
        trade.setProductId(productMarket.getProductId());
        trade.setProductName(productMarket.getProductName());
        trade.setTransPrice(lastPrice);//卖出价格
        trade.setTransNum(sellVol);
        trade.setTransAmount(sellAmount);//卖出资产总数
        trade.setPriceUnit(AcctInfo.byType(productMarket.getSellCalUnit()).getCode());//卖出的计算价格单位
        trade.setChargeAmount(chargeAmount);//手续费
        trade.setDoneUnit(AcctInfo.byType(productMarket.getSellDoneUnit()).getCode());//成交资产单位
        trade.setDoneAmount(amount);//成交资产数
        trade.setTransStatus(TransConst.TransStatus.success);
        trade.setBussType(TransConst.BussType.sell);
        trade.setUpdateTime(new Date());
        trade.setCreateTime(new Date());
        trade.setProductType(productMarket.getProductType());
        tradeMapper.insert(trade);

        // 更新账户资金汇总
        fundGatherService.updateFundGather(userId);
    }


}
