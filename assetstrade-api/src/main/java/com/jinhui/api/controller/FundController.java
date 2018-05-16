package com.jinhui.api.controller;

import com.github.pagehelper.PageInfo;
import com.jinhui.api.entity.po.RegularTrade;
import com.jinhui.api.entity.po.Trade;
import com.jinhui.api.entity.vo.*;
import com.jinhui.api.mapper.RegularTradeMapper;
import com.jinhui.api.mapper.TradeMapper;
import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.Account;
import com.jinhui.common.entity.po.FundGather;
import com.jinhui.common.entity.po.Market;
import com.jinhui.common.mapper.FundGatherMapper;
import com.jinhui.common.mapper.MarketMapper;
import com.jinhui.common.mapper.ProductPositionMapper;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.utils.ListPageUtil;
import com.jinhui.common.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    private static Logger logger = LoggerFactory.getLogger(FundController.class);

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private AccountService accountService;


    @Autowired
    private ProductPositionMapper productPositionMapper;

    @Autowired
    private RegularTradeMapper regularTradeMapper;

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private FundGatherMapper fundGatherMapper;

    /**
     * 查询用户账户的余额
     */
    @GetMapping("queryAcctBalance")
    WebResult queryAcctBalance(@RequestParam String accountType) {

        String userId = UserUtils.getUserId();

        WebResult result = WebResult.ok();
        boolean exist = accountService.isExist(userId, AcctInfo.byType(accountType));
        if (!exist) {
            result.setData("0");
            return result;
        }

        Account account = accountService.queryAccount(userId, AcctInfo.byType(accountType));

        result.setData(account.getPositionVol());

        return result;
    }

    /**
     * 查询用户已拥有的资产名称列表
     */
    @GetMapping("queryFundNames")
    WebResult queryFundName() {

        String userId = UserUtils.getUserId();
        TransQueryParam transQueryParam = new TransQueryParam();
        transQueryParam.setUserId(userId);
        List<String> list = new ArrayList<>();
        List<Trade> trades = tradeMapper.selectByTransTimeAndProduct(transQueryParam);
        for (Trade trade : trades) {

            list.add(trade.getProductName());
        }
        List<RegularTrade> regularTrades = regularTradeMapper.selectByQueryParam(transQueryParam);
        for (RegularTrade regularTrade : regularTrades) {
            list.add(regularTrade.getProductName());
        }
        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        WebResult result = WebResult.ok();
        result.setData(collect);
        return result;
    }

    /**
     * 查询交易记录
     */
    @PostMapping("queryTrans")
    WebResult queryTrans(@RequestBody TransQueryParam transQueryParam) {

        String userId = UserUtils.getUserId();

        transQueryParam.setUserId(userId);

        List<TradeRecordVo> list = new ArrayList<>();

        List<Trade> trades = tradeMapper.selectByTransTimeAndProduct(transQueryParam);
        for (Trade trade : trades) {
            TradeRecordVo tradeRecordVo = TradeRecordVo.toTradeRecordVo(trade);
            list.add(tradeRecordVo);
        }

        List<RegularTrade> regularTrades = regularTradeMapper.selectByQueryParam(transQueryParam);

        for (RegularTrade regularTrade : regularTrades) {
            TradeRecordVo tradeRecordVo = TradeRecordVo.toTradeRecordVo(regularTrade);
            list.add(tradeRecordVo);
        }

        //按时间倒序排列
        List<TradeRecordVo> collect = list.stream().sorted(Comparator.comparing(TradeRecordVo::getTransTime).reversed()).collect(Collectors.toList());

        Integer pageNum = transQueryParam.getPageNum();
        Integer pageSize = transQueryParam.getPageSize();
        ListPageUtil pageUtil=new ListPageUtil(collect,pageNum,pageSize);
        PageInfo pageInfo = pageUtil.toPageInfo();

        WebResult result = WebResult.ok();
        result.setData(pageInfo);

        return result;

    }

    /**
     * 查询用户的资产
     */

    @GetMapping("queryFunds")
    WebResult queryTrans() {

        String userId = UserUtils.getUserId();

        List<Account> accounts = accountService.queryAllByUserId(userId);

        List<UserFundVo> list = new ArrayList<>();

        for (Account account : accounts) {

            UserFundVo userFundVo = new UserFundVo();

            userFundVo.setFundName(account.getAccountName());
            userFundVo.setFundBalance(account.getPositionVol().toPlainString());

            //查询相应的定期产品持仓总金额
            BigDecimal position = productPositionMapper.selectTotalPosition(userId, account.getAccountName());
            userFundVo.setInvestFund(position.toPlainString());

            //相加得到总资产
            BigDecimal totalFunds = position.add(account.getPositionVol());
            userFundVo.setTotalFund(totalFunds.toPlainString());

            //把总资产换算成cny金额
            AcctInfo acctInfo = AcctInfo.byCode(account.getAccountType());

            Market market = marketMapper.selectByBuyPayAndSellPay(AcctInfo.cny.getType(), acctInfo.getType());
            BigDecimal sellPrice = market.getSellPrice();
            userFundVo.setTotalAmount(totalFunds.multiply(sellPrice).toPlainString());

            list.add(userFundVo);
        }

        WebResult result = WebResult.ok();
        result.setData(list);
        return result;
    }

    /**
     * 查询用户的定期资产
     */
    @GetMapping("queryInvestFunds")
    WebResult queryInvestFunds() {
        String userId = UserUtils.getUserId();

        List<RegularTrade> regularTrades = regularTradeMapper.selectByUserId(userId);

        List<InvestFundsVo> list = new ArrayList<>();
        for (RegularTrade regularTrade : regularTrades) {
            InvestFundsVo investFundsVo = InvestFundsVo.toInvestFunsVo(regularTrade);
            list.add(investFundsVo);
        }

        WebResult result = WebResult.ok();
        result.setData(list);
        return result;
    }

    /**
     * 用户的汇总数据
     */
    @GetMapping("queryFundGather")
    WebResult queryFundGather() {
        String userId = UserUtils.getUserId();
        FundGather fundGather = fundGatherMapper.selectByUserId(userId);

        WebResult result = WebResult.ok();
        result.setData(fundGather);

        return result;
    }


}
