package com.ht.oa.manage.utils.crypto;

public class PwdUtils {

    /**
     * 获取用户加密的密码
     */
    public static String getSecretPwd(String pwd, int salt) {
        return new Md5().getMD5ofStr(pwd + "{" + salt + "}");
    }


}
