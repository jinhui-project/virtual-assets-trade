package com.jinhui.api.service.product.impl;

import com.jinhui.api.constants.TransConst;
import com.jinhui.common.entity.po.ProductPosition;
import com.jinhui.api.entity.po.RegularProduct;
import com.jinhui.api.entity.po.RegularTrade;
import com.jinhui.api.mapper.RegularProductMapper;
import com.jinhui.api.mapper.RegularTradeMapper;
import com.jinhui.api.service.product.ProductService;
import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.Account;
import com.jinhui.common.entity.po.User;
import com.jinhui.common.exception.BizException;
import com.jinhui.common.mapper.ProductPositionMapper;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.account.FundGatherService;
import com.jinhui.common.service.account.PlatformAccountService;
import com.jinhui.common.service.id.IdService;
import com.jinhui.common.utils.UserUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RegularProductMapper regularProductMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlatformAccountService platformAccountService;

    @Autowired
    private ProductPositionMapper productPositionMapper;

    @Autowired
    private RegularTradeMapper regularTradeMapper;

    @Autowired
    private IdService idService;

    @Autowired
    private FundGatherService fundGatherService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void buyProduct(String productId, String productName, BigDecimal buyVol) {

        User user = UserUtils.getUser();
        String userId = user.getUserId();
        String userName = user.getUserName();

//
//        userId = "M990000001";
//        userName = "测试1";

        RegularProduct regularProduct = regularProductMapper.selectById(productId);

        String payUnit = regularProduct.getPayUnit();

        //检查购买的数量
        BigDecimal startAmount = regularProduct.getSubsStartAmount();
        if (buyVol.compareTo(startAmount) < 0) {
            throw new BizException("购买数量不能小于:" + startAmount);
        }

        //检查用户的是否有账户
        if (!accountService.isExist(userId, AcctInfo.byType(payUnit))) {
            throw new BizException(payUnit.toUpperCase() + "账户余额不足,购买失败");
        }

        //减去用户支付账户的持仓
        Account purchaseAccount = accountService.queryAccount(userId, AcctInfo.byType(payUnit));
        boolean purchaseFlag = accountService.subtractPosition(purchaseAccount.getUserAccount(), buyVol);
        if (purchaseFlag == false) {
            throw new BizException(payUnit.toUpperCase() + "账户余额不足,购买失败");
        }

        //增加平台的收入
        platformAccountService.addPosition(AcctInfo.byType(payUnit).getCode(), buyVol);

        //如果用户没有该产品的持仓，先初始化
        ProductPosition productPosition = productPositionMapper.selectByUserIdAndProductId(userId, productId);
        if (productPosition == null) {
            ProductPosition position = new ProductPosition();
            position.setProductId(productId);
            position.setProductName(productName);
            position.setPositionVol(new BigDecimal("0"));
            position.setGatherDate(new Date());
            position.setUserId(userId);
            position.setUserName(userName);
            position.setAccountName(purchaseAccount.getAccountName());
            productPositionMapper.insert(position);
        }

        //增加用户的产品持仓
        productPositionMapper.addPosition(userId, productId, buyVol);

        //增加交易记录
        RegularTrade regularTrade = new RegularTrade();
        regularTrade.setProductId(productId);
        regularTrade.setProductName(productName);
        regularTrade.setBussType(TransConst.BussType.buy);
        BigDecimal incomeRate = regularProduct.getIncomeRate();
        regularTrade.setExpectIncome(buyVol.multiply(incomeRate));
        regularTrade.setFundAccount(purchaseAccount.getUserAccount());
        regularTrade.setSerialNo(idService.generateTransNo());
        regularTrade.setChargeAmount(new BigDecimal("0"));

        DateTime now = DateTime.now();
        DateTime rateDate = now.plusDays(1);//下一日为起息日
        Integer expdates = regularProduct.getProductExpdate();
        DateTime termDate = rateDate.plusDays(expdates);

        regularTrade.setRateDate(rateDate.toDate());//起息日
        regularTrade.setTermDate(termDate.toDate());//到期日
        regularTrade.setTransNum(buyVol);
        regularTrade.setTransStatus(TransConst.TransStatus.success);
        regularTrade.setTransTime(new Date());
        regularTrade.setCreateTime(new Date());
        regularTrade.setUpdateTime(new Date());
        regularTrade.setUserId(userId);
        regularTrade.setUserName(userName);
        regularTrade.setTransUnit(regularProduct.getPayUnit());

        regularTradeMapper.insert(regularTrade);

        fundGatherService.updateFundGather(userId);
    }

    @Override
    public void redeem() {
        //TODO
    }
}
