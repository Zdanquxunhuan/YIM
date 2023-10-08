package com.yu.route.service.impl;

import com.yu.route.service.UserInfoCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.yu.route.constant.Constant.LOGIN_STATUS_PREFIX;

@Slf4j
@Service
public class UserInfoCacheServiceImpl implements UserInfoCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean CheckAndSaveUserLoginStatus(Long userId) {

        Long add = redisTemplate.opsForSet().add(LOGIN_STATUS_PREFIX, userId.toString());
        if (add == 0) {
            return true;
        } else {
            return false;
        }
    }
}
