package com.cyan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    /**
     * ID - 主键
     */
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 封面地址
    */
    private String cover;

    /**
    * 详情图片1
    */
    private String image1;

    /**
    * 详情图片2
    */
    private String image2;

    /**
    * 价格
    */
    private Double price;

    /**
    * 简介
    */
    private String intro;

    /**
    * 库存
    */
    private Integer stock;

    /**
     * 类目 - 外键
     */
    private Type type;

    // 判断是否为[条幅|热销|新品]
    private boolean isScroll;
    private boolean isHot;
    private boolean isNew;

    private static final long serialVersionUID = 1L;
}