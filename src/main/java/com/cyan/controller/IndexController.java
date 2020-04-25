package com.cyan.controller;

import com.cyan.pojo.Goods;
import com.cyan.pojo.Users;
import com.cyan.service.inteface.GoodsService;
import com.cyan.service.inteface.UsersService;
import com.cyan.utils.AesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UsersService usersService;
    @Value("${strKeyAES}") // 秘钥
    private static String key;

    // 前台主页
    @RequestMapping(value = {"/", "/index.html"})
    public String index(Model model) {

//         获取条幅商品
        Goods scroll = goodsService.getScrollGoods();
        model.addAttribute("scroll", scroll);
//         获取热销商品
        List<Goods> hotlist = goodsService.getHotGoodsList();
        model.addAttribute("hotList", hotlist);
//         获取新品商品
        List<Goods> newlist = goodsService.getNewGoodsList();
        model.addAttribute("newList", newlist);

        return "index";
    }

    // 后台主页
    @RequestMapping(value = {"/admin/", "/admin/index.html"})
    public String adminIndex() {
        return "admin/index";
    }

    // 后台登录页
    @GetMapping(value = {"/adminLogin.html"})
    public String adminLoginPage() {
        return "adminLogin";
    }

    // 后台登录页
    @PostMapping(value = {"/adminLogin"})
    public String adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {

        Users login = usersService.login(username, password);
        if (login != null) {
            session.setAttribute("loginUser", login);
            return "redirect:/admin/";
        } else {
            return "adminLogin";
        }
    }
}
