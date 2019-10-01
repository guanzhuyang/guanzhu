package com.design.yang.Util;

/**
 * @program: yang
 * @description: FormatCheck
 * @author: 阳
 * @create: 2019-04-11 09:12
 */
public class FormatCheck {

    public static boolean isNull(String var){
        if(var == null || var.equals("")) return true;
        return false;
    }

    public static boolean IsRegisterAccountMatch(String account) {
        return !account.matches("^\\w{5,20}$");
    }

    public static boolean IsMobileMatch(String mobile){
        return !mobile.matches("^1\\d{10}$");
    }

    public static boolean IsEmailMatch(String email){
        return !email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }
}
