package com.jinhui.web.controller;

import com.esms.common.entity.GsmsResponse;
import com.github.pagehelper.PageInfo;
import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.constants.MarketAcctEnum;
import com.jinhui.common.constants.ServiceRateConst;
import com.jinhui.common.entity.po.*;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.account.MarketServcie;
import com.jinhui.common.service.account.PlatformAccountService;
import com.jinhui.common.service.account.ServiceRateService;
import com.jinhui.common.service.id.IdService;
import com.jinhui.common.service.transfer.FundTransferService;
import com.jinhui.common.utils.RedisUtils;
import com.jinhui.common.utils.ResultResp;
import com.jinhui.web.common.FundTransferEnum;
import com.jinhui.web.controller.vo.*;
import com.jinhui.web.utils.SMSender;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 充值，提现控制器
 *
 * @autor wsc
 * @create 2018-03-27 11:11
 **/
@Controller
@RequestMapping("/account")
public class AccountController {
    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Value("${accountName}")
    private String	accountName;
    @Value("${password}")
    private String	password;
    @Value("${gateWayIP}")
    private String gateWayIP;
    @Value("${gateWayPort}")
    private int gateWayPort;
    @Autowired
    private FundTransferService fundTransferService;
    @Autowired
    private IdService idService;
    @Autowired
    private PlatformAccountService platformAccountService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ServiceRateService serviceRateService;
    @Autowired
    private MarketServcie marketServcie;


    @ApiOperation(value="充币申请")
    @PostMapping(value="/recharge")
    @ResponseBody
    public ResultResp recharge(@RequestBody RechargeVo rechargeVo,HttpServletRequest req) throws Exception {
        logger.info("-----------------------  充币  -----------------------");
        User user = RedisUtils.getRedisUser(req);
        if(user != null) {
            PlatformAccount platformAccount = platformAccountService.queryAccountByType(rechargeVo.getAccountType());
            if(platformAccount == null){
                return ResultResp.fail("该币种不存在!");
            }
            logger.info("充值币种：" + platformAccount.toString());
            if(new BigDecimal(rechargeVo.getRechargeAmount()).compareTo(platformAccount.getMinRecharge()) < 0){
                return ResultResp.fail("充币数量小于最小可充单位!" );
            }else if(!new Sha256Hash(rechargeVo.getTradePwd(), FundTransferEnum.PWD_SALT).toHex().equals(user.getTradePwd())){
                return ResultResp.fail("交易密码错误!");
            }else{
                FundTransfer fundTransfer = new FundTransfer();
                fundTransfer.setSerialNo(idService.generateTransNo());
                fundTransfer.setUserId(user.getUserId());
                fundTransfer.setUserName(user.getUserName());
                fundTransfer.setAccountType(rechargeVo.getAccountType());

                fundTransfer.setTransferInAccount(platformAccount.getAccountAddr());
                fundTransfer.setTransferOutAccount(rechargeVo.getTransferOutAccount());
                //充值数字货币
                boolean flag = accountService.isExist(user.getUserId(), AcctInfo.byCode(rechargeVo.getAccountType()));
                if(!flag){
                    //新增币账户
                    accountService.addAccount(user.getUserId(),user.getUserName(),AcctInfo.byCode(rechargeVo.getAccountType()));
                }
                fundTransfer.setBussType(FundTransferEnum.BUSS_TYPE_RECHARGE_COIN);
                fundTransfer.setTransNum(new BigDecimal(rechargeVo.getRechargeAmount()));
                Market market = marketServcie.selectByProductName(MarketAcctEnum.byAcctCode(rechargeVo.getAccountType()).getProductName());
                if(market == null){
                    return ResultResp.fail("该币种的价格不存在!");
                }else{
                    logger.info(market.getProductName() + " 当前价格: " + market.getLastPrice());
                }
                fundTransfer.setTransPrice(market.getLastPrice()); //todo
                fundTransfer.setTransAmount(fundTransfer.getTransNum().multiply(fundTransfer.getTransPrice()));
                fundTransfer.setChargeAmount(BigDecimal.ZERO);
                fundTransfer.setTransStatus(FundTransferEnum.TRANS_STATUS_HANDING);
                fundTransferService.recharge(fundTransfer);
                return ResultResp.success("充币申请成功!");
            }
        }else{
            return ResultResp.fail("未登录!");
        }
    }

    @ApiOperation(value="提币申请")
    @PostMapping(value="/withdraw")
    @ResponseBody
    public ResultResp withdraw(@RequestBody WithdrawVo withdrawVo,HttpServletRequest req){
        logger.info("-----------------------  提币  -----------------------");
        User user = RedisUtils.getRedisUser(req);
        if(user != null) {
            logger.info("session: "+user.toString());
            String smsCode = RedisUtils.get(user.getPhone() + "withdraw");
            logger.info("缓存的短信验证码为：" + smsCode);
            PlatformAccount platformAccount = platformAccountService.queryAccountByType(withdrawVo.getAccountType());
            logger.info("提现币种：" + platformAccount.toString());
            Account account = accountService.queryAccount(user.getUserId(),AcctInfo.byCode(withdrawVo.getAccountType()));
            logger.info("客户持仓：" + account.getPositionVol());
            if(!withdrawVo.getSmsCode().equals(smsCode) || smsCode == null){
                return ResultResp.fail("短信验证码不正确!");
            }else if(new BigDecimal(withdrawVo.getWithdrawAmount()).compareTo(platformAccount.getMinWithdraw()) < 0){
                return ResultResp.fail("提现金额小于最小可提单位!" );
            }else if(new BigDecimal(withdrawVo.getWithdrawAmount()).compareTo(platformAccount.getMaxWithdraw()) > 0){
                return ResultResp.fail("提现金额大于单笔提现限额!" );
            }else if(new BigDecimal(withdrawVo.getWithdrawAmount()).compareTo(account.getPositionVol()) > 0){
                return ResultResp.fail("提现金额大于客户持仓!" );
            }else if(!new Sha256Hash(withdrawVo.getTradePwd(), FundTransferEnum.PWD_SALT).toHex().equals(user.getTradePwd())){
                return ResultResp.fail("交易密码错误!" );
            }else{
                FundTransfer fundTransfer = new FundTransfer();
                fundTransfer.setSerialNo(idService.generateTransNo());
                fundTransfer.setUserId(user.getUserId());
                fundTransfer.setUserName(user.getUserName());
                fundTransfer.setAccountType(withdrawVo.getAccountType());
                fundTransfer.setTransferInAccount(withdrawVo.getTransferInAccount());
                fundTransfer.setTransferOutAccount(platformAccount.getAccountAddr());
                //提币
                fundTransfer.setBussType(FundTransferEnum.BUSS_TYPE_WITHDRAW_COIN);
                fundTransfer.setTransNum(new BigDecimal(withdrawVo.getWithdrawAmount()));
                Market market = marketServcie.selectByProductName(MarketAcctEnum.byAcctCode(withdrawVo.getAccountType()).getProductName());
                logger.info(market.getProductName() + " 当前价格: " + market.getLastPrice());
                fundTransfer.setTransPrice(market.getLastPrice()); //todo
                fundTransfer.setTransAmount(fundTransfer.getTransNum().multiply(fundTransfer.getTransPrice()));
                BigDecimal rate = serviceRateService.queryRateByType(ServiceRateConst.提币费率).getRate();
                fundTransfer.setChargeAmount(new BigDecimal(withdrawVo.getWithdrawAmount()).multiply(rate));

                fundTransfer.setTransStatus(FundTransferEnum.TRANS_STATUS_HANDING);
                fundTransferService.recharge(fundTransfer);
                return ResultResp.success("提币申请成功!");
            }
        }else{
                return ResultResp.fail("未登录!");
            }
        }

    @ApiOperation(value="提币手续费")
    @PostMapping(value="/withdrawFee")
    @ResponseBody
    public ResultResp withdrawFee(@RequestBody WithdrawFeeVo withdrawFeeVo,HttpServletRequest req){
        logger.info("-----------------------  提币手续费  -----------------------");
        User user = RedisUtils.getRedisUser(req);
        BigDecimal fee = null;
        if(user != null) {
            logger.info("session: "+user.toString());
            //提币
                BigDecimal rate = serviceRateService.queryRateByType(ServiceRateConst.提币费率).getRate();
                fee = new BigDecimal(withdrawFeeVo.getWithdrawAmount()).multiply(rate);
            return ResultResp.successData(fee);
        }else{
            return ResultResp.fail("未登录!");
        }
    }

    @ApiOperation(value="可提额度")
    @PostMapping(value="/canWithdrawAmount")
    @ResponseBody
    public ResultResp canWithdrawAmount(@RequestBody WithdrawAmountVo withdrawAmountVo, HttpServletRequest req){
        logger.info("-----------------------  可提额度  -----------------------");
        User user = RedisUtils.getRedisUser(req);
        if(user != null) {
            logger.info("session: "+user.toString());
            Account account = accountService.queryAccount(user.getUserId(),AcctInfo.byCode(withdrawAmountVo.getAccountType()));
            return ResultResp.successData(account.getPositionVol());
        }else{
            return ResultResp.fail("未登录!");
        }
    }

    @ApiOperation(value="提币发送验证码")
    @PostMapping(value="/sendSmsCode")
    @ResponseBody
    public ResultResp sendSmsCode(HttpServletRequest req) {
        User user = RedisUtils.getRedisUser(req);
        if (user != null) {
            logger.info("session: " + user.toString());
            int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
            String content = new String("您正在执行提现操作,验证码为: " + mobile_code + " 有效时间3分钟,请勿将其提供予其他人。");
            SMSender smSender = null;
            GsmsResponse respMsg = null;
            try {
                smSender = new SMSender(accountName, password, gateWayIP, gateWayPort);
                respMsg = smSender.doSendSms(user.getPhone(), content);
                if (respMsg.getResult() == 0) {
                    logger.info("手机号为：【" + user.getPhone() + "】" + "验证码为：" + mobile_code);
                    //将手机验证码放入缓存，有效期3分钟
                    RedisUtils.set(user.getPhone() + "withdraw", mobile_code, 180);
                    return ResultResp.success("短信验证码发送成功!");
                } else {
                    return ResultResp.fail("短信验证码发送失败!");
                }
            } catch (Exception e) {
                return ResultResp.fail(e.getMessage());
            }
        } else {
            return ResultResp.fail("未登录!");
        }
    }

    @ApiOperation(value="账户资金明细")
    @PostMapping(value="/queryAssetsList")
    @ResponseBody
    public ResultResp queryAssetsList(@RequestBody FundTransferVo fundTransferVo, HttpServletRequest req) {
        User user = RedisUtils.getRedisUser(req);
        if (user != null) {
            logger.info("session: " + user.toString());
            PageInfo<FundTransfer> list = fundTransferService
                                            .queryListBySearch(fundTransferVo.getStartDate(),
                                                               fundTransferVo.getAccountType(),
                                                     fundTransferVo.getPageNum(),
                                                    fundTransferVo.getPageSize());
            return ResultResp.successData(list);
        } else {
            return ResultResp.fail("未登录!");
        }
    }

}
