package com.design.yang.BaseResponse;

public class BaseResponse {
    long code;
    String msg;
    Object data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

        public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseResponse() {
        this.code = 0;
        this.msg = "ok";
    }

    public BaseResponse(Object data) {
        this.code = 0;
        this.msg = "ok";
        this.data = data;
    }
}
