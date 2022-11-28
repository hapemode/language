package com.ppx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppx.common.base.BaseResult;
import com.ppx.common.constant.UserConstant;
import com.ppx.entity.Reason;
import com.ppx.entity.User;
import com.ppx.mapper.UserMapper;
import com.ppx.request.UserReq;
import com.ppx.service.ReasonService;
import com.ppx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hape
 * @Date: 2022/11/17 10:02
 */
@Slf4j
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

//    @Async("AsyncExecutor")

    @Resource
    private Executor asyncExecutor;

    @Autowired
    private ReasonService reasonService;

    @Override
    @Transactional
    public void doo(int i, int count) {
        asyncExecutor.execute(() -> {
            System.out.println("当前线程=====>" + Thread.currentThread().getName());
            User user = new User(i, "ppx", "ss");
            Reason reason = new Reason();
            try {
                save(user);
                reason.setId(i);
                reason.setUserId(user.getId());
                reason.setStatus("1");
                if (i == 1) {
                    int a = 1 / 0;
                }
            } catch (Exception e) {
                reason.setStatus("0");
                if (count >= 3) {
                    log.error("重试次数耗尽");
                    e.printStackTrace();
                } else {
                    log.info("重试===>" + count + 1);
                    doo(i, count + 1);
                }
            } finally {
                reasonService.save(reason);
            }
        });
    }

}
