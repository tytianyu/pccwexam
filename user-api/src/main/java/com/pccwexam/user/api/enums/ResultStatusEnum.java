package com.pccwexam.user.api.enums;

public enum ResultStatusEnum {

    SUCCESS("1", "成功状态"),
    ERROR("0", "失败状态");

    private String status;

    private String message;

    ResultStatusEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
