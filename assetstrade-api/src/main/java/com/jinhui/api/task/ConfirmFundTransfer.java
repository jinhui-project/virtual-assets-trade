package com.jinhui.api.task;

import com.jinhui.api.constants.TransConst;
import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.FundTransfer;
import com.jinhui.common.entity.po.Wallet;
import com.jinhui.common.entity.po.WalletTx;
import com.jinhui.common.mapper.FundTransferMapper;
import com.jinhui.common.mapper.WalletMapper;
import com.jinhui.common.mapper.WalletTxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

@Component
public class ConfirmFundTransfer {


    @Autowired
    private WalletTxMapper walletTxMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private FundTransferMapper fundTransferMapper;


    private final static BigDecimal wei18 = new BigDecimal("1000000000000000000");


    /**
     * 确认用户的充值，提现记录
     */
    public void confirmFundTransfer() {

        List<Wallet> wallets = walletMapper.selectAll();


        for (Wallet wallet : wallets) {

            if (wallet.getType().equals("eth")) {

                //按创建时间顺序依次确认
                List<FundTransfer> fundTransfers = fundTransferMapper.selectByActType(AcctInfo.eth.getCode(), "0");
                System.out.println(fundTransfers);
                //对转入的记录进行确认
                fundTransfers.stream().filter(f -> f.getBussType().equals("02"))
                        .forEach(i -> {
                                    String transferInAccount = i.getTransferOutAccount();
                                    BigDecimal transNum = i.getTransNum().multiply(wei18);
                                    System.out.println("transferInAccount：" + transferInAccount);
                                    System.out.println("transNum：" + transNum);

                                    WalletTx eth = walletTxMapper.selectInputTx(transferInAccount, transNum, "eth");
                                    if (eth != null) {
                                        //更新
                                        i.setTransStatus(TransConst.TransStatus.success);
                                        fundTransferMapper.updateByPrimaryKey(i);

                                        eth.setState("1");
                                        walletTxMapper.updateByPrimaryKey(eth);
                                    }
                                }
                        );


                //对转出的记录进行确认
                fundTransfers.stream().filter(f -> f.getBussType().equals("03"))
                        .forEach(i -> {
                                    String transferOutAccount = i.getTransferInAccount();
                                    BigDecimal transNum = i.getTransNum().multiply(wei18);
                                    WalletTx eth = walletTxMapper.selectOutTx(transferOutAccount, transNum, "eth");
                                    if (eth != null) {
                                        //更新
                                        i.setTransStatus(TransConst.TransStatus.success);
                                        fundTransferMapper.updateByPrimaryKey(i);

                                        System.out.println();
                                        eth.setState("1");
                                        walletTxMapper.updateByPrimaryKey(eth);
                                    }
                                }
                        );

            }
        }


    }


}
