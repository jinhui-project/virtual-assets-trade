package com.jinhui.common.service.transfer.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.common.entity.po.FundTransfer;
import com.jinhui.common.entity.po.User;
import com.jinhui.common.mapper.FundTransferMapper;
import com.jinhui.common.service.transfer.FundTransferService;
import com.jinhui.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 充值提现服务层实现
 *
 * @autor wsc
 * @create 2018-03-27 11:06
 **/
@Service("fundTransferService")
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private FundTransferMapper fundTransferMapper;

    public int recharge(FundTransfer fundTransfer) {
        return fundTransferMapper.insertSelective(fundTransfer);
    }

    public int withdraw(FundTransfer fundTransfer) {
        return fundTransferMapper.insertSelective(fundTransfer);
    }

    @Override
    public PageInfo<FundTransfer> queryListBySearch(String startDate, String accountType, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        User user = RedisUtils.getLocalUser();
        List<FundTransfer> list = fundTransferMapper.queryListBySearch(startDate,accountType,user.getUserId());

        return new PageInfo<>(list);
    }
}
