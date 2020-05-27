package com.ht.oa.manage.utils.common;


import com.ht.oa.manage.model.code.ResultCode;
import com.ht.oa.manage.model.resp.CommonResponseLogin;
import com.ht.oa.manage.model.resp.CommonResponseSimple;

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
