package com.jinhui.api.mapper;

import com.jinhui.api.entity.po.Trade;
import com.jinhui.api.entity.vo.TransQueryParam;

import java.util.List;

public interface TradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);


    List<Trade> selectByTransTimeAndProduct(TransQueryParam queryParam);
}