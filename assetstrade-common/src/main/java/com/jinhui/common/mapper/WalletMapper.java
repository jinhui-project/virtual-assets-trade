package com.jinhui.common.mapper;

import com.jinhui.common.entity.po.Wallet;

import java.util.List;

public interface WalletMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

    /**
     * 查询所有钱包信息
     */
    List<Wallet> selectAll();

    /**
     * 根据type和地址更新余额
     */

    int updateBalance(Wallet record);
}