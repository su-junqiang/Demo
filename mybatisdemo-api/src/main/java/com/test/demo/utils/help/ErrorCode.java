package com.test.demo.utils.help;

public enum ErrorCode {
    USER_NOT_FOUND(400, "查无此用户"),
    USER_ADD_ERROR(401, "用户添加失败"),
    USER_UPDATE_ERROR(402, "更新失败"),
    USER_DELETE_ERROR(403, "删除失败"),
    PARAM_NOT_FOUND(600, "缺少参数"),
    PARAM_NULL(500, "缺少参数，请确认参数不为空"),
    EXCEPTION(300, "异常"),
    TOKEN_NULL(401, "token不存在"),
    TOKEN_HAS_EXP(402, "token已过期"),
    TOKEN_CHECK_ERROR(403, "验证token失败"),
    PARAM_ACCOUNT(400, "用户已存在"),
    PARAM_USER_PHONE(400, "手机号已经被注册"),
    UNDEFIND_ACCOUNT(404, "用户不存在"),
    UNDIFIND_ACCOUNT_PWD(404, "账号或密码错误"),
    UNDEFIND_BOOK(405, "书籍为空"),
    PARAM_BOOKID(406, "书籍ID已经存在"),
    BOOK_ADD_FAILED(407, "添加书籍失败");
    private Integer code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
