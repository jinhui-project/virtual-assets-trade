package com.jinhui.web.controller;

import com.esms.common.entity.GsmsResponse;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jinhui.common.constants.AcctInfo;
import com.jinhui.common.entity.po.Account;
import com.jinhui.common.entity.po.User;
import com.jinhui.common.service.account.FundGatherService;
import com.jinhui.common.service.account.AccountService;
import com.jinhui.common.service.id.IdService;
import com.jinhui.common.service.user.UserService;
import com.jinhui.common.utils.RedisUtils;
import com.jinhui.common.utils.ResultResp;
import com.jinhui.web.common.FundTransferEnum;
import com.jinhui.web.controller.vo.*;
import com.jinhui.web.utils.JwtToken;
import com.jinhui.web.utils.SMSender;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @autor wsc
 * @create 2018-03-22 15:47
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${accountName}")
    private String	accountName;
    @Value("${password}")
    private String	password;
    @Value("${gateWayIP}")
    private String gateWayIP;
    @Value("${gateWayPort}")
    private int gateWayPort;
    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    private UserService userService;
    @Autowired
    private IdService idService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private FundGatherService fundGatherService;


    @ApiOperation(value="获取图片验证码")
    @GetMapping(value="/sendImageCode")
    @ResponseBody
    public ResultResp sendImageCode(String t,HttpServletResponse resp) throws IOException {
        if(!"".equals(t) && t != null){
            byte[] captchaChallengeAsJpeg = null;
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            try {
                //生产验证码字符串并保存到session中
                String createText = captchaProducer.createText();
                RedisUtils.set("imageCode"+t, createText,180);
                logger.info("图片验证码： "+createText);
                //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
                BufferedImage challenge = captchaProducer.createImage(createText);
                ImageIO.write(challenge, "jpg", jpegOutputStream);
            } catch (IllegalArgumentException e) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return ResultResp.fail();
            }

            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            resp.setHeader("Cache-Control", "no-store");
            resp.setHeader("Pragma", "no-cache");
            resp.setDateHeader("Expires", 0);
            resp.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream =
                    resp.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        }else{
            return ResultResp.fail("参数不正确!");
        }

        return ResultResp.success();
    }

    @ApiOperation(value="获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendSmsCode", value = "发送短信验证码vo", required = true, dataType = "SendSmsCode"),
    })
    @PostMapping(value="/sendSmsCode")
    @ResponseBody
    public ResultResp sendSmsCode(@RequestBody SendSmsCode sendSmsCode) throws IOException {
        String cache_imageCode = RedisUtils.get("imageCode"+ sendSmsCode.getT());
          if(StringUtils.isEmpty(cache_imageCode) || StringUtils.isBlank(cache_imageCode)){
              return ResultResp.failData("图片验证码已失效，请重新获取!");
          }else if(StringUtils.isEmpty(sendSmsCode.getMobileNo()) || StringUtils.isBlank(sendSmsCode.getMobileNo())){
              return ResultResp.failData("手机号码不能为空!");
          }else if(StringUtils.isEmpty(sendSmsCode.getImageCode()) || StringUtils.isBlank(sendSmsCode.getImageCode()) || !sendSmsCode.getImageCode().equalsIgnoreCase(cache_imageCode)){
               return ResultResp.failData("图片验证码不正确!");
          }else{
              int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
              String content = new String("您正在注册资产管理平台,验证码为: " + mobile_code + " 有效时间3分钟,请勿将其提供予其他人。");
              GsmsResponse respMsg = null;
              SMSender smSender = null;
              try {
                  smSender = new SMSender(accountName,password,gateWayIP,gateWayPort);
                  respMsg = smSender.doSendSms(sendSmsCode.getMobileNo(), content);
                  if (respMsg.getResult() == 0) {
                      logger.info("手机号为：【"+ sendSmsCode.getMobileNo() +"】"+"验证码为："+mobile_code);
                      //将手机验证码放入缓存，有效期3分钟
                      RedisUtils.set(sendSmsCode.getMobileNo() + sendSmsCode.getT(),mobile_code,180);
                      return ResultResp.success("短信验证码发送成功!");
                  }else{
                      return ResultResp.fail("短信验证码发送失败!");
                  }
              } catch (Exception e) {
                  return ResultResp.fail(e.getMessage());
              }
          }
    }

    @ApiOperation(value="注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userRegist", value = "注册vo", required = true, dataType = "UserRegist"),
    })
    @PostMapping(value="/regist")
    @ResponseBody
    public ResultResp regist(@RequestBody UserRegist userRegist) throws Exception {
        String smsCode = RedisUtils.get(userRegist.getMobileNo() + userRegist.getT());
        String userId = "M99"+ idService.generateUserId();
        User user = new User();
        User user_in = new User();
        user.setPhone(userRegist.getMobileNo());
        User queryUser = userService.queryUserByLogin(user);
        if(queryUser != null) {
            return ResultResp.failData("该手机号码已注册!");
        }else if(StringUtils.isBlank(smsCode) || StringUtils.isEmpty(smsCode)){
            return ResultResp.failData("短信验证码已失效!");
        }else{
            if(StringUtils.isEmpty(userRegist.getMobileNo()) || StringUtils.isBlank(userRegist.getMobileNo())){
                return ResultResp.failData("手机号码不能为空!");
            }else if(StringUtils.isEmpty(userRegist.getLoginPwd()) || StringUtils.isBlank(userRegist.getLoginPwd())){
                return ResultResp.failData("账号密码不能为空!");
            }else if(!smsCode.equalsIgnoreCase(userRegist.getSmsCode())){
                return ResultResp.failData("短信验证码不正确!");
            }else if(!userRegist.getLoginPwd().equalsIgnoreCase(userRegist.getConfirmLoginPwd())){
                return ResultResp.failData("两次登录密码不一致!");
            }else{
                user_in.setUserId(userId);
                user_in.setPhone(userRegist.getMobileNo());
                user_in.setLoginPwd(new Sha256Hash(userRegist.getLoginPwd(), FundTransferEnum.PWD_SALT).toHex());
                userService.addUser(user_in);
                //将注册成功的用户ID放入缓存，缓存时间10分钟
                RedisUtils.set(userRegist.getMobileNo()+ "userId",userId,600);
                //创建现金账户
                boolean flag = accountService.isExist(userId, AcctInfo.cny);
                if(!flag){
                    //新增现金账户
                   // accountService.addAccount(userId,"",AcctInfo.cny);
                    //初始化账户资产信息
                    fundGatherService.initFundGather(userId);
                }
            }
        }
        //生成token
        String token = JwtToken.createToken(userId);
        //将用户数据保存在redis，同时保存在threadlocal,一小时有效期
        RedisUtils.setLocalUser(token,user_in,RedisUtils.ONE_HOUR_EXPIRE);
        ResultVo resultVo = new ResultVo();
        resultVo.setToken(token);
        resultVo.setImprove(false);
        return ResultResp.successData(resultVo);
    }

    @ApiOperation(value="完善客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "improveUserInfo", value = "完善客户信息vo", required = true, dataType = "ImproveUserInfo"),
    })
    @PostMapping(value="/improveUserInfo")
    @ResponseBody
    public ResultResp improveUserInfo(@RequestBody ImproveUserInfo improveUserInfo,HttpServletRequest req){
        String userId = RedisUtils.get(improveUserInfo.getMobileNo()+"userId");
        User user = new User();
         if(StringUtils.isBlank(userId) || StringUtils.isEmpty(userId)){
             return ResultResp.failData("缓存的客户信息已失效!");
         }else{
                 if (StringUtils.isEmpty(improveUserInfo.getUserName()) || StringUtils.isBlank(improveUserInfo.getUserName())) {
                     return ResultResp.failData("用户姓名不能为空!");
                 } else if (StringUtils.isEmpty(improveUserInfo.getIdNo()) || StringUtils.isBlank(improveUserInfo.getIdNo())) {
                     return ResultResp.failData("身份证号不能为空!");
                 } else if (StringUtils.isEmpty(improveUserInfo.getTradePwd()) || StringUtils.isBlank(improveUserInfo.getTradePwd())) {
                     return ResultResp.failData("交易密码不能为空!");
                 } else if (StringUtils.isEmpty(improveUserInfo.getConfirmTradePwd()) || StringUtils.isBlank(improveUserInfo.getConfirmTradePwd())) {
                     return ResultResp.failData("确认密码不能为空!");
                 }else if(!improveUserInfo.getTradePwd().equalsIgnoreCase(improveUserInfo.getConfirmTradePwd())){
                     return ResultResp.failData("两次交易密码不一致!");
                 }else{
                     user.setUserId(userId);
                     user.setUserName(improveUserInfo.getUserName());
                     user.setIdNo(improveUserInfo.getIdNo());
                     user.setIdType(improveUserInfo.getIdType());
                     user.setSex(improveUserInfo.getSex());
                     user.setProfession(improveUserInfo.getProfession());
                     user.setAddress(improveUserInfo.getAddress());
                     user.setTradePwd(new Sha256Hash(improveUserInfo.getTradePwd(), FundTransferEnum.PWD_SALT).toHex());
                     userService.updatUser(user);

                 }
         }

        String token = req.getHeader("token");
         logger.info(token);
         //完善信息之后更新redis中的数据
         if(token != null){
             RedisUtils.delete(token);
             RedisUtils.setLocalUser(token,user,RedisUtils.ONE_HOUR_EXPIRE);
         }
        ResultVo resultVo = new ResultVo();
        resultVo.setToken(token);
        resultVo.setImprove(true);
        return ResultResp.successData(resultVo);
    }


    @ApiOperation(value="登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userLogin", value = "登录vo", required = true, dataType = "UserLogin"),
    })
    @PostMapping(value="/login")
    @ResponseBody
    public ResultResp login(@RequestBody UserLogin userLogin, HttpServletRequest req) throws Exception {
        logger.info("-----------------------  登录开始  -----------------------");
        User user = new User();
        user.setPhone(userLogin.getMobileNo());
        User queryUser = userService.queryUserByLogin(user);
        if(queryUser == null || !queryUser.getLoginPwd().equals(new Sha256Hash(userLogin.getLoginPwd(), FundTransferEnum.PWD_SALT).toHex())){
            return ResultResp.fail("账号或密码不正确!");
        }else{
            RedisUtils.set(queryUser.getPhone()+ "userId",queryUser.getUserId());
            //生成token
            String token = JwtToken.createToken(queryUser.getUserId());
            //将用户数据保存在redis，同时保存在threadlocal, 一小时有效期
            RedisUtils.setLocalUser(token,queryUser,RedisUtils.ONE_HOUR_EXPIRE);
            ResultVo resultVo = new ResultVo();
            resultVo.setToken(token);
            resultVo.setAttorneyFlag(queryUser.getAttorneyFlag());
            if("".equals(queryUser.getUserName()) || queryUser.getUserName() == null){
                resultVo.setImprove(false);
            }else{
                resultVo.setImprove(true);
            }
            return ResultResp.successData(resultVo);
        }
    }

    @ApiOperation(value="开启资产委托")
    @PostMapping(value="/openAssetsMgt")
    @ResponseBody
    public ResultResp openAssetsMgt(HttpServletRequest req){
        User user = RedisUtils.getRedisUser(req);
        if(user != null){
            User user_update = new User();
            user_update.setAttorneyFlag("1");
            user_update.setUserId(user.getUserId());
           userService.updatUser(user_update);
        }else{
            return ResultResp.fail("未登录!");
        }
        return ResultResp.success();
    }

    @ApiOperation(value="关闭资产委托")
    @PostMapping(value="/closeAssetsMgt")
    @ResponseBody
    public ResultResp closeAssetsMgt(HttpServletRequest req){
        User user = RedisUtils.getRedisUser(req);
        if(user != null){
            User user_update = new User();
            user_update.setUserId(user.getUserId());
            user_update.setAttorneyFlag("0");
            user_update.setUserName(user.getUserName());
            userService.closeAssetsMgt(user_update);
        }else{
            return ResultResp.fail("未登录!");
        }
        return ResultResp.success();
    }

}
