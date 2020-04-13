package com.cyan.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    /**
     * ID - 主键
     */
    private Integer id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 密码
    */
    private String password;

    /**
    * 收货人
    */
    private String name;

    /**
    * 收货电话
    */
    private String phone;

    /**
    * 收货地址
    */
    private String address;

    /**
    * 是否是管理员
    */
    private Boolean isadmin;

    /**
    * 是否已验证邮箱
    */
    private Boolean isvalidate;

    private static final long serialVersionUID = 1L;
}