package com.cyan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.cyan.pojo.Orderitem;

import java.util.List;

import com.cyan.mapper.OrderitemMapper;
import com.cyan.service.inteface.OrderitemService;

@Service
public class OrderitemServiceImpl implements OrderitemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OrderitemMapper orderitemMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderitemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Orderitem record) {
        return orderitemMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Orderitem record) {
        return orderitemMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Orderitem record) {
        return orderitemMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Orderitem record) {
        return orderitemMapper.insertSelective(record);
    }

    @Override
    public Orderitem selectByPrimaryKey(Integer id) {
        return orderitemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Orderitem record) {
        return orderitemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orderitem record) {
        return orderitemMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Orderitem> list) {
        return orderitemMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Orderitem> list) {
        return orderitemMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Orderitem> list) {
        return orderitemMapper.batchInsert(list);
    }

    /*根据订单ID查询所有订单项*/
    @Override
    public List<Orderitem> selectAllByOrderId(Integer id) {
        logger.info("获取所有订单项");
        return orderitemMapper.selectAllByOrderId(id);
    }

    /*根据订单ID删除订单项*/
    @Override
    public int deleteByOrderId(Integer id) {
        logger.info("删除订单项");
        return orderitemMapper.deleteByOrderId(id);
    }

    /*根据商品ID查询订单项*/
    @Override
    public List<Orderitem> selectByGoodId(Integer id) {
        logger.info("获取订单项");
        return orderitemMapper.selectByGoodId(id);
    }
}
