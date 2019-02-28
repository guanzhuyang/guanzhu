package com.design.yang.LoginService;

import com.design.yang.dto.AppUser;
import com.design.yang.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //验证登陆是否合法
        List<AppUser> users = sysUserRoleMapper.queryUserByAccount(username);
        if(users == null || users.size() == 0){
            throw new UsernameNotFoundException("未找到用户名");
        }
        AppUser user = users.get(0);
        log.info("user login userid {}",user.getUserId());
        return new User(String.valueOf(user.getUserId()), passwordEncoder.encode(user.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
    }
}
