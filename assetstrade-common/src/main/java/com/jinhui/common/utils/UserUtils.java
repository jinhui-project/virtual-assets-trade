package com.jinhui.common.utils;


import com.jinhui.common.entity.po.User;
import com.jinhui.common.exception.BizException;


/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class UserUtils {


    public static String getUserId(){

        return getUser().getUserId();
    }


    public static User getUser() {

        User user = RedisUtils.getLocalUser();

        if(null==user||user.equals("")){
            throw new BizException("用户未登录，获取不到用户信息");
        }
        return user;
    }



}
