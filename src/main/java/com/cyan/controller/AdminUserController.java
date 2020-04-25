package com.cyan.controller;

import com.cyan.pojo.Users;
import com.cyan.service.inteface.UsersService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/list")
    public String userList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           Model model) {
        PageInfo<Users> pageInfo = usersService.selectAll(pageNum,8);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/user_list";
    }

    /*密码修改页,获取参数传递到修改页面显示*/
    @GetMapping("/reset/{id}")
    public String resetPasswordPage(@PathVariable("id") Integer id,
                                    @Param("username") String username,
                                    @Param("email") String email,
                                    Model model) {
        model.addAttribute("uId", id);
        model.addAttribute("uUsername", username);
        model.addAttribute("uEmail", email);
        return "admin/user_reset";
    }

    /*修改密码*/
    @PostMapping("/reset/{id}")
    public String resetPassword(@PathVariable("id") Integer id,
                                @Param("password") String password,
                                Model model) {

        Users user = new Users();
        user.setId(id);
        user.setPassword(password);
        usersService.updateByPrimaryKeySelective(user);
        return "redirect:/admin/user/list";
    }

    /*修改页,获取ID 查询用户 将用户对象传递到修改页面显示*/
    @GetMapping("/update/{id}")
    public String updateUserPage(@PathVariable("id") Integer id,
                                 Model model) {
        Users user = usersService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "admin/user_edit";
    }

    /*修改*/
    @PostMapping("/update/{id}")
    public String updateUser(Users user, Model model) {
        usersService.updateByPrimaryKeySelective(user);
        return "redirect:/admin/user/list";
    }

    /*删除*/
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        int isExsit = usersService.deleteByPrimaryKey(id);
        if (isExsit > 0)
            model.addAttribute("msg", "删除成功！");
        else
            model.addAttribute("failMsg", "删除失败！");
        return "forward:/admin/user/list";
    }

    /*添加页*/
    @GetMapping("/save")
    public String saveUserPage() {
        return "admin/user_add";
    }

    /*添加*/
    @PostMapping("/save")
    public String saveUser(Users user, Model model) {
        boolean isExsit = usersService.register(user);
        if (isExsit) {
            return "redirect:/admin/user/list";
        } else {
            model.addAttribute("failMsg", "用户名或邮箱重复,请重新填写!");
            model.addAttribute("user", user);
            return "admin/user_add";
        }
    }

    @GetMapping("/edit")
    public String editAdminPasswordPage() {
        return "admin/adminPassword";
    }

    @PostMapping("/edit")
    public String editAdminPassword(@Param("password") String password,
                                    @Param("passwordNew") String passwordNew,
                                    HttpSession session,
                                    Model model) {

        Users user = (Users) session.getAttribute("loginUser");

        if (password.equals(user.getPassword())) {
            user.setPassword(passwordNew);
            int success = usersService.updateByPrimaryKeySelective(user);
            if (success > 0)
                model.addAttribute("msg", "修改成功!");
            else
                model.addAttribute("failMsg", "修改失败!");
        } else {
            model.addAttribute("failMsg", "原密码马岩,你再谂谂!");
        }
        return "admin/adminPassword";
    }
}
