package com.design.yang.Util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @program: yang
 * @description: my Context
 * @author: 阳
 * @create: 2019-04-08 20:47
 */
public class AppSecurityContext {
    public static String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static List<String> getUserRoles(){
        Collection authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> list = new ArrayList<>();
        Iterator it = authorities.iterator();
        while (it.hasNext()){
            GrantedAuthority authority = (GrantedAuthority)it.next();
            list.add(authority.getAuthority());
        }
        return list;
    }

}
