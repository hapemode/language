package com.ppx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/28 9:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reason {

    private Integer id;
    private Integer userId;
    private String status;

}
