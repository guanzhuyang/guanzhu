package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description: RegisterAccountIsWrongfulException
 * @author: 阳
 * @create: 2019-04-10 18:21
 */
public class RegisterAccountIsWrongfulException extends AppException {

    public RegisterAccountIsWrongfulException() {
        super(ExceptionConfig.REGISTER_ACCOUNT_IS_WRONGFUL,ExceptionConfig.REGISTER_ACCOUNT_IS_WRONGFUL_MSG);
    }
}
