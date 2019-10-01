package com.design.yang.service;

import com.design.yang.dto.UserIdentity;
import com.design.yang.mapper.UserIdentityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-30 23:49
 */
@Service
public class UserIdentityService {
    @Autowired
    UserIdentityMapper userIdentityMapper;

    public boolean isAuthentication(long id){
        UserIdentity identity = new UserIdentity();
        identity.setUserId(id);
        List<UserIdentity> userIdentitys = userIdentityMapper.selectUserIdentity(identity);
        if(userIdentitys.size() == 0){
            return false;
        }
        UserIdentity userIdentity = userIdentitys.get(0);
        if("N".equals(userIdentity.getIsExamine())){
            return false;
        }
        return true;
    }

    public List<UserIdentity> getUnauditedId(){
        UserIdentity identity = new UserIdentity();
        identity.setIsExamine("N");
        return userIdentityMapper.selectUserIdentity(identity);
    }

    public void insertId(UserIdentity identity){
        identity.setIsExamine("Y");
        userIdentityMapper.insertUserIdentity(identity);
    }

    public void updateId(UserIdentity identity){
        userIdentityMapper.updateUserIdentity(identity);
    }
}
