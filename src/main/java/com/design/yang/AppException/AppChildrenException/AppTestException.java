package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

public class AppTestException extends AppException {
    public AppTestException() {
        super(ExceptionConfig.TEST_ERROR,ExceptionConfig.TEST_ERROR_MSG);
    }
}
