package com.ppx.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/17 9:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {
    private Long id;

    private String username;

    private String password;
}
