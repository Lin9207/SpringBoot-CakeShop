package com.cyan.service;

import com.cyan.mapper.OrderMapper;
import com.cyan.mapper.OrderitemMapper;
import com.cyan.pojo.Order;
import com.cyan.pojo.Orderitem;
import com.cyan.service.inteface.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderitemMapper orderitemMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        /*删除订单项*/
        orderitemMapper.deleteByOrderId(id);
        /*删除订单*/
        orderMapper.deleteByPrimaryKey(id);
        return 1;
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Order record) {
        return orderMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Order record) {
        return orderMapper.insertOrUpdateSelective(record);
    }

    @Override
    @Transactional
    public int insertSelective(Order record) {
        // 添加订单
        int success = orderMapper.insert(record);
        // 获得订单id，并设置
        int id = getLastInsertId();
        record.setId(id);
        // 添加订单项，需要先获取订单ID
        for (Orderitem orderitem : record.getItemMap().values()) {
            orderitemMapper.insert(orderitem);
        }
        return success;
    }

    @Override
    public Order selectByPrimaryKey(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Order> list) {
        return orderMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Order> list) {
        return orderMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Order> list) {
        return orderMapper.batchInsert(list);
    }

    /*获取订单表中新添加的ID*/
    @Override
    public int getLastInsertId() {
        return orderMapper.getLastInsertId();
    }

    /*根据用户ID查询订单*/
    @Override
    public List<Order> selectAllByUserId(Integer id) {
        /*查询订单*/
        List<Order> orderList = orderMapper.selectAllByUserId(id);
        /*遍历订单-添加订单项*/
        for (Order order : orderList) {
            /*查询订单项*/
            List<Orderitem> orderitemList = orderitemMapper.selectAllByOrderId(order.getId());
            order.setItemList(orderitemList);
        }
        return orderList;
    }

    /*根据Status状态查询订单 [0-所有 1-未付款 2-已付款 3-配送中 4-已完成]*/
    @Override
    public List<Order> selectAllByStatus(Integer status) {

        /*查询订单*/
        List<Order> orderList = orderMapper.selectAllByStatus(status);
        /*遍历订单-添加订单项*/
        for (Order order : orderList) {
            /*查询订单项*/
            List<Orderitem> orderitemList = orderitemMapper.selectAllByOrderId(order.getId());
            order.setItemList(orderitemList);
        }
        return orderList;
    }

    /*修改订单状态*/
    @Override
    public int updateStatusById(Integer id, Integer status) {
        return orderMapper.updateStatusById(id,status);
    }
}
