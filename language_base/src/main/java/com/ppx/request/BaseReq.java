package com.ppx.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/16 10:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReq {
    private int pageSize;
    private int pageNum;
}
