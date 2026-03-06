package com.nguyenngocquyet.duancuatoi.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    USER_EXISTED(1001, "User existed"),
    INVALID_KEY(1002, "Invalid key"),
    USERNAME_INVALID(1003, "Username is invalid"),
    PASSWORD_TOO_SHORT(1004, "Password must be at least 8 characters")

    ;
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
