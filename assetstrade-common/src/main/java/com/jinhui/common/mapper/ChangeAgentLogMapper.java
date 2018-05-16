package com.jinhui.common.mapper;


import com.jinhui.common.entity.po.ChangeAgentLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ChangeAgentLogMapper {


    int insertSelective(ChangeAgentLog record);

}