package com.jinhui.common.mapper;

import com.jinhui.common.entity.po.Market;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Market record);

    int insertSelective(Market record);

    Market selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);

    Market selectByProductId(@Param("productId") String productId);

    Market selectByProductName(@Param("productName") String productName);

    List<Market> selectAll();


    /**
     * 根据卖出货币和买入货币查询对应的行情
     *
     */
    Market selectByBuyPayAndSellPay(@Param("buyPayUnit") String buyPayUnit,@Param("sellPayUnit") String sellPayUnit);


}