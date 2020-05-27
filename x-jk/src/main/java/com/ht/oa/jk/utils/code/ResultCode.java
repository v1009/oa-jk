package com.ht.oa.jk.utils.code;

public enum ResultCode {

    success(200, "成功"),
    param(201, "参数问题"),
    failure(500, "系统繁忙"),
    busiError(501, "服务繁忙"),
    login(101, "登录");

    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return code;
    }

    public String desc() {
        return desc;
    }
}
