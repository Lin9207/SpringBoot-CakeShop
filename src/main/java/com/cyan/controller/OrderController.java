package com.cyan.controller;

import com.cyan.pojo.Order;
import com.cyan.pojo.Users;
import com.cyan.service.inteface.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger log = Logger.getLogger(this.getClass());

    // 我的订单
    @RequestMapping("/myOrder")
    public String myOrder(HttpSession session, Model model) {

        Users user = (Users) session.getAttribute("loginUser");
        List<Order> orderList = orderService.selectAllByUserId(user.getId());
        model.addAttribute("orderList", orderList);
        return "order_myorder";
    }

    // 购买
    @RequestMapping("/pay")
    public String pay(HttpSession session, Model model) {

        /*判断是否登录,没有登录不能购买*/
        if (session.getAttribute("loginUser") != null) {
            /*获取订单,购买时数量不能少于1*/
            Order userOrder = (Order) session.getAttribute("userOrder");
            if (userOrder != null && userOrder.getAmount() > 0) {
                return "order_pay";
            } else {
                return "good_cart";
            }
        } else {
            model.addAttribute("failMsg", "请登录后,再提交订单！");
            return "user_login";
        }
    }

    // 购买成功
    @RequestMapping("/paySuccess")
    public String paySuccess(HttpSession session,
                             Model model,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address,
                             @RequestParam(value = "paytype") Integer paytype) {

        Order order = (Order) session.getAttribute("userOrder"); // 获取购物车订单信息
        // 添加订单信息
        order.setName(name); // 收货人
        order.setPhone(phone); // 收货电话
        order.setAddress(address); // 收货地址
        order.setPaytype(paytype); // 支付方式
        order.setStatus(2); // 订单状态
        order.setDatetime(new Date(System.currentTimeMillis())); // 下单提交时间
        Users user = (Users) session.getAttribute("loginUser"); // 下单用户
        order.setUserId(user.getId());

        orderService.insertSelective(order); // 插入订单
        session.removeAttribute("userOrder"); // 删除购物车订单项

        model.addAttribute("msg", "订单支付成功！");
        return "order_paysuccess";
    }

    /*删除订单*/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        orderService.deleteByPrimaryKey(id);
        return "redirect:/order/myOrder";
    }
}