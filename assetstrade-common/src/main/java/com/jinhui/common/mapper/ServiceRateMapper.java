package com.jinhui.common.mapper;


import com.jinhui.common.entity.po.ServiceRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ServiceRateMapper {

    //根据费率类型查费率值
    ServiceRate selectByRateType(@Param("rateType") String rateType);

}