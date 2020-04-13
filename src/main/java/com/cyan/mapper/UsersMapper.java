package com.cyan.mapper;

import com.cyan.pojo.Users;

import java.util.List;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {

    /*删除指定ID的User用户表信息*/
    int deleteByPrimaryKey(Integer id);

    /*插入所有字段 - 主键自增,插入User用户表*/
    int insert(Users record);

    /*插入或更新所有字段 - 主键自增,插入User用户表,主键存在就执行更新*/
    int insertOrUpdate(Users record);

    /*选择插入所有User用户表字段 - 主键自增,插入User用户表*/
    int insertSelective(Users record);

    /*选择插入或更新所有User用户表字段 - 主键自增,插入User用户表,主键存在就执行更新*/
    int insertOrUpdateSelective(Users record);

    /*查询指定ID的User用户表信息*/
    Users selectByPrimaryKey(Integer id);

    /*修改User用户表*/
    int updateByPrimaryKey(Users record);

    /*选择修改User用户表*/
    int updateByPrimaryKeySelective(Users record);

    /*批量修改User用户表*/
    int updateBatch(List<Users> list);

    /*批量选择修改User用户表*/
    int updateBatchSelective(List<Users> list);

    /*批量插入User用户表*/
    int batchInsert(@Param("list") List<Users> list);

    // 登录验证-用户名
    Users selectByUsernamePassword(@Param("username") String usernameOrEmail, @Param("password") String password);

    // 登录验证-邮箱
    Users selectByEmailPassword(@Param("email") String usernameOrEmail, @Param("password") String password);

    // 验证用户名
    Users selectByUsername(String username);

    // 验证邮箱
    Users selectByEmail(String email);

    /*查询所有用户*/
    List<Users> selectAll();
}