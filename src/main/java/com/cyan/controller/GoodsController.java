package com.cyan.controller;

import com.cyan.pojo.*;
import com.cyan.service.inteface.GoodsService;
import com.cyan.service.inteface.GoodsrecordsService;
import com.cyan.service.inteface.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/good")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private GoodsrecordsService srecordsService;

    // 商品详情页
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") Integer id,
                            HttpSession session,
                            Model model) {
        // 获取登录用户
        Users user = (Users) session.getAttribute("loginUser");

        // 如果用户已登录 - 获取当前用户浏览最多商品列表，并增加当前商品浏览次数
        if (user != null) {
            // 获取浏览记录
            Goodsrecords goodsrecords = srecordsService.selectByUserIDGoodsID(user.getId(), id);
            if (goodsrecords != null)   // 存在 - 获取+1
                goodsrecords.setCount(goodsrecords.getCount() + 1);
            else    // 不存在 - 创建=1
                goodsrecords = new Goodsrecords(id, user.getId(), 1);
            // 添加更新记录
            srecordsService.insertOrUpdateSelective(goodsrecords);
        }

        // 推荐商品 - 用户已登录，推荐当前用户浏览最多商品 - 未登录，随机推荐热销商品
        List<Goods> recordList = null;
        Set<Goods> recordSet = new HashSet<>();
        if (user != null)
            recordList = goodsService.selectBySrecords(user.getId(), 10);
        else
            recordList = goodsService.selectByRecommendType(2, 1, 10).getList();
        // 随机抽取四个商品
        for (int i = 0; i < recordList.size(); i++) {
            if (recordSet.size() >= 4)
                break;
            Goods goods = recordList.get((int) (Math.random() * 9 + 0));
            recordSet.add(goods);
        }
        model.addAttribute("recordSet", recordSet);

        // 获取商品详情信息
        Goods goods = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good", goods);

        return "good_detail";
    }

    // 分页-分类获取所有商品
    @GetMapping("/list/{typeId}")
    public String getList(@PathVariable("typeId") Integer typeId,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          Model model) {

        PageInfo<Goods> pageInfo = goodsService.selectByTypeId(typeId, pageNum, 8);
        model.addAttribute("pageInfo", pageInfo);

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

    // 分页-热销&新品
    @GetMapping("/recommend")
    public String getRecommend(@RequestParam("type") Integer type,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               Model model) {

        PageInfo<Goods> pageInfo = goodsService.selectByRecommendType(type, pageNum, 8);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", type);

        return "good_recommend";
    }

    // 分页-搜索
    @GetMapping("/search")
    public String getSearch(@RequestParam("searchName") String searchName,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            Model model) {
        PageInfo<Goods> pageInfo = goodsService.getSearchGoods(searchName, pageNum, 8);
        model.addAttribute("searchName", searchName);
        model.addAttribute("pageInfo", pageInfo);
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
