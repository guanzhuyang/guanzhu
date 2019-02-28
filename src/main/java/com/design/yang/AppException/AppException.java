package com.design.yang.AppException;

public class AppException extends RuntimeException {
    protected Long errCode;
    protected Object data;

    public AppException(Long errorCode,String message,Object data,Throwable e){
        super(message,e);
        this.errCode = errorCode ;
        this.data = data ;
    }

    public AppException(Long errorCode,String message,Object data){
        this(errorCode,message,data,null);
    }

    public AppException(Long errorCode,String message){
        this(errorCode,message,null,null);
    }

    public AppException(String message,Throwable e){
        this(null,message,null,e);
    }

    public AppException(){

    }

    public AppException(Throwable e){
        super(e);
    }

    public Long getErrorCode() {
        return errCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
