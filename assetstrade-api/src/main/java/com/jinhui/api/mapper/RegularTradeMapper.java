package com.jinhui.api.mapper;

import com.jinhui.api.entity.po.RegularTrade;
import com.jinhui.api.entity.vo.TransQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegularTradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RegularTrade record);

    int insertSelective(RegularTrade record);

    RegularTrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegularTrade record);

    int updateByPrimaryKey(RegularTrade record);


    List<RegularTrade> selectByQueryParam(TransQueryParam queryParam);

    /**
     * 查询用户成功的交易记录
     * @return
     */
    List<RegularTrade> selectByUserId(@Param("userId") String userId);
}