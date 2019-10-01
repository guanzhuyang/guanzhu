package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-21 19:35
 */
public class EntrustIsNotExistException extends AppException {
    public EntrustIsNotExistException() {
        super(ExceptionConfig.Entrust_Is_Not_Exist,ExceptionConfig.Entrust_Is_Not_EXIST_MSG);
    }
}
