package com.jinhui.common.mapper;

import com.jinhui.common.entity.po.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 根据用户id和账户类别查询
     */
    Account selectByUserIdAndType(@Param("userId") String userId, @Param("accountType") String accountType);


    /**
     * 减去用户关于某个账号的持仓
     */
    int subtractPosition(@Param("userAccount") String userAccount,  @Param("amount") BigDecimal amount);


    /**
     * 增加用户关于某个账号的持仓
     */
    int addPosition(@Param("userAccount") String userAccount,@Param("amount") BigDecimal amount);

    /**
     * 查询所有的账户
     */
    List<Account> selectAllByUserId(@Param("userId") String userId);

}