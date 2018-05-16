package com.jinhui.common.service.account;


import com.jinhui.common.constants.MarketAcctEnum;
import com.jinhui.common.entity.po.PlatformAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 平台账户服务层
 *
 * @autor wsc
 * @create 2018-03-26 17:35
 **/
public interface PlatformAccountService {
    //查询平台发布的所有币种
    List<PlatformAccount> queryAllAccount();

    //根据币种类型查询币种地址，可提现额度，可充值额度
    PlatformAccount queryAccountByType(String accountType);

    //减少币种的平台持仓
    int subtractPosition(MarketAcctEnum marketAcctEnum, BigDecimal amount);

    //增加币种的平台持仓
    int addPosition(MarketAcctEnum marketAcctEnum, BigDecimal amount);

    //增加币种的收入账户的持仓
    int addCharge(String  AcctType, BigDecimal amount);

    //增加币种的平台持仓
    int addPosition(String  AcctType, BigDecimal amount);
}
