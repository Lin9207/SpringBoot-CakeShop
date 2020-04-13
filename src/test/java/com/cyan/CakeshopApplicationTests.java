package com.cyan;

import com.cyan.pojo.Goods;
import com.cyan.service.inteface.GoodsService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class CakeshopApplicationTests {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    GoodsService goodsService;

    @Test
    void contextLoads() throws SQLException {
        Goods goods = goodsService.selectByPrimaryKey(1);
        System.out.println(goods.getType().getId());
        System.out.println(goods.getType().getName());
    }
}
