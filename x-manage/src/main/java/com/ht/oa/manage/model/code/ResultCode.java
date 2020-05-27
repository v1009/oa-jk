package com.ht.oa.manage.model.code;

public enum ResultCode {

    success(200, "成功"),
    failure(500, "服务异常"),
    busiError(501, "服务异常"),
    param(502, "参数问题"),
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
