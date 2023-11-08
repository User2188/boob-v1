package com.example.boobcommoncore.response;

public enum ResponseEnum {
    /**
     * ok
     */
    OK("00000", "ok"),

    /**
     * 用于直接显示提示用户的错误，内容由输入内容决定
     */
    SHOW_FAIL("A00001", ""),

    /**
     * 方法参数没有校验，内容由输入内容决定
     */
    METHOD_ARGUMENT_NOT_VALID("A00002", ""),

    /**
     * 无法读取获取请求参数
     */
    HTTP_MESSAGE_NOT_READABLE("A00003", "请求参数格式有误"),

    /**
     * 未授权
     */
    UNAUTHORIZED("A00004", "Unauthorized"),

    /**
     * 服务器出了点小差
     */
    EXCEPTION("A00005", "服务器出了点小差"),

    /**
     * 数据异常
     */
    DATA_ERROR("A00007", "数据异常，请刷新后重新操作"),

    /**
     * 一些需要登录的接口，而实际上因为前端无法知道token是否已过期，导致token已失效时，
     * 应该返回一个状态码，告诉前端token已经失效了，及时清理
     */
    CLEAN_TOKEN("A00008", "clean token"),

    /**
     * 刷新token已过期
     */
    REFRESH_TOKEN_EXIST("A00009", "refresh token exist"),

    /**
     * 数据不完整
     */
    DATA_INCOMPLETE("A00010", "数据不完整"),

    /**
     * 01 开头代表注册登录之类的异常状态
     *
     */
    SOCIAL_ACCOUNT_NOT_BIND("A04001", "social account not bind"),

    /**
     * 有些时候第三方系统授权之后，会有个临时的key，比如小程序的session_key
     * 这个异常代表session_key过期，前端遇到这个问题的时候，应该再次调用社交登录的接口，刷新session_key
     */
    BIZ_TEMP_SESSION_KEY_EXPIRE("A04002", "biz temp session key expire"),

    /**
     * 账号未注册，前端看到这个状态码，弹出选择框，提示用户账号未注册，是否进入注册页面，用户选择是，进入注册页面
     */
    ACCOUNT_NOT_REGISTER("A04003", "account not register");
    private final String code;

    private final String msg;

    public String value() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "} " + super.toString();
    }
}
