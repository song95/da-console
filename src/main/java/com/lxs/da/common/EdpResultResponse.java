package com.lxs.da.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @version: version
 * @author: lixinsong
 * @className: EdpResultResponse
 * @packageName: com.lxs.da.common
 * @description:
 * @data: 2020/6/21
 **/


@JsonInclude(Include.NON_NULL)
public class EdpResultResponse <T>{
    private Boolean success = Boolean.TRUE;
    private int status = 200;
    private T result;

    public EdpResultResponse() {
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public int getStatus() {
        return this.status;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public EdpResultResponse<T> withResult(T result) {
        this.result = result;
        return this;
    }

    public String toString() {
        return "EdpResultResponse{success=" + this.success + ", status=" + this.status + ", result=" + this.result + '}';
    }
}
