package com.jinhui.common.mapper;


import com.jinhui.common.entity.po.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    int insertSelective(User record);


    int updateByUserId(User record);

    int closeAssetsMgt(User record);


    User selectBySective(User user);
}