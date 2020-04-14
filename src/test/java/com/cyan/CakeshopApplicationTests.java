package com.cyan;

import com.cyan.pojo.Goods;
import com.cyan.service.inteface.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class CakeshopApplicationTests {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    GoodsService goodsService;

    @Test
    void contextLoads() throws SQLException {

        PageInfo<Goods> pageInfo = goodsService.getSearchGoods("",1,8);

        int index = 0;

        System.err.println(pageInfo.getPageNum());
        System.err.println(pageInfo.getSize());

        for (Goods goods:pageInfo.getList()){
            System.err.println("Debug=====> "+(index++)+" - "+goods.getName());
        }
    }
}
