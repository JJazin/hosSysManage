package com.ushier.hospital.illness.web.bean;

public class ResponseMessageBean<T> {

    private String status;
    private String msg;
    private T data;

    public ResponseMessageBean() {
    }

    public ResponseMessageBean(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
