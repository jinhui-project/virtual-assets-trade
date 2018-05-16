package com.jinhui.common.mapper;


import com.jinhui.common.entity.po.PlatformAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface PlatformAccountMapper {

    //根据币种类型查询币种地址，可提现额度，可充值额度
    PlatformAccount selectByType(@Param("accountType") String accountType);

    //查询平台发布的所有币种
    List<PlatformAccount> selectAllAccount();

    //减少币种的平台持仓
    int subtractPosition(@Param("accountType") String accountType, @Param("amount") BigDecimal amount);

    //增加币种的平台持仓
    int addPosition(@Param("accountType") String accountType, @Param("amount") BigDecimal amount);

}