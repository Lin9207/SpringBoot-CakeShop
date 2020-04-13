package com.cyan.controller;

import com.cyan.pojo.Goods;
import com.cyan.pojo.Order;
import com.cyan.pojo.Type;
import com.cyan.pojo.Users;
import com.cyan.service.inteface.GoodsService;
import com.cyan.service.inteface.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/good")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypeService typeService;

    // 商品详情页
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") Integer id, Model model) {
        Goods goods = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good", goods);
        return "good_detail";
    }

    // 分类获取所有商品
    @GetMapping("/list/{typeId}")
    public String getList(@PathVariable("typeId") Integer typeId, Model model) {

        List<Goods> list = goodsService.selectByTypeId(typeId);
        model.addAttribute("list", list);

        // 查询typeId类目
        Type type = null;
        if (typeId != 0) {
            type = typeService.selectByPrimaryKey(typeId);
        }
        model.addAttribute("type", type);

        return "good_list";
    }

    // 我的购物车
    @GetMapping("/cart")
    public String getCart(HttpSession session) {
        return "good_cart";
    }

    // 热销&新品
    @GetMapping("/recommend")
    public String getRecommend(@RequestParam("type") Integer type, Model model) {
        List<Goods> list = goodsService.selectByRecommendType(type);
        model.addAttribute("list", list);
        model.addAttribute("type", type);
        return "good_recommend";
    }

    // 搜索
    @GetMapping("/search")
    public String getSearch(@RequestParam("searchName") String searchName, Model model) {
        List<Goods> searchList = goodsService.getSearchGoods(searchName);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchList", searchList);
        return "good_search";
    }

    // 添加到购物车 buy
    @PostMapping("/buy")
    @ResponseBody
    public String buy(@RequestParam("id") Integer id, HttpSession session) {

        // 创建订单表对象 ；如果会话中存在该对象则获取 ，不存在则创建一个对象
        Order order = null;
        if (session.getAttribute("userOrder") != null) {
            order = (Order) session.getAttribute("userOrder");
        } else {
            order = new Order();
            session.setAttribute("userOrder", order);
        }
        Goods goods = goodsService.selectByPrimaryKey(id);
        if (goods.getStock() > 0) {
            order.addGoods(goods);
            return "ok";
        } else {
            return "fail";
        }
    }

    // 购物车减去
    @PostMapping("/lessen")
    @ResponseBody
    public String lessen(@RequestParam("id") Integer id, HttpSession session) {
        Order order = (Order) session.getAttribute("userOrder");
        order.lessen(id);
        return "ok";
    }

    // 购物车删除
    @PostMapping("/deletes")
    @ResponseBody
    public String deletes(@RequestParam("id") Integer id, HttpSession session) {
        Order order = (Order) session.getAttribute("userOrder");
        order.deletes(id);
        return "ok";
    }
}
