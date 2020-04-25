package com.cyan.service.inteface;

import java.util.List;

import com.cyan.pojo.Goodsrecords;
import org.apache.ibatis.annotations.Param;

public interface GoodsrecordsService {


    int deleteByPrimaryKey(Integer id);

    int insert(Goodsrecords record);

    int insertOrUpdate(Goodsrecords record);

    int insertOrUpdateSelective(Goodsrecords record);

    int insertSelective(Goodsrecords record);

    Goodsrecords selectByPrimaryKey(Integer id);

    /**
     * 根据用户和商品查询获取记录
     * @param userid 用户ID
     * @param goodsid 商品ID
     * @return
     */
    Goodsrecords selectByUserIDGoodsID(Integer userid, Integer goodsid);

    /**
     * 根据用户浏览记录查询记录表,limitSize查询数量
     * @param userid 用户ID
     * @param limitSize 查询数量
     * @return
     */
    List<Goodsrecords> selectByUserID(Integer userid, Integer limitSize);

    int updateByPrimaryKeySelective(Goodsrecords record);

    int updateByPrimaryKey(Goodsrecords record);

    int updateBatch(List<Goodsrecords> list);

    int updateBatchSelective(List<Goodsrecords> list);

    int batchInsert(List<Goodsrecords> list);
}
