package com.cyan.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goodsrecords implements Serializable {
    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 商品ID
    */
    private Integer goodsId;

    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 浏览次数
    */
    private Integer count;

    public Goodsrecords(Integer goodsId, Integer userId, Integer count) {
        this.goodsId = goodsId;
        this.userId = userId;
        this.count = count;
    }

    private static final long serialVersionUID = 1L;
}