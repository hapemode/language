package com.ppx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppx.entity.Reason;
import com.ppx.mapper.ReasonMapper;
import com.ppx.service.ReasonService;
import org.springframework.stereotype.Service;

/**
 * @Author: hape
 * @Date: 2022/11/28 9:33
 */
@Service
public class ReasonServiceImpl extends ServiceImpl<ReasonMapper, Reason> implements ReasonService {
}
