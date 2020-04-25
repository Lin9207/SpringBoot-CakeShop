package com.cyan.controller;

import com.cyan.pojo.Type;
import com.cyan.service.inteface.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/type")
public class AdminTypeController {

    @Autowired
    private TypeService typeService;

    /*查询所有类目*/
    @GetMapping("/list")
    public String list(Model model) {
        List<Type> typeList = typeService.selectAll();
        model.addAttribute("typeList", typeList);
        return "admin/goodstype_list";
    }

    /*显示修改页*/
    @GetMapping("/edit/{id}")
    public String editTypePage(@PathVariable("id") Integer id, Model model) {
        Type type = typeService.selectByPrimaryKey(id);
        model.addAttribute("type", type);
        return "admin/goodstype_edit";
    }

    /*修改类名名称*/
    @PostMapping("/edit/{id}")
    public String editType(Type type) {
        typeService.updateByPrimaryKey(type);
        return "redirect:/admin/type/list";
    }

    /*删除类目*/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {

        // 删除当前类目并回到当前页
        int isSuccess = typeService.deleteByPrimaryKey(id);
        if (isSuccess > 0) {
            model.addAttribute("msg", "删除成功！");
        } else {
            model.addAttribute("failMsg", "类目下包含商品,无法直接删除！");
        }
        return "redirect:/admin/type/list";
    }

    /*添加类目*/
    @PostMapping("/save")
    public String saveType(Type type) {
        typeService.insert(type);
        return "redirect:/admin/type/list";
    }
}
