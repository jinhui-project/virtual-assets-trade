package com.jinhui.common.service.user;


import com.jinhui.common.entity.po.User;

/**
 * 测试
 *
 * @autor wsc
 * @create 2018-03-22 15:44
 **/
public interface UserService {

    int addUser(User user);

    int updatUser(User user);

    int closeAssetsMgt(User user);

    User queryUserByLogin(User user);
}
