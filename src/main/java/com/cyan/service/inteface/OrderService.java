package com.cyan.service.inteface;

import com.cyan.pojo.Order;

import java.util.List;

public interface OrderService{

    /*删除指定ID的Order信息*/
    int deleteByPrimaryKey(Integer id);

    /*插入所有字段 - 主键自增,插入Order*/
    int insert(Order record);

    /*插入或更新所有字段 - 主键自增,插入Order,主键存在就执行更新*/
    int insertOrUpdate(Order record);

    /*选择插入所有Order字段 - 主键自增,插入Order*/
    int insertSelective(Order record);

    /*选择插入或更新所有Order字段 - 主键自增,插入Order,主键存在就执行更新*/
    int insertOrUpdateSelective(Order record);

    /*查询指定ID的Order对象信息*/
    Order selectByPrimaryKey(Integer id);

    /*修改Order信息*/
    int updateByPrimaryKey(Order record);

    /*选择修改Order信息*/
    int updateByPrimaryKeySelective(Order record);

    /*批量修改Order信息*/
    int updateBatch(List<Order> list);

    /*批量选择修改Order信息*/
    int updateBatchSelective(List<Order> list);

    /*批量插入Order信息*/
    int batchInsert(List<Order> list);

    /*获取最后插入表数据的ID*/
    int getLastInsertId();

    /*根据用户ID查询订单*/
    List<Order> selectAllByUserId(Integer id);

    /*根据Status状态查询订单 [0-所有 1-未付款 2-已付款 3-配送中 4-已完成]*/
    List<Order> selectAllByStatus(Integer status);

    /*修改订单状态*/
    int updateStatusById(Integer id, Integer status);
}
