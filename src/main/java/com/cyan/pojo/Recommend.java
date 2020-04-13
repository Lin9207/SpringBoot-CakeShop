package com.cyan.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品推荐表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommend implements Serializable {

    /**
     * ID - 主键
     */
    private Integer id;

    /**
    * 推荐类型 - ( 1条幅-2热销-3新品 )
    */
    private Integer type;

    /**
    * 产品id
    */
    private Integer goodsId;

    private static final long serialVersionUID = 1L;

    public Recommend(Integer type, Integer goodsId) {
        this.type = type;
        this.goodsId = goodsId;
    }
}