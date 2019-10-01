package com.design.yang.aop;

import com.design.yang.AppException.AppException;
import com.design.yang.BaseResponse.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {

    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse exception(Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        BaseResponse base = new BaseResponse();
        if(exception instanceof AppException){//api异常
            AppException apiException = (AppException)exception;
            base.setCode(apiException.getErrorCode());
        }else{//未知异常
            base.setCode(60011);
        }
        base.setMsg(exception.getMessage());
       return base;
    }

    class ErrorDTO{
        private Long errorCode;
        private String tip;

        public Long getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Long errorCode) {
            this.errorCode = errorCode;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}

