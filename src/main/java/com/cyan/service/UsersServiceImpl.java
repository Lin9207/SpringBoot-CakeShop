package com.cyan.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

import com.cyan.pojo.Users;
import com.cyan.mapper.UsersMapper;
import com.cyan.service.inteface.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Users record) {
        return usersMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Users record) {
        return usersMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Users> list) {
        return usersMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Users> list) {
        return usersMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Users> list) {
        return usersMapper.batchInsert(list);
    }

    // 登录验证
    @Override
    public Users login(String usernameOrEmail, String password) {

        Users user = null;

        // 验证用户名与密码
        user = usersMapper.selectByUsernamePassword(usernameOrEmail, password);
        if (user != null)
            return user;

        // 验证邮箱与密码
        user = usersMapper.selectByEmailPassword(usernameOrEmail, password);
        if (user != null)
            return user;

        // 用户名与邮箱验证失败-返回一个空值User
        return user;
    }

    // 用户注册
    @Override
    public boolean register(Users user) {
        /*查询用户名与邮箱是否存在,如果存在就不能注册创建*/
        if (null != usersMapper.selectByUsername(user.getUsername()))
            return false;
        if (null != usersMapper.selectByEmail(user.getEmail()))
            return false;
        /*注册用户*/
        usersMapper.insert(user);
        return true;
    }

    /*查询所有用户*/
    @Override
    public List<Users> selectAll() {
        return usersMapper.selectAll();
    }
}
