package com.design.yang.redisService;

import com.design.yang.dto.TestArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestAreaRedisService {
    @Autowired
    RedisTemplate<String, List<TestArea>> redisTemplate;

    public void add(String key,List<TestArea> areas){
        redisTemplate.opsForValue().set(key,areas);
    }

    public List<TestArea> get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
