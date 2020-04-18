package com.ht.oa.jk.utils.common;


import com.ht.oa.jk.model.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponseLogin;
import com.ht.oa.jk.model.resp.CommonResponseSimple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtils {


    /**
     * list返回
     *
     * @return
     */
    public static Map<String, Object> list(List<Map<String, Object>> list) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", ResultCode.success.code());
        resMap.put("data", list);
        return resMap;
    }


    /**
     * 登录返回
     */
    public static CommonResponseLogin login() {
        CommonResponseLogin commonResponseLogin = new CommonResponseLogin();
        commonResponseLogin.setCode(ResultCode.login.code());
        commonResponseLogin.setUrl(BaseUtils.getServerSuffixPath());
        return commonResponseLogin;
    }

    /**
     * 检查参数
     *
     * @param resMsg
     * @return
     */
    public static Object paramNoPass(String resMsg) {
        CommonResponseSimple commonResponseSimple = new CommonResponseSimple();
        commonResponseSimple.setCode(ResultCode.param.code());
        commonResponseSimple.setResMsg(resMsg);
        return commonResponseSimple;
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static Object exception() {
        CommonResponseSimple commonResponseSimple = new CommonResponseSimple();
        commonResponseSimple.setCode(ResultCode.failure.code());
        commonResponseSimple.setResMsg(ResultCode.failure.desc());
        return commonResponseSimple;
    }

}
