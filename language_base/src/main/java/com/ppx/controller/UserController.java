package com.ppx.controller;

import com.ppx.common.base.BaseResult;
import com.ppx.common.constant.UserConstant;
import com.ppx.request.UserReq;
import com.ppx.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hape
 * @Date: 2022/11/17 9:49
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public BaseResult login(@RequestBody UserReq req) {
        if (StringUtils.isEmpty(req.getUsername()) || StringUtils.isEmpty(req.getPassword())) {
            return BaseResult.getFailResult();
        }
        String token = redisTemplate.opsForValue().get(UserConstant.login + req.getUsername());
        if (StringUtils.isEmpty(token)) {
            return userService.login(req);
        } else {
            BaseResult result = BaseResult.getSuccessResult();
            result.setData(token);
            return result;
        }
    }

    @PostMapping("/logout")
    public BaseResult logout(@RequestBody UserReq req) {
        if (StringUtils.isEmpty(req.getUsername()) || StringUtils.isEmpty(req.getPassword())) {
            return BaseResult.getFailResult();
        } else {
            return userService.logout(req);
        }
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody UserReq req) {
        if (StringUtils.isEmpty(req.getUsername()) || StringUtils.isEmpty(req.getPassword())) {
            return BaseResult.getFailResult();
        } else {
            return userService.register(req);
        }
    }

}
