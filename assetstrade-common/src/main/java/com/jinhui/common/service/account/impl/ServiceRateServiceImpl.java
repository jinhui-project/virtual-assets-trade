package com.jinhui.common.service.account.impl;

import com.jinhui.common.constants.ServiceRateConst;
import com.jinhui.common.entity.po.ServiceRate;
import com.jinhui.common.mapper.ServiceRateMapper;
import com.jinhui.common.service.account.ServiceRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 手续费率实现
 *
 * @autor wsc
 * @create 2018-03-28 11:09
 **/
@Service("serviceRateService")
public class ServiceRateServiceImpl implements ServiceRateService {

    @Autowired
    private ServiceRateMapper serviceRateMapper;

    @Override
    public ServiceRate queryRateByType(ServiceRateConst serviceRateConst) {
        return serviceRateMapper.selectByRateType(serviceRateConst.getRateType());
    }
}
