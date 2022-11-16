package com.ppx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hape
 * @since 2022-11-15 15:20:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("fifty_tone")
public class FiftyTone implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
