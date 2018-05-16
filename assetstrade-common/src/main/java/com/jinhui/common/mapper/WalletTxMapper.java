package com.jinhui.common.mapper;

import com.jinhui.common.entity.po.WalletTx;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface WalletTxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WalletTx record);

    int insertSelective(WalletTx record);

    WalletTx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WalletTx record);

    int updateByPrimaryKey(WalletTx record);


    int selectByHashCount(@Param("hash") String hash);

    /**
     * 根据转入地址，转入金额，钱包类型 查询转入类型的钱包交易
     */
    WalletTx selectInputTx(@Param("fromAddress") String fromAddress, @Param("inputAmount") BigDecimal inputAmount, @Param("type") String type);

    /**
     * 根据转出地址，转出金额，钱包类型 查询转出的的钱包交易
     */
    WalletTx selectOutTx(@Param("toAddress") String toAddress, @Param("outAmount") BigDecimal outAmount, @Param("type") String type);

}