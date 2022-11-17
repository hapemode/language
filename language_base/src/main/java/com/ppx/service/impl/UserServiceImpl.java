package com.ppx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppx.common.base.BaseResult;
import com.ppx.common.constant.UserConstant;
import com.ppx.entity.User;
import com.ppx.mapper.UserMapper;
import com.ppx.request.UserReq;
import com.ppx.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hape
 * @Date: 2022/11/17 10:02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public BaseResult login(UserReq req) {
        User user = query().eq("username", req.getUsername()).eq("password", req.getPassword()).one();
        if (user != null) {
            BaseResult result = BaseResult.getSuccessResult();
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            redisTemplate.opsForValue().set(UserConstant.login + req.getUsername(), token, 30, TimeUnit.MINUTES);
            result.setData(token);
            return result;
        } else {
            return BaseResult.getFailResult();
        }
    }

    @Override
    public BaseResult logout(UserReq req) {
        String token = redisTemplate.opsForValue().get(UserConstant.login + req.getUsername());
        if (StringUtils.isEmpty(token)) {
            return BaseResult.getSuccessResult();
        } else {
            redisTemplate.delete(UserConstant.login + req.getUsername());
            return BaseResult.getSuccessResult();
        }
    }

    @Override
    public BaseResult register(UserReq req) {
        int count = query().eq("username", req.getUsername()).count().intValue();
        if (count >= 1) {
            BaseResult result = BaseResult.getFailResult();
            result.setMessage("用户名重复");
            return result;
        } else {
            User user = new User(null, req.getUsername(), req.getPassword());
            save(user);
            return BaseResult.getSuccessResult();
        }
    }
}
