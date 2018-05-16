package com.jinhui.common.service.account.impl;

import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.Account;
import com.jinhui.common.mapper.AccountMapper;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.id.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27 0027.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IdService idService;


    @Override
    public boolean isExist(String userId, AcctInfo acctInfo) {

        Account account = accountMapper.selectByUserIdAndType(userId, acctInfo.getCode());
        if (account == null) {
            return false;
        }
        return true;
    }

    @Override
    public Account queryAccount(String userId, AcctInfo acctInfo) {

        Account account = accountMapper.selectByUserIdAndType(userId, acctInfo.getCode());
        if (account == null) {
            throw new RuntimeException("用户" + userId + "的" + acctInfo.getName() + "不存在");
        }

        return account;
    }

    @Override
    public Account addAccount(String userId, String userName, AcctInfo acctInfo) {
        Account sellAccount = new Account();
        sellAccount.setAccountType(acctInfo.getCode());
        sellAccount.setCreateTime(new Date());
        sellAccount.setUserId(userId);
        sellAccount.setAccountName(acctInfo.getName());
        sellAccount.setUserAccount(idService.generateAccountNo(acctInfo.getType()));
        sellAccount.setUserName(userName);
        sellAccount.setPositionVol(new BigDecimal("0"));
        accountMapper.insert(sellAccount);
        return queryAccount(userId, acctInfo);
    }

    @Override
    public boolean subtractPosition(String userAccount, BigDecimal amount) {

        int i = accountMapper.subtractPosition(userAccount, amount);
        if (i == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addPosition(String userAccount, BigDecimal amount) {
        int i = accountMapper.addPosition(userAccount, amount);
        if (i == 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<Account> queryAllByUserId(String userId) {

        return accountMapper.selectAllByUserId(userId);
    }


}
