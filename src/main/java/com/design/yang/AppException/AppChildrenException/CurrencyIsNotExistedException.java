package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-28 18:41
 */
public class CurrencyIsNotExistedException extends AppException {
    public CurrencyIsNotExistedException() {
        super(ExceptionConfig.Currency_IS_Not_Existed,ExceptionConfig.Currency_IS_Not_Existed_Msg);
    }
}
