package com.cyan.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    /**
     * ID - 主键
     */
    private Integer id;

    /**
    * 名称
    */
    private String name;

    private static final long serialVersionUID = 1L;
}