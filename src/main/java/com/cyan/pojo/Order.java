package com.cyan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {


    /**
     * ID - 主键
     */
    private Integer id;

    /**
     * 总价
     */
    private Float total = 0.0f;

    /**
     * 商品总数
     */
    private Integer amount = 0;

    /**
     * 订单状态 - (1未付款/2已付款/3已发货/4已完成)
     */
    private Integer status = 0;

    /**
     * 支付方式 - (1微信/2支付宝/3货到付款)
     */
    private Integer paytype;

    /**
     * 收货人
     */
    private String name;

    /**
     * 收货电话
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 下单时间
     */
    private Date datetime;

    /**
     * 下单用户ID - 外键
     */
    private int userId;
    private Users user;

    /*一对多关系,一个订单包含多个订单项 [Order - OrderItems]*/
    private Map<Integer, Orderitem> itemMap = new HashMap<>();
    private List<Orderitem> itemList = new ArrayList<>();

    // 添加商品

    public void addGoods(Goods goods) {

        if (itemMap.containsKey(goods.getId())) {
            Orderitem orderitem = itemMap.get(goods.getId());
            orderitem.setAmount(orderitem.getAmount() + 1);
        } else {
            Orderitem orderitem = new Orderitem(goods.getPrice(), 1, goods.getId(), this.id, goods, this);
            itemMap.put(goods.getId(), orderitem);
        }
        //总数+1
        amount += 1;
        //总价运算-避免损失精度计算
        BigDecimal bd1 = new BigDecimal(total);
        BigDecimal bd2 = new BigDecimal(goods.getPrice());
        total = bd1.add(bd2).floatValue();
    }

    // 减少商品
    public void lessen(int goodsid) {
        if (itemMap.containsKey(goodsid)) {
            Orderitem item = itemMap.get(goodsid);
            item.setAmount(item.getAmount() - 1);

            //总价运算-避免损失精度计算
            BigDecimal bd1 = new BigDecimal(total);
            BigDecimal bd2 = new BigDecimal(item.getPrice());
            total = bd1.subtract(bd2).floatValue();

            //总数-1,总数少于等于0的话 在购物车中删除
            this.amount -= 1;
            if (item.getAmount() <= 0) {
                itemMap.remove(goodsid);
            }
        }
    }

    // 删除商品
    public void deletes(int goodsid) {
        if (itemMap.containsKey(goodsid)) {
            Orderitem item = itemMap.get(goodsid);
            amount -= item.getAmount();

            //总价运算-避免损失精度计算
            BigDecimal bd1 = new BigDecimal(total);
            BigDecimal bd2 = new BigDecimal(item.getAmount() * item.getPrice());
            total = bd1.subtract(bd2).floatValue();

            itemMap.remove(goodsid);
        }
    }
}