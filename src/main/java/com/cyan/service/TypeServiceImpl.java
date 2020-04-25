package com.cyan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.cyan.pojo.Type;
import com.cyan.mapper.TypeMapper;
import com.cyan.service.inteface.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private TypeMapper typeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Type record) {
        return typeMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Type record) {
        return typeMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Type record) {
        return typeMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Type record) {
        return typeMapper.insertSelective(record);
    }

    @Override
    public Type selectByPrimaryKey(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Type record) {
        return typeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Type record) {
        return typeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Type> list) {
        return typeMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Type> list) {
        return typeMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Type> list) {
        return typeMapper.batchInsert(list);
    }

    /*查询获取Type类目表所有信息*/
    @Override
    public List<Type> selectAll() {
        logger.info("查询所有商品类目");
        List<Type> list = typeMapper.selectAll();
        logger.info("查询成功");
        return list;
    }
}
