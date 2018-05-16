package com.jinhui.common.service.account;

import com.jinhui.common.constants.ServiceRateConst;
import com.jinhui.common.entity.po.ServiceRate;

/**
 * 手续费率
 *
 * @autor wsc
 * @create 2018-03-28 11:08
 **/
public interface ServiceRateService {

    //根据费率类型查手续费率
    ServiceRate queryRateByType(ServiceRateConst serviceRateConst);
}
