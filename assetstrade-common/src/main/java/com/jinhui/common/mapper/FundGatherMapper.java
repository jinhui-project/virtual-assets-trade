package com.jinhui.common.mapper;

import com.jinhui.common.entity.po.FundGather;
import org.apache.ibatis.annotations.Param;

public interface FundGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundGather record);

    int insertSelective(FundGather record);

    FundGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundGather record);

    int updateByPrimaryKey(FundGather record);

    int updateByUserId(FundGather record);

    FundGather selectByUserId(@Param("userId") String  userId);
}