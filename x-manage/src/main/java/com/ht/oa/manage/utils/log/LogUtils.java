package com.ht.oa.manage.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    private static Logger Console = LoggerFactory.getLogger("console");
    private static Logger App = LoggerFactory.getLogger("app");
    private static Logger Error = LoggerFactory.getLogger("error");

    /**
     * 打印错误日志
     */
    public static void app(String msg) {
        Console.info(msg);
    }


    /**
     * 打印错误日志
     */
    public static void error(String msg) {
        Error.error(msg);
    }

    /**
     * 打印错误日志
     */
    public static void error(Exception e) {
        Error.error(ExceptionUtils.getFullStaceInfo(e));
    }

    /**
     * 打印错误日志
     */
    public static void error(String sign, Exception e) {
        Error.error(sign + ExceptionUtils.getFullStaceInfo(e));
    }

}
