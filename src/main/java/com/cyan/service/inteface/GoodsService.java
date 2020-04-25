package com.cyan.service.inteface;

import com.cyan.pojo.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {

    /*插入所有字段 - 主键自增,插入good*/
    int insert(Goods record);

    /*插入或更新所有字段 - 主键自增,插入good,主键存在就执行更新*/
    int insertOrUpdate(Goods record);

    /*选择插入所有Good字段 - 主键自增,插入good*/
    int insertSelective(Goods record);

    /*选择插入或更新所有Goods字段 - 主键自增,插入good,主键存在就执行更新*/
    int insertOrUpdateSelective(Goods record);

    /*批量插入good信息*/
    int batchInsert(List<Goods> list);

    /*删除指定ID的Good对象信息*/
    int deleteByPrimaryKey(Integer id);

    /*修改Good信息*/
    int updateByPrimaryKey(Goods record);

    /*选择修改Good信息*/
    int updateByPrimaryKeySelective(Goods record);

    /*批量修改Good信息*/
    int updateBatch(List<Goods> list);

    /*批量选择修改Good信息*/
    int updateBatchSelective(List<Goods> list);

    /*查询指定ID的Good对象信息*/
    Goods selectByPrimaryKey(Integer id);

    /*获取条幅商品*/
    Goods getScrollGoods();

    /*获取热销商品*/
    List<Goods> getHotGoodsList();

    /*获取新品商品*/
    List<Goods> getNewGoodsList();

    /*分页,根据字段name模糊搜索查询*/
    PageInfo<Goods> getSearchGoods(String searchName, Integer pageNum, Integer pageSize);

    /*分页,根据类目ID查询商品*/
    PageInfo<Goods> selectByTypeId(Integer typeId, Integer pageNum, Integer pageSize);

    /*分页,根据Recommend推荐表查询 - type(0-所有 1-条幅/2-热销/3-新品)*/
    PageInfo<Goods> selectByRecommendType(Integer type, Integer pageNum, Integer pageSize);

    // 判断是都为条幅商品
    boolean isScroll(Integer goodId);

    // 判断是都为热销商品
    boolean isHot(Integer goodId);

    // 判断是都为新品商品
    boolean isNew(Integer goodId);

    // 根据商品记录表 获取推荐商品
    List<Goods> selectBySrecords(Integer userid,Integer limitSize);

}
