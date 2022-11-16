package com.ppx.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/16 10:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiftyToneReq extends BaseReq {

    private int size = 10;

    /**
     * id
     */
    private Long id;

    /**
     * 读法
     */
    private String pronunciation;

    /**
     * 平假名
     */
    private String hiragana;

    /**
     * 平假名写法
     */
    private String hiraganaWrite;

    /**
     * 片假名
     */
    private String katakana;

    /**
     * 片假名写法
     */
    private String katakanaWrite;
}
