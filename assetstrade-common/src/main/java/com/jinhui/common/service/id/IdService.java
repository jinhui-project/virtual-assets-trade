package com.jinhui.common.service.id;

/**
 * 提供ID生成服务
 */
public interface IdService {

    /**
     * 生成账户号
     * @return
     */
    String generateAccountNo(String prefix);


    /**
     * 生成交易流水
     */
    String generateTransNo();

    /**
     * 生成用户ID
     * @return
     */
    String generateUserId();



}
