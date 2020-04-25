package com.cyan.service.inteface;

import java.util.List;

import com.cyan.pojo.Recommend;

public interface RecommendService{

    /*插入所有字段 - 主键自增,插入Recommend商品推荐表*/
    int insert(Recommend record);

    /*插入或更新所有字段 - 主键自增,插入Recommend商品推荐表,主键存在就执行更新*/
    int insertOrUpdate(Recommend record);

    /*选择插入所有Recommend商品推荐表字段 - 主键自增,插入Recommend商品推荐表*/
    int insertSelective(Recommend record);

    /*选择插入或更新所有Recommend商品推荐表字段 - 主键自增,插入Recommend商品推荐表,主键存在就执行更新*/
    int insertOrUpdateSelective(Recommend record);

    /*批量插入Recommend商品推荐表*/
    int batchInsert(List<Recommend> list);

    /*删除指定ID的Recommend商品推荐表信息*/
    int deleteByPrimaryKey(Integer id);

    /*修改Recommend商品推荐表*/
    int updateByPrimaryKey(Recommend record);

    /*选择修改Recommend商品推荐表*/
    int updateByPrimaryKeySelective(Recommend record);

    /*批量修改Recommend商品推荐表*/
    int updateBatch(List<Recommend> list);

    /*批量选择修改Recommend商品推荐表*/
    int updateBatchSelective(List<Recommend> list);

    /*查询指定ID的Recommend商品推荐表信息*/
    Recommend selectByPrimaryKey(Integer id);

    /*根据推荐类型type查询*/
    List<Recommend> selectByTypeId(Integer typeid);

    /*修改推荐类型*/
    void changeType(Integer r_type, Integer g_id);
}
