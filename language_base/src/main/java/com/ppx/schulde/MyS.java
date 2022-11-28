package com.ppx.schulde;

import com.ppx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: hape
 * @Date: 2022/11/24 18:47
 */
@Slf4j
@EnableScheduling
public class MyS {

    @Autowired
    UserService userService;
}
