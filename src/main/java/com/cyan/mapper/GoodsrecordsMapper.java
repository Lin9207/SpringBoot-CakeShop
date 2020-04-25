package com.cyan.mapper;

import com.cyan.pojo.Goodsrecords;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsrecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goodsrecords record);

    int insertOrUpdate(Goodsrecords record);

    int insertOrUpdateSelective(Goodsrecords record);

    int insertSelective(Goodsrecords record);

    Goodsrecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goodsrecords record);

    int updateByPrimaryKey(Goodsrecords record);

    int updateBatch(List<Goodsrecords> list);

    int updateBatchSelective(List<Goodsrecords> list);

    int batchInsert(@Param("list") List<Goodsrecords> list);

    /**
     *
     * @param userid 用户ID
     * @param goodsid   商品ID
     * @return
     */
    Goodsrecords selectByUserIDGoodsID(@Param("userid") Integer userid, @Param("goodsid") Integer goodsid);

    /**
     *
     * @param userid 用户ID
     * @param limitSize 查询数量
     * @return
     */
    List<Goodsrecords> selectByUserID(@Param("userid") Integer userid, @Param("limitSize") Integer limitSize);

}