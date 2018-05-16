package com.jinhui.common.utils.validator;

/**
 * Created by Administrator on 2018/1/25 0025.
 */
public class ValidateResult {

    private boolean isSuccess;

    private String errorMessage;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ValidateResult{" +
                "isSuccess=" + isSuccess +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
