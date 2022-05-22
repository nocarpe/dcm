package com.dcm.application.util;

import com.alibaba.fastjson.JSON;
import java.util.Collection;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaAssert<E extends BizException> {
    private static final Logger log = LoggerFactory.getLogger(JavaAssert.class);

    public JavaAssert() {
    }

    public static <E extends BizException> void notNull(Object object, BizEnum returnCode, String message, Class<E> exceptionCls) {
        if (object == null) {
            log.warn(message);
            throwException(returnCode, message, exceptionCls);
        }

    }

    public static <E extends BizException> void isTrue(boolean expression, BizEnum returnCode, String message, Object arg, Class<E> exceptionCls) {
        if (!expression) {
            log.warn("expression is false,{},args:{}", message, arg);
            throwException(returnCode, message, exceptionCls);
        }

    }

    public static <E extends BizException> void isTrue(boolean expression, BizEnum returnCode, String message, Class<E> exceptionCls) {
        if (!expression) {
            log.warn("{}", message);
            throwException(returnCode, message, exceptionCls);
        }

    }

    public static <E extends BizException> void hasText(String text, BizEnum returnCode, String message, Class<E> exceptionCls) {
        if (StringUtils.isBlank(text)) {
            log.warn("{},args:{}", message, text);
            throwException(returnCode, message, exceptionCls);
        }

    }

    public static <E extends BizException> void notEmpty(Collection<?> collection, BizEnum returnCode, String message, Class<E> exceptionCls) {
        if (null == collection || 0 == collection.size()) {
            log.warn("{},args:{}", message, JSON.toJSONString(collection));
            throwException(returnCode, message, exceptionCls);
        }

    }

    public static <E extends BizException> void isNum(String text, BizEnum returnCode, String message, Class<E> exceptionCls) {
        boolean isNum = Pattern.compile("^[0-9]*$").matcher(text).find();
        if (!isNum) {
            log.warn("{},args:{}", message, text);
            throwException(returnCode, "不是正整数", exceptionCls);
        }

    }

    private static <E extends BizException> void throwException(BizEnum returnCode, String message, Class<E> exceptionCls) {
        if (null == exceptionCls) {
            throw new BizException(returnCode, message);
        } else {
            BizException cls = null;

            try {
                cls = (BizException)exceptionCls.newInstance();
            } catch (InstantiationException var5) {
                log.warn("构建异常失败,Class:{}", exceptionCls, var5.getMessage());
            } catch (IllegalAccessException var6) {
                log.warn("构建异常失败,Class:{}", exceptionCls, var6.getMessage());
            }

            if (null == cls) {
                cls = new BizException();
            }

            cls.setErrorCode(returnCode);
            cls.setErrorMessage(message);
            throw cls;
        }
    }
}