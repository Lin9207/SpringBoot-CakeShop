package com.cyan.mapper;

import com.cyan.pojo.Goods;
import com.cyan.pojo.Orderitem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderitemMapper {

    /*删除指定ID的Orderitem信息*/
    int deleteByPrimaryKey(Integer id);

    /*插入所有字段 - 主键自增,插入Orderitem*/
    int insert(Orderitem record);

    /*插入或更新所有字段 - 主键自增,插入Orderitem,主键存在就执行更新*/
    int insertOrUpdate(Orderitem record);

    /*选择插入所有Orderitem字段 - 主键自增,插入Orderitem*/
    int insertSelective(Orderitem record);

    /*选择插入或更新所有Orderitem字段 - 主键自增,插入Orderitem,主键存在就执行更新*/
    int insertOrUpdateSelective(Orderitem record);

    /*查询指定ID的Orderitem对象信息*/
    Orderitem selectByPrimaryKey(Integer id);

    /*修改Orderitem信息*/
    int updateByPrimaryKey(Orderitem record);

    /*选择修改Orderitem信息*/
    int updateByPrimaryKeySelective(Orderitem record);

    /*批量修改Orderitem信息*/
    int updateBatch(List<Orderitem> list);

    /*批量选择修改Orderitem信息*/
    int updateBatchSelective(List<Orderitem> list);

    /*批量插入Orderitem信息*/
    int batchInsert(@Param("list") List<Orderitem> list);

    /*根据订单ID查询所有订单项*/
    List<Orderitem> selectAllByOrderId(Integer id);

    /*根据订单ID删除订单项*/
    int deleteByOrderId(Integer id);

    /*根据商品ID查询订单项*/
    List<Orderitem>  selectByGoodId(Integer id);
}