package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-21 19:37
 */
public class EntrustNoAuthorityException extends AppException {
    public EntrustNoAuthorityException() {
        super(ExceptionConfig.Entrust_No_Authority,ExceptionConfig.Entrust_No_Authority_MSG);
    }
}
