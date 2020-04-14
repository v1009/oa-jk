package com.ht.oa.jk.utils.common;

/**
 * Created with IntelliJ IDEA.
 * User: gaokb
 * Date: 18-5-22
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {

    public static boolean isBlank(String input) {
        if (input == null || input.trim().equals("")) {
            return true;
        }
        return false;
    }

}
