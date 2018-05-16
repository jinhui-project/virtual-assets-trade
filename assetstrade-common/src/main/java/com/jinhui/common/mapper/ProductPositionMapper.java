package com.jinhui.common.mapper;

import com.jinhui.common.entity.po.ProductPosition;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface ProductPositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPosition record);

    int insertSelective(ProductPosition record);

    ProductPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPosition record);

    int updateByPrimaryKey(ProductPosition record);


    ProductPosition selectByUserIdAndProductId(@Param("userId") String userId, @Param("productId") String userAccount);

    /**
     * 减去用户关于某个产品的持仓
     */
    int subtractPosition(@Param("userId") String userId, @Param("productId") String productId, @Param("amount") BigDecimal amount);


    /**
     * 增加用户关于某个产品的持仓
     */
    int addPosition(@Param("userId") String userId, @Param("productId") String productId, @Param("amount") BigDecimal amount);


    /**
     * 查询用户关于某类产品的总持仓
     */
    BigDecimal selectTotalPosition(@Param("userId") String userId, @Param("accountName") String accountName);

}