package com.ht.oa.jk.utils.common;


import com.ht.oa.jk.model.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponseLogin;
import com.ht.oa.jk.model.resp.CommonResponseSimple;

public class ResultUtils {


    /**
     * 登录返回
     */
    public static CommonResponseLogin getLoginResult() {
        CommonResponseLogin commonResponseLogin = new CommonResponseLogin();
        commonResponseLogin.setCode(ResultCode.login.code());
        commonResponseLogin.setUrl(BaseUtils.getServerSuffixPath());
        return commonResponseLogin;
    }

    public static Object checkValid(String resMsg) {
        CommonResponseSimple commonResponseSimple = new CommonResponseSimple();
        commonResponseSimple.setCode(ResultCode.param.code());
        commonResponseSimple.setResMsg(resMsg);
        return commonResponseSimple;
    }

}
