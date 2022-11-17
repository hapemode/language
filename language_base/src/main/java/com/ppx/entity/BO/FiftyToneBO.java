package com.ppx.entity.BO;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/15 16:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiftyToneBO {

    /**
     * 读法
     */
    @ExcelProperty("发音")
    private String pronunciation;

    /**
     * 平假名
     */
    @ExcelProperty("平假名")
    private String hiragana;

    /**
     * 平假名写法
     */
    @ExcelProperty("平假名写法")
    private String hiraganaWrite;

    /**
     * 片假名
     */
    @ExcelProperty("片假名")
    private String katakana;

    /**
     * 片假名写法
     */
    @ExcelProperty("片假名写法")
    private String katakanaWrite;
}
