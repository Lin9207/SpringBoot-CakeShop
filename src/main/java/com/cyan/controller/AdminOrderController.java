package com.cyan.controller;

import com.cyan.pojo.Order;
import com.cyan.service.inteface.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "status", defaultValue = "0") Integer status,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Model model) {

        PageInfo<Order> pageInfo = orderService.selectAllByStatus(status,pageNum,8);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("status", status);
        return "admin/order_list";
    }

    /*修改订单状态*/
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestParam(value = "status", defaultValue = "0") Integer status) {

        orderService.updateStatusById(id,status);
        return "forward:/admin/order/list";
    }

    /*删除订单*/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         @RequestParam(value = "status", defaultValue = "0") Integer status) {
        orderService.deleteByPrimaryKey(id);
        return "redirect:/admin/order/list";
    }
}
