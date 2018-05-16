package com.jinhui.common.mapper;


import com.jinhui.common.entity.po.FundTransfer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface FundTransferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FundTransfer record);

    int insertSelective(FundTransfer record);

    FundTransfer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundTransfer record);

    int updateByPrimaryKey(FundTransfer record);

    /**
     * 根据条件查询账户资金明细
     *
     * @param startDate
     * @param accountType 资金类型
     * @return
     */
    List<FundTransfer> queryListBySearch(@Param("startDate") String startDate,
                                         @Param("accountType") String accountType, @Param("userId") String userId);

    /**
     * 按交易状态和业务类型统计用户的交易总金额
     */
    BigDecimal statistics(@Param("bussType") String bussType, @Param("transStatus") String transStatus, @Param("userId") String userId);



    List<FundTransfer> selectAll();

    List<FundTransfer> selectByActType(@Param("accountType") String accountType, @Param("transStatus") String transStatus);

}