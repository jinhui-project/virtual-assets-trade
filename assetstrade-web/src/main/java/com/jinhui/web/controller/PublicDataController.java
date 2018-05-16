package com.jinhui.web.controller;

import com.jinhui.common.entity.po.Account;
import com.jinhui.common.entity.po.PlatformAccount;
import com.jinhui.common.entity.po.User;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.account.PlatformAccountService;
import com.jinhui.common.service.user.UserService;
import com.jinhui.common.utils.RedisUtils;
import com.jinhui.common.utils.ResultResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公共数据控制层
 *
 * @autor wsc
 * @create 2018-03-26 17:44
 **/
@Controller
@RequestMapping("/public")
public class PublicDataController {
    private final static Logger logger = LoggerFactory.getLogger(PublicDataController.class);

    @Autowired
    private PlatformAccountService platformAccountService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;


    @ApiOperation(value="获取可充值的币种列表")
    @GetMapping(value="/queryRechargeCoins")
    @ResponseBody
    public ResultResp queryRechargeCoins(){
        List<PlatformAccount> list = platformAccountService.queryAllAccount();
        return ResultResp.successData(list);
    }

    @ApiOperation(value="获取某个币种的平台地址和提现限额")
    @GetMapping(value="/queryCoinAddress")
    @ResponseBody
    public ResultResp queryCoinTypeAdds(String accountType){
        PlatformAccount platformAccount = platformAccountService.queryAccountByType(accountType);
        return ResultResp.successData(platformAccount);
    }

    @ApiOperation(value="获取用户可提现的币种列表")
    @GetMapping(value="/queryWithdrawCoins")
    @ResponseBody
    public ResultResp queryWithdrawCoins(HttpServletRequest req){
        User user = RedisUtils.getRedisUser(req);
        if(user != null){
            logger.info("session: " + user.toString());
           List<Account> accountList = accountService.queryAllByUserId(user.getUserId());
           return ResultResp.successData(accountList);
        }else{
            return ResultResp.fail("未登录!");
        }
    }

   /* @ApiOperation(value="测试")
    @GetMapping(value="/test")
    @ResponseBody
    public ResultResp test(){
        int a = platformAccountService.subtractPosition(MarketAcctEnum.BTC_CNY,new BigDecimal("11"));
        int b = platformAccountService.addPosition(MarketAcctEnum.ETH_CNY,new BigDecimal("11"));
        return ResultResp.success("");
    }*/


}
