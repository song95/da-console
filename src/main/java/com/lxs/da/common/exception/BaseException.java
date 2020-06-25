package com.lxs.da.common.exception;

/**
 * @version: version
 * @author: lixinsong
 * @className: BaseException
 * @packageName: com.lxs.da.common.exception
 * @description:
 * @data: 2020/6/21
 **/


public class BaseException extends RuntimeException {
    private int httpStatus;
    private String code;
    private String requestId;

    public BaseException(String message) {
        super(message);
        this.httpStatus = 400;
        String name = this.getClass().getSimpleName();
        if (this.getClass().getName().contains("$")) {
            String[] parts = this.getClass().getCanonicalName().split("\\.");
            name = parts[parts.length - 2] + "." + parts[parts.length - 1];
        }

        this.code = name;
    }

    public BaseException(String message, int httpStatus) {
        this(message);
        this.httpStatus = httpStatus;
    }

    public BaseException(String message, String code) {
        super(message);
        this.httpStatus = 400;
        this.code = code;
    }

    public BaseException(String message, int httpStatus, String code) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public String toString() {
        return "BecResponseException{httpStatus='" + this.httpStatus + '\'' + ", requestId='"
                + this.requestId + '\'' + ", code='" + this.code + '\'' + ", message='" + this.getMessage() + "'" + '}';
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            BaseException that = (BaseException) o;
            if (this.httpStatus != that.httpStatus) {
                return false;
            } else {
                if (this.code != null) {
                    if (!this.code.equals(that.code)) {
                        return false;
                    }
                } else if (that.code != null) {
                    return false;
                }

                if (this.requestId != null) {
                    return this.requestId.equals(that.requestId);
                } else return that.requestId == null;

            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.httpStatus;
        result = 31 * result + (this.requestId != null ? this.requestId.hashCode() : 0);
        result = 31 * result + (this.code != null ? this.code.hashCode() : 0);
        return result;
    }
}
