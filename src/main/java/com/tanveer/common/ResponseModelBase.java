package com.tanveer.common;

public abstract class ResponseModelBase {
    public String result;
    public int statusCode;

    public ResponseModelBase(int statusCode, String result) {
        this.statusCode = statusCode;
        this.result = result;
    }
}
