package com.cyan.pojo;

import java.io.Serializable;

import lombok.*;

/**
 * 订单项表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderitem implements Serializable {

    /**
     * ID - 主键
     */
    private Integer id;

    /**
     * 购买时价格
     */
    private Double price;

    /**
     * 数量
     */
    private Integer amount;

    private String name;// 产品名-非表数据库字段 - 接收mybatis查询返回值

    /**
     * 产品ID - 外键
     */
    private Integer goodsId;

    /**
     * 订单ID - 外键
     */
    private Integer orderId;

    // 产品
    private Goods goods;

    // 订单,order_id
    private Order order;

    public Orderitem(Double price, Integer amount, Integer goodsId, Integer orderId, Goods goods, Order order) {
        this.price = price;
        this.amount = amount;
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.goods = goods;
        this.order = order;
    }

}