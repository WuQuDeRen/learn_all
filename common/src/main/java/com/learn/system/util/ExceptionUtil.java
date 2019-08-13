package com.learn.system.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-30 12:17
 */
public class ExceptionUtil {

    private static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    public static String getMessage(Throwable e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        e.printStackTrace(printStream);
        String message = "";
        try {
            message = byteArrayOutputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            logger.error("异常转换错误", e);
        }
        return message;
    }
}
