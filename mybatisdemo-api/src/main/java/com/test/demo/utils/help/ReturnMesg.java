package com.test.demo.utils.help;

import java.io.Serializable;

public class ReturnMesg<T>implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code = 200;
    private String message = "操作成功";
    private T data;

    public ReturnMesg() {
    }

    public ReturnMesg(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ReturnMesg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnMesg(T data) {
        this.data = data;
    }

    public ReturnMesg(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
