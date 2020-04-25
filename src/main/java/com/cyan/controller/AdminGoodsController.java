package com.cyan.controller;

import com.cyan.utils.UploadUtil;
import com.cyan.pojo.Goods;
import com.cyan.service.inteface.GoodsService;
import com.cyan.service.inteface.RecommendService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/good")
public class AdminGoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private UploadUtil uploadFile;

    /*查询商品 返回列表*/
    @GetMapping("/list")
    public String list(@RequestParam(value = "url_r_type", defaultValue = "0") Integer type,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Model model) {
        PageInfo<Goods> pageInfo = goodsService.selectByRecommendType(type, pageNum, 8);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("url_r_type", type);//将当前url类型专递过去
        return "admin/goods_list";
    }

    /*修改推荐状态*/
    @GetMapping("/recommend")
    public String recommendType(@RequestParam(value = "r_type") Integer r_type,
                                @RequestParam(value = "g_id") Integer g_id,
                                @RequestParam(value = "url_r_type", required = false) Integer url_r_type,
                                Model model) {
        recommendService.changeType(r_type, g_id);

        model.addAttribute("url_r_type", url_r_type);//将当前url类型专递过去
        return "forward:/admin/good/list";
    }

    /*显示 - 商品修改页*/
    @GetMapping("/edit")
    public String editPage(@RequestParam("g_id") Integer id,
                           @RequestParam(value = "url_r_type", required = false) Integer url_r_type,
                           Model model) {
        Goods good = goodsService.selectByPrimaryKey(id);

        model.addAttribute("good", good);
        model.addAttribute("url_r_type", url_r_type);//将当前url类型专递过去

        return "admin/goods_edit";
    }

    /*修改商品*/
    @PostMapping("/edit")
    public String edit(@RequestParam("cover_file") MultipartFile cover,
                       @RequestParam("image1_file") MultipartFile image1,
                       @RequestParam("image2_file") MultipartFile image2,
                       @RequestParam(value = "url_r_type", defaultValue = "0") Integer url_r_type,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Goods good, Model model) {

        /*保存表单提交的图片文件,并返回文件名,设置到商品对象中*/
        good.setCover(uploadFile.upload(cover));
        good.setImage1(uploadFile.upload(image1));
        good.setImage2(uploadFile.upload(image2));

        int isSuccess = goodsService.updateByPrimaryKeySelective(good);
        if (isSuccess > 0) {
            model.addAttribute("msg", "商品修改成功！");
        } else {
            model.addAttribute("failMsg", "错误-商品修改失败！");
        }

        /*修改完成-获取商品信息,返回当前商品管理页*/
        PageInfo<Goods> pageInfo = goodsService.selectByRecommendType(url_r_type, pageNum, 8);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("url_r_type", url_r_type);// 将当前url类型专递过去
        return "admin/goods_list";
    }

    /*删除商品*/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         @RequestParam(value = "url_r_type", required = false) Integer url_r_type,
                         Model model) {
        int isSuccess = goodsService.deleteByPrimaryKey(id);
        if (isSuccess > 0) {
            model.addAttribute("msg", "删除成功！");
        } else {
            model.addAttribute("failMsg", "该商品订单正在进行中,无法删除！");
        }

        model.addAttribute("url_r_type", url_r_type);//将当前url类型专递过去
        return "forward:/admin/good/list";
    }

    /*商品添加页*/
    @GetMapping("/save")
    public String savePage() {
        return "admin/goods_add";
    }

    /*商品添加页*/
    @PostMapping("/save")
    public String save(@RequestParam("cover_file") MultipartFile cover,
                       @RequestParam("image1_file") MultipartFile image1,
                       @RequestParam("image2_file") MultipartFile image2,
                       @RequestParam(value = "url_r_type", defaultValue = "0") Integer url_r_type,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Goods good, Model model) {

        /*保存表单提交的图片文件,并返回文件名,设置到商品对象中*/
        good.setCover(uploadFile.upload(cover));
        good.setImage1(uploadFile.upload(image1));
        good.setImage2(uploadFile.upload(image2));
        goodsService.insertSelective(good);

        model.addAttribute("msg", "商品添加成功！");

        /*修改完成-获取商品信息,返回当前商品管理页*/
        PageInfo<Goods> pageInfo = goodsService.selectByRecommendType(url_r_type, pageNum, 8);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("url_r_type", url_r_type);// 将当前url类型专递过去

        return "admin/goods_list";
    }
}
