package com.ppx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppx.common.base.BaseResult;
import com.ppx.entity.User;
import com.ppx.request.UserReq;

/**
 * @Author: hape
 * @Date: 2022/11/17 10:01
 */
public interface UserService extends IService<User> {
    BaseResult login(UserReq req);

    BaseResult logout(UserReq req);

    BaseResult register(UserReq req);

    void doo(int i1, int count);
}
