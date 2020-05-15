package com.ht.oa.jk.utils.common;


import com.ht.oa.jk.utils.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponseLogin;
import com.ht.oa.jk.model.resp.CommonResponseMulti;
import com.ht.oa.jk.model.resp.CommonResponseSimple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultUtils {

    /**
     * list返回
     *
     * @return
     */
    public static CommonResponseMulti list(List<Map<String, Object>> list) {
        CommonResponseMulti commonResponse = new CommonResponseMulti();
        commonResponse.setCode(ResultCode.success.code());
        commonResponse.setData(list);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> item : list) {
                Set<String> keySet = item.keySet();
                for (String key : keySet) {
                    /**
                     *  1，解决long精度丢失问题，转换为string
                     *  2，值为null的情况下转化为空字符串
                     */
                    String value = String.valueOf(item.get(key) == null ? "" : item.get(key));
                    item.put(key, value);
                }
            }
        }
        return commonResponse;
    }

    /**
     * item 返回
     *
     * @return
     */
    public static CommonResponseMulti item(Map<String, Object> item) {
        CommonResponseMulti commonResponse = new CommonResponseMulti();
        commonResponse.setCode(ResultCode.success.code());
        commonResponse.setData(item);
        if (item != null) {
            Set<String> keySet = item.keySet();
            for (String key : keySet) {
                /**
                 *  1，解决long精度丢失问题，转换为string
                 *  2，值为null的情况下转化为空字符串
                 */
                String value = String.valueOf(item.get(key) == null ? "" : item.get(key));
                item.put(key, value);
            }
        }
        return commonResponse;
    }

    /**
     * model 返回
     *
     * @return
     */
    public static CommonResponseMulti model(Object data) {
        CommonResponseMulti commonResponse = new CommonResponseMulti();
        commonResponse.setCode(ResultCode.success.code());
        commonResponse.setData(data);
        return commonResponse;
    }

    /**
     * success 返回
     *
     * @return
     */
    public static CommonResponseSimple success(String resMsg) {
        CommonResponseSimple commonResponse = new CommonResponseSimple();
        commonResponse.setCode(ResultCode.success.code());
        commonResponse.setResMsg(resMsg);
        return commonResponse;
    }

    /**
     * 登录返回
     */
    public static CommonResponseLogin login() {
        CommonResponseLogin commonResponseLogin = new CommonResponseLogin();
        commonResponseLogin.setCode(ResultCode.login.code());
        commonResponseLogin.setUrl("login expired");
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
     * 业务失败返回
     *
     * @return
     */
    public static Object busiFail(String resMsg) {
        CommonResponseSimple commonResponseSimple = new CommonResponseSimple();
        commonResponseSimple.setCode(ResultCode.busiError.code());
        commonResponseSimple.setResMsg(resMsg);
        return commonResponseSimple;
    }

    /**
     * 异常返回
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
