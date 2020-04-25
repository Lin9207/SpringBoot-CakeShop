package com.cyan.service;

import com.cyan.mapper.UsersMapper;
import com.cyan.pojo.Users;
import com.cyan.service.inteface.UsersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

        // 加密
//        password = DigestUtils.md5DigestAsHex(password.getBytes());

        logger.info("用户请求登录");
        Users user = null;
        // 验证用户名与密码
        user = usersMapper.selectByUsernamePassword(usernameOrEmail, password);
        if (user != null) {
            logger.info("用户名登录");
            return user;
        }
        // 验证邮箱与密码
        user = usersMapper.selectByEmailPassword(usernameOrEmail, password);
        if (user != null) {
            logger.info("邮箱登录");
            return user;
        }
        // 用户名与邮箱验证失败-返回一个空值User
        logger.info("登录失败");
        return user;
    }

    // 用户注册
    @Override
    public boolean register(Users user) {

        // 加密
//        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        logger.info("用户注册");
        /*查询用户名与邮箱是否存在,如果存在就不能注册创建*/
        if (null != usersMapper.selectByUsername(user.getUsername())) {
            logger.info("邮箱重复");
            return false;
        }
        if (null != usersMapper.selectByEmail(user.getEmail())) {
            logger.info("用户名重复");
            return false;
        }
        /*注册用户*/
        usersMapper.insert(user);
        logger.info("用户成功");

        return true;
    }

    /*查询所有用户*/
    @Override
    public PageInfo<Users> selectAll(Integer pageNum, Integer pageSIze) {
        logger.info("查询所有用户");
        /*设置分页*/
        Page<Users> page = PageHelper.startPage(pageNum, pageSIze, true);
        /*查询*/
        List<Users> list = usersMapper.selectAll();
        /*将分页模型返回*/
        logger.info("查询成功");
        return new PageInfo<>(list);
    }
}
