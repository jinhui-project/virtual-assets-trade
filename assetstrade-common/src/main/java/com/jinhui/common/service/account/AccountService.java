package com.jinhui.common.service.account;

import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27 0027.
 */
@Component
public interface AccountService {

    /**
     * 根据用户名和账户类别查询对应的账号是否存在, false是不存在,true是已存在
     */
    boolean isExist(String userId, AcctInfo acctInfo);

    /**
     * 根据用户名和账户类别查询对应的账号
     */
    Account queryAccount(String userId, AcctInfo acctInfo);

    /**
     * 新增账号
     */
    Account addAccount(String userId, String userName, AcctInfo acctInfo);


    /**
     * 根据账户号减去账号的份额,true为成功，false为失败
     */
    boolean subtractPosition(String userAccount, BigDecimal amount);

    /**
     * 根据账户号增加账号的份额,true为成功，false为失败
     */
    boolean addPosition(String userAccount, BigDecimal amount);

    /**
     * 根据userid查询该用户所有的账户
     */
    List<Account> queryAllByUserId(String userId);

}
