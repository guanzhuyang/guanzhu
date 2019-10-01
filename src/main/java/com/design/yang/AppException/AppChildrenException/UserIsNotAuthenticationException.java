package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-31 13:45
 */
public class UserIsNotAuthenticationException extends AppException {

    public UserIsNotAuthenticationException() {
        super(ExceptionConfig.USER_IS_Not_Authentication,ExceptionConfig.USER_IS_Not_Authentication_Msg);
    }
}
