package com.jinhui.common.service.user.impl;

import com.jinhui.common.entity.po.ChangeAgentLog;
import com.jinhui.common.entity.po.User;
import com.jinhui.common.mapper.ChangeAgentLogMapper;
import com.jinhui.common.mapper.UserMapper;
import com.jinhui.common.service.user.UserService;
import com.jinhui.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @autor wsc
 * @create 2018-03-22 15:45
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChangeAgentLogMapper changeAgentLogMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updatUser(User user) {
        return userMapper.updateByUserId(user);
    }

    @Override
    public int closeAssetsMgt(User user) {

        //记录绑定经纪人日志
        ChangeAgentLog changeAgentLog = new ChangeAgentLog();
        changeAgentLog.setUserId(user.getUserId());
        changeAgentLog.setUserName(user.getUserName());
        changeAgentLog.setType("0");
        changeAgentLog.setOperateId(user.getUserId());
        changeAgentLogMapper.insertSelective(changeAgentLog);

        return userMapper.closeAssetsMgt(user);
    }

    @Override
    public User queryUserByLogin(User user) {
        return userMapper.selectBySective(user);
    }
}
