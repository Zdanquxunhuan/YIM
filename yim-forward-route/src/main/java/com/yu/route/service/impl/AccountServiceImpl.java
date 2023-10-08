package com.yu.route.service.impl;

import com.yu.route.service.AccountService;
import com.yu.route.service.UserInfoCacheService;
import com.yu.yimrouteapi.req.LoginReqDTO;
import com.yu.yimrouteapi.res.RegisterInfoResDTO;
import com.yuge.yimcommon.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.yu.route.constant.Constant.ACCOUNT_PREFIX;
import static com.yu.route.constant.Constant.ROUTE_PREFIX;
import static com.yuge.yimcommon.enums.StatusEnum.REPEAT_LOGIN;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserInfoCacheService userInfoCacheService;

    @Override
    public RegisterInfoResDTO register(RegisterInfoResDTO registerInfo) {

        //cim-account:123456
        String accountKey = ACCOUNT_PREFIX + registerInfo.getUserId();
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();

        String accountValue = opsForValue.get(registerInfo.getUserName());
        if (StringUtils.isEmpty(accountValue)) {
            //Make a redundant copy for easy to query
            opsForValue.set(accountKey, registerInfo.getUserName());
            opsForValue.set(registerInfo.getUserName(), accountKey);
        } else {
            long userId = Long.parseLong(accountValue.split(":")[1]);
            registerInfo.setUserId(userId);
        }
        return registerInfo;
    }

    @Override
    public StatusEnum login(LoginReqDTO loginReq) {

        String accountKey = ACCOUNT_PREFIX + loginReq.getUserId();
        String accountValue = redisTemplate.opsForValue().get(accountKey);

        if (StringUtils.isEmpty(accountValue) || !accountValue.equals(loginReq.getUserName()))
            return StatusEnum.ACCOUNT_NOT_MATCH;

        //Log in successfully. Save the login status
        boolean hasLogin = userInfoCacheService.CheckAndSaveUserLoginStatus(loginReq.getUserId());
        if (hasLogin)
            return StatusEnum.REPEAT_LOGIN;

        return StatusEnum.SUCCESS;
    }

    @Override
    public void saveRouteInfo(Long userId, String serverInfo) {
        String key = ROUTE_PREFIX + userId;
        redisTemplate.opsForValue().set(key, serverInfo);
    }
}
