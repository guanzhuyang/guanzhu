package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description: RegisterAccountIsexistedExption
 * @author: 阳
 * @create: 2019-04-10 18:22
 */
public class RegisterAccountIsexistedExption extends AppException {
    public RegisterAccountIsexistedExption() {
        super(ExceptionConfig.REGISTER_ACCOUNT_IS_EXISTED,ExceptionConfig.REGISTER_ACCOUNT_IS_EXISTED_MSG);
    }
}
