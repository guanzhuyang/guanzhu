package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description: Register Account Is Null Exception
 * @author: 阳
 * @create: 2019-04-10 18:11
 */
public class RegisterAccountIsNullException extends AppException {
    public RegisterAccountIsNullException() {
        super(ExceptionConfig.REGISTER_ACCOUNT_IS_NULL
                ,ExceptionConfig.REGISTER_ACCOUNT_IS_NULL_MSG);
    }
}
