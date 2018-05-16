package com.jinhui.common.utils.validator;


import com.jinhui.common.exception.BizException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验断言
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BizException(message);
        }
    }
}
