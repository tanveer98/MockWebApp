package com.tanveer.common;

public class ErrorResponseModel extends ResponseModelBase {
    public String reason;

    public ErrorResponseModel(int statusCode, String reason) {
        super(statusCode, Constants.RESULT_FAIL);
        this.reason = reason;
    }
}
