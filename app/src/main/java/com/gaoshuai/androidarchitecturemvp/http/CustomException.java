package com.gaoshuai.androidarchitecturemvp.http;

/**
 * Created by gaoshuai on 2019/3/22.
 * Describe：
 */
public class CustomException extends Throwable {
    private int errCode;
    private String errMsg;

    public CustomException(int errCode, String message) {
        super(message);
        this.errMsg = message;
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public int getErrCode() {
        return errCode;
    }
}
