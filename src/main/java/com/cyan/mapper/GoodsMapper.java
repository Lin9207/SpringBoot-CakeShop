package com.cyan.mapper;

import com.cyan.pojo.Goods;
import java.util.List;

import com.cyan.pojo.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper {

//=====增:

    /*插入所有字段 - 主键自增,插入good*/
    int insert(Goods record);

    /*插入或更新所有字段 - 主键自增,插入good,主键存在就执行更新*/
    int insertOrUpdate(Goods record);

    /*选择插入所有Good字段 - 主键自增,插入good*/
    int insertSelective(Goods record);

    /*选择插入或更新所有Goods字段 - 主键自增,插入good,主键存在就执行更新*/
    int insertOrUpdateSelective(Goods record);

    /*批量插入good所有字段*/
    int batchInsert(@Param("list") List<Goods> list);

//=====删:

    /*删除指定ID的Good对象信息*/
    int deleteByPrimaryKey(Integer id);

//=====改:

    /*修改Good信息*/
    int updateByPrimaryKey(Goods record);

    /*选择修改Good信息*/
    int updateByPrimaryKeySelective(Goods record);

    /*批量修改Good信息*/
    int updateBatch(List<Goods> list);

    /*批量选择修改Good信息*/
    int updateBatchSelective(List<Goods> list);

//=====查:

    /*查询指定ID的Good对象信息*/
    Goods selectByPrimaryKey(Integer id);

    /*根据推荐表类型批量查询*/
    List<Goods> selectByRecommendIds(@Param("list")List<Recommend> list);

    /*根据推荐表类型查询*/
    Goods selectByRecommendId(@Param("recommend")Recommend recommend);

    /*根据表字段name模糊查询*/
    List<Goods> getSearchGoods(@Param("searchName")String searchName);

    /*根据类目ID查询商品*/
    List<Goods> selectByTypeId(@Param("typeId")Integer typeId);

    /*根据Recommend推荐表查询 - type(0-所有 1-条幅/2-热销/3-新品)*/
    List<Goods> selectByRecommendType(@Param("type")Integer type);
}