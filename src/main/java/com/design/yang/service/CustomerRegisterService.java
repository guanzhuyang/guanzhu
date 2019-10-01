package com.design.yang.service;

import com.design.yang.AppException.AppChildrenException.RegisterAccountIsNullException;
import com.design.yang.AppException.AppChildrenException.RegisterAccountIsWrongfulException;
import com.design.yang.AppException.AppChildrenException.RegisterAccountIsexistedExption;
import com.design.yang.AppException.AppException;
import com.design.yang.Util.FormatCheck;
import com.design.yang.constantClass.Constant;
import com.design.yang.dto.RegisterInfo;
import com.design.yang.dto.SysRole;
import com.design.yang.dto.UserBaseInfo;
import com.design.yang.dto.UserCurrencyBalance;
import com.design.yang.exceptionConfig.ExceptionConfig;
import com.design.yang.mapper.CurrencyBalanceMapper;
import com.design.yang.mapper.CustomerRegisterMapper;
import com.design.yang.mapper.SampleUserInfoMapper;
import com.design.yang.mapper.SysRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yang
 * @description: Customer register service
 * @author: 阳
 * @create: 2019-04-10 17:38
 */
@Service
@Slf4j
public class CustomerRegisterService {
    @Autowired
    CustomerRegisterMapper customerRegisterMapper;
    @Autowired
    SampleUserInfoMapper sampleUserInfoMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    CurrencyBalanceMapper currencyBalanceMapper;

    public boolean RegisterNewCustomer(RegisterInfo registerInfo){
        boolean flag = false;
        if(registerInfo == null) throw new RegisterAccountIsNullException();
        if(FormatCheck.isNull(registerInfo.getAccount())) throw new RegisterAccountIsNullException();
        if(FormatCheck.IsRegisterAccountMatch(registerInfo.getAccount())) throw new RegisterAccountIsWrongfulException();
        List<RegisterInfo> registerInfos = customerRegisterMapper.selectByAccount(registerInfo.getAccount());
        if(registerInfos.size() != 0) throw new RegisterAccountIsexistedExption();
        if(FormatCheck.isNull(registerInfo.getMobile()) && FormatCheck.isNull(registerInfo.getEmail())) throw new AppException(ExceptionConfig.REGISTER_MobileAndEmail_IS_NULL,ExceptionConfig.REGISTER_MobileAndEmail_IS_NULL_MSG);
        if(!FormatCheck.isNull(registerInfo.getMobile())){
            if(FormatCheck.IsMobileMatch(registerInfo.getMobile()))
                throw new AppException(ExceptionConfig.REGISTER_Mobile_IS_WRONGFUL,ExceptionConfig.REGISTER_Mobile_IS_WRONGFUL_MSG);
            registerInfos = customerRegisterMapper.selectByMobile(registerInfo.getMobile());
            if(registerInfos.size() != 0) throw new AppException(ExceptionConfig.REGISTER_Mobile_IS_EXISTED,ExceptionConfig.REGISTER_Mobile_IS_EXISTED_MSG);
        }
        if(!FormatCheck.isNull(registerInfo.getEmail())){
            if(FormatCheck.IsEmailMatch(registerInfo.getEmail()))
                throw new AppException(ExceptionConfig.REGISTER_Email_IS_WRONGFUL,ExceptionConfig.REGISTER_Email_IS_WRONGFUL_MSG);
            registerInfos = customerRegisterMapper.selectByEmail(registerInfo.getEmail());
            if(registerInfos.size() != 0) throw new AppException(ExceptionConfig.REGISTER_Email_IS_EXISTED,ExceptionConfig.REGISTER_Email_IS_EXISTED_MSG);
        }
        customerRegisterMapper.insertNewCustomer(registerInfo);
        SysRole role = sysRoleMapper.selectEffectiveRoleByRoleName("WEB_USER");
        List<RegisterInfo> registerInfoList = customerRegisterMapper.selectByAccount(registerInfo.getAccount());
        sysRoleMapper.insertRoleUser(registerInfoList.get(0).getId(),role.getRoleId());
        registerInfos = customerRegisterMapper.selectByAccount(registerInfo.getAccount());
        UserBaseInfo info = new UserBaseInfo();
        info.setAccount(registerInfos.get(0).getId().toString());
        info.setNickName(registerInfos.get(0).getAccount());
        sampleUserInfoMapper.insertInfo(info);
        UserCurrencyBalance currencyBalance = new UserCurrencyBalance();
        currencyBalance.setCurName(Constant.BASE_CURRENCY);
        currencyBalance.setUserId(registerInfos.get(0).getId());
        currencyBalance.setAvailableBalance(Double.valueOf(0));
        currencyBalance.setBalance(Double.valueOf(0));
        currencyBalanceMapper.insertNewCurrencyBalance(currencyBalance);
        flag = true;
        return flag;
    }

}
