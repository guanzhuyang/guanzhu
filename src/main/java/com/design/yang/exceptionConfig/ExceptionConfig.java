package com.design.yang.exceptionConfig;

public class ExceptionConfig {
    public final static Long UNKOWN_ERROR = 60111L;//未知错误
    public final static String UNKOWN_ERROR_MSG = "";//未知错误信息

    public final static Long TEST_ERROR = 20000L;//测试错误
    public final static String TEST_ERROR_MSG = "这是测试错误";//测试错误信息

    public final static Long REGISTER_ACCOUNT_IS_NULL = 30001L;//注册账号不能为空
    public final static String REGISTER_ACCOUNT_IS_NULL_MSG = "注册账号不能为空";

    public final static Long REGISTER_ACCOUNT_IS_WRONGFUL = 30002L;//注册账号不合法
    public final static String REGISTER_ACCOUNT_IS_WRONGFUL_MSG = "注册账号不合法";

    public final static Long REGISTER_ACCOUNT_IS_EXISTED = 30003L;//注册账号已存在
    public final static String REGISTER_ACCOUNT_IS_EXISTED_MSG = "注册账号已存在";

    public final static Long REGISTER_MobileAndEmail_IS_NULL = 30004L;//注册账号已存在
    public final static String REGISTER_MobileAndEmail_IS_NULL_MSG = "注册手机号码和邮箱不能同时为空";

    public final static Long REGISTER_Mobile_IS_WRONGFUL = 30005L;//注册账号不合法
    public final static String REGISTER_Mobile_IS_WRONGFUL_MSG = "注册手机号码不合法";


    public final static Long REGISTER_Mobile_IS_EXISTED = 30006L;//注册账号已存在
    public final static String REGISTER_Mobile_IS_EXISTED_MSG = "注册手机号码已存在";


    public final static Long REGISTER_Email_IS_WRONGFUL = 30007L;//注册账号不合法
    public final static String REGISTER_Email_IS_WRONGFUL_MSG = "注册邮箱不合法";


    public final static Long REGISTER_Email_IS_EXISTED = 30008L;//注册账号已存在
    public final static String REGISTER_Email_IS_EXISTED_MSG = "注册邮箱已存在";

    public final static Long BALANCE_IS_NOT_ENOUGH = 40001L;
    public final static String BALANCE_IS_NOT_ENOUGH_MSG = "可用余额不足";

    public final static Long Entrust_Is_Not_Exist = 40002L;
    public final static String Entrust_Is_Not_EXIST_MSG = "此委托不存在或已完成";

    public final static Long Entrust_No_Authority = 40003L;
    public final static String Entrust_No_Authority_MSG = "无权限操作该委托";

    public final static Long Entrust_Number_Is_Null = 40004L;
    public final static String Entrust_Number_Is_Null_Msg = "委托交易货币数量不能为空";

    public final static Long Entrust_Price_Is_Null = 40005L;
    public final static String Entrust_Price_Is_Null_Msg = "委托交易货币价格不能为空";

    public final static Long Entrust_Pairs_Is_Null = 40006L;
    public final static String Entrust_Pairs_Is_Null_Msg = "委托交易货币对不能为空";

    //Currency
    public final static Long Currency_IS_Not_Existed = 40100L;
    public final static String Currency_IS_Not_Existed_Msg = "查询货币信息不存在";


    public final static Long USER_IS_Not_Authentication = 40200L;
    public final static String USER_IS_Not_Authentication_Msg = "实名认证后才可交易";


}
