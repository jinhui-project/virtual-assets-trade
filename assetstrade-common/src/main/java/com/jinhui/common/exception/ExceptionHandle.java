package com.jinhui.common.exception;

import com.jinhui.common.entity.vo.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

@RestControllerAdvice
public class ExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 捕获该处理器的业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public WebResult expHandler(BizException e) {
        logger.error("业务异常", e);
        return WebResult.error("99",e.getMessage());
    }

    /**
     * 捕获该处理器的所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResult expHandler(Exception e) {
        logger.error("接口异常", e);
        return WebResult.error("请联系管理员");
    }


}
