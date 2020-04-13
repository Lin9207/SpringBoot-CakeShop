package com.cyan.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.cyan.pojo.Recommend;
import com.cyan.mapper.RecommendMapper;
import com.cyan.service.inteface.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private RecommendMapper recommendMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return recommendMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Recommend record) {
        return recommendMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Recommend record) {
        return recommendMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Recommend record) {
        return recommendMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Recommend record) {
        return recommendMapper.insertSelective(record);
    }

    @Override
    public Recommend selectByPrimaryKey(Integer id) {
        return recommendMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Recommend record) {
        return recommendMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Recommend record) {
        return recommendMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Recommend> list) {
        return recommendMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Recommend> list) {
        return recommendMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Recommend> list) {
        return recommendMapper.batchInsert(list);
    }

    /*根据推荐类型查询 返回Recommend集合*/
    @Override
    public List<Recommend> selectByTypeId(Integer typeid) {
        return recommendMapper.selectByTypeId(typeid);
    }

    /*修改推荐类型*/
    @Override
    public void changeType(Integer r_type, Integer g_id) {
        /*根据 商品ID 推荐类型 查询Recommend表*/
        Recommend isRecommend = recommendMapper.isRecommend(r_type, g_id);
        /*如果存在 则删除 否则添加*/
        if (isRecommend != null) {
            recommendMapper.deleteByPrimaryKey(isRecommend.getId());
        } else {
            recommendMapper.insert(new Recommend(r_type,g_id));
        }
    }
}
