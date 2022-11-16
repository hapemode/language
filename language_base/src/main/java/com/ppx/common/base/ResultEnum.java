package com.ppx.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/15 15:44
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS("1", "success"),

    FAIL("0", "fail");

    private String code;
    private String message;
}
