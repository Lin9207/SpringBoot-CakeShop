package com.cyan.mapper;

import com.cyan.pojo.Recommend;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecommendMapper {

    /*插入所有字段 - 主键自增,插入Recommend商品推荐表*/
    int insert(Recommend record);

    /*插入或更新所有字段 - 主键自增,插入Recommend商品推荐表,主键存在就执行更新*/
    int insertOrUpdate(Recommend record);

    /*选择插入所有Recommend商品推荐表字段 - 主键自增,插入Recommend商品推荐表*/
    int insertSelective(Recommend record);

    /*选择插入或更新所有Recommend商品推荐表字段 - 主键自增,插入Recommend商品推荐表,主键存在就执行更新*/
    int insertOrUpdateSelective(Recommend record);

    /*批量插入Recommend商品推荐表*/
    int batchInsert(@Param("list") List<Recommend> list);

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

    /*查询商品goods是否是[1-条幅，2-热销，3-新品]*/
    Recommend isRecommend(@Param("typeId") Integer typeId,@Param("goodId")Integer goodId);

    /*根据商品ID删除推荐表*/
    int deleteByGoodId(Integer id);
}