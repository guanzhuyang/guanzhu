package com.design.yang.redisService;

import com.design.yang.dto.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yang
 * @description: Base redis service
 * @author: 阳
 * @create: 2019-04-03 17:19
 */
@Repository
public class BaseReidsService<T> {
    @Autowired
    protected RedisTemplate<String, T> redisTemplate;


    /**
    *@Description: add redis
    *@Param:
    *@return:
    *@date: 2019-4-3
    */public void add(String key, T value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
    *@Description: get redis value
    *@Param:
    *@return:
    *@date: 2019-4-3
    */
    public T get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void remove(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
