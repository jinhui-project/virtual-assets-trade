package com.jinhui.api.mapper;

import com.jinhui.api.entity.po.RegularProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegularProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RegularProduct record);

    int insertSelective(RegularProduct record);

    RegularProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegularProduct record);

    int updateByPrimaryKey(RegularProduct record);


    List<RegularProduct> selectAll();

    RegularProduct selectByName(@Param("productName") String  productName);


    RegularProduct selectById(@Param("productId") String  productId);
}