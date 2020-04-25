package com.cyan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.cyan.mapper.GoodsrecordsMapper;

import java.util.List;

import com.cyan.pojo.Goodsrecords;
import com.cyan.service.inteface.GoodsrecordsService;

@Service
public class GoodsrecordsServiceImpl implements GoodsrecordsService {

    @Resource
    private GoodsrecordsMapper goodsrecordsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsrecordsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goodsrecords record) {
        return goodsrecordsMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Goodsrecords record) {
        return goodsrecordsMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Goodsrecords record) {
        return goodsrecordsMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Goodsrecords record) {
        return goodsrecordsMapper.insertSelective(record);
    }

    @Override
    public Goodsrecords selectByPrimaryKey(Integer id) {
        return goodsrecordsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goodsrecords record) {
        return goodsrecordsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goodsrecords record) {
        return goodsrecordsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Goodsrecords> list) {
        return goodsrecordsMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Goodsrecords> list) {
        return goodsrecordsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Goodsrecords> list) {
        return goodsrecordsMapper.batchInsert(list);
    }

    @Override
    public Goodsrecords selectByUserIDGoodsID(Integer userid, Integer goodsid) {
        Goodsrecords goodsrecords = goodsrecordsMapper.selectByUserIDGoodsID(userid, goodsid);
        return goodsrecords;
    }

    @Override
    public List<Goodsrecords> selectByUserID(Integer userid, Integer limitSize) {
        List<Goodsrecords> list = goodsrecordsMapper.selectByUserID(userid, limitSize);
        return list;
    }
}
