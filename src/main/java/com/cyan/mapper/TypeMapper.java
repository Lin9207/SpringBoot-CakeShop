package com.cyan.mapper;

import com.cyan.pojo.Recommend;
import com.cyan.pojo.Type;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TypeMapper {

    /*删除指定ID的Type类目表信息*/
    int deleteByPrimaryKey(Integer id);

    /*插入所有字段 - 主键自增,插入Type类目表*/
    int insert(Type record);

    /*插入或更新所有字段 - 主键自增,插入Type类目表,主键存在就执行更新*/
    int insertOrUpdate(Type record);

    /*选择插入所有Type类目表字段 - 主键自增,插入Type类目表*/
    int insertSelective(Type record);

    /*选择插入或更新所有Type类目表字段 - 主键自增,插入Type类目表,主键存在就执行更新*/
    int insertOrUpdateSelective(Type record);

    /*查询指定ID的Type类目表信息*/
    Type selectByPrimaryKey(Integer id);

    /*修改Type类目表*/
    int updateByPrimaryKey(Type record);

    /*选择修改Type类目表*/
    int updateByPrimaryKeySelective(Type record);

    /*批量修改Type类目表*/
    int updateBatch(List<Type> list);

    /*批量选择修改Type类目表*/
    int updateBatchSelective(List<Type> list);

    /*批量插入Type类目表*/
    int batchInsert(@Param("list") List<Type> list);

    /*查询获取Type类目表所有信息*/
    List<Type> selectAll();
}