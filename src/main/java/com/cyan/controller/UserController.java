package com.cyan.controller;

import com.cyan.pojo.Users;
import com.cyan.service.inteface.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    // 登录页
    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {
        return "user_login";
    }

    // 登入
    @PostMapping("/login")
    public String login(@RequestParam("usernameOrEmail") String usernameOrEmail,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        Users user = usersService.login(usernameOrEmail, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/user/myCenter";
        } else {
            model.addAttribute("failMsg", "登录失败,请重新登录");
            return "user_login";
        }
    }

    // 登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    // 注册页
    @GetMapping("/register")
    public String registerPage() {
        return "user_register";
    }

    // 注册
    @PostMapping("/register")
    public String register(Users user, Model model) {

        boolean isExsit = usersService.register(user);

        if (isExsit) {
            model.addAttribute("msg", "注册成功,请登录");
            return "user_login";
        } else {
            model.addAttribute("msg", "注册失败,请重新注册");
            return "user_register";
        }
    }

    // 个人中心
    @GetMapping("/myCenter")
    public String myCenter(HttpSession session, Model model) {
        /*未登录不能进入*/
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("failMsg", "未登录！");
            return "user_login";
        }
        return "user_mycenter";
    }

    /*修改收货信息*/
    @PostMapping("/changeaddress")
    public String changeAddress(Users changeUser, HttpSession session, Model model) {

        Users loginUser = (Users) session.getAttribute("loginUser");
        loginUser.setName(changeUser.getName());
        loginUser.setPhone(changeUser.getPhone());
        loginUser.setAddress(changeUser.getAddress());

        int success = usersService.updateByPrimaryKeySelective(loginUser);
        if (success > 0)
            model.addAttribute("msg", "修改成功!");
        else
            model.addAttribute("failMsg", "修改失败!");
        return "user_mycenter";
    }

    /*修改密码*/
    @PostMapping("/changepassword")
    public String changePassword(@RequestParam("password") String password,
                                 @RequestParam("passwordNew") String passwordNew,
                                 HttpSession session,
                                 Model model) {

        Users loginUser = (Users) session.getAttribute("loginUser");

        /*验证输入的原密码是否正确*/
        if (password.equals(loginUser.getPassword())) {
            /*修改密码,添加到数据库*/
            loginUser.setPassword(passwordNew);
            int success = usersService.updateByPrimaryKeySelective(loginUser);
            /*添加成功,修改session域的loginUser*/
            if (success > 0) {
                session.setAttribute("loginUser", loginUser);
                model.addAttribute("msg", "修改成功!");
            } else {
                model.addAttribute("failMsg", "修改失败!");
            }
        } else {
            model.addAttribute("failMsg", "原密码马岩,你再谂谂!");
        }
        return "user_mycenter";
    }

}