package com.jinhui.common.service.id.impl;

import com.jinhui.common.mapper.IdSequenceMapper;
import com.jinhui.common.service.id.IdService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinhui on 2017/5/25.
 */
@Service
public class IdServiceImpl implements IdService {

    @Autowired
    private IdSequenceMapper idSequenceMapper;


    @Override
    public String generateAccountNo(String prefix) {

        long seq = getSeq(prefix);
        return prefix + String.format("%07d", seq);//不足7位补零

    }

    @Override
    public String generateTransNo() {
        long seq = getSeq("trans");
        String yyyyMMdd = DateTime.now().toString("yyyyMMdd");
        return yyyyMMdd + String.format("%010d", seq);//不足10位补零

    }

    @Override
    public String generateUserId() {
        long seq = getSeq("user");
        return String.format("%07d", seq);//不足7位补零
    }


    /**
     * 根据key递增生成seq
     */
    private synchronized long getSeq(final String key) {

        idSequenceMapper.insertOrUpdateByKey(key);

        return idSequenceMapper.querySeqByKey(key);

    }


}
