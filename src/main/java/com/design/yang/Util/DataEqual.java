package com.design.yang.Util;

import com.design.yang.constantClass.Constant;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-19 17:41
 */
public class DataEqual {
    public static boolean equal(Double a , Double b){
        if(a - b < Constant.Accuracy && b - a < Constant.Accuracy) return true;
        return false;
    }
}
