package com.design.yang.AppException.AppChildrenException;

import com.design.yang.AppException.AppException;
import com.design.yang.exceptionConfig.ExceptionConfig;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-20 18:12
 */
public class BalanceIsNotEnoughException extends AppException {
    public BalanceIsNotEnoughException(String cur) {
        super(ExceptionConfig.BALANCE_IS_NOT_ENOUGH,cur+ExceptionConfig.BALANCE_IS_NOT_ENOUGH_MSG);
    }
}
