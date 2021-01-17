package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.Result;
import com.wronggo.model.TbSpecification;
import com.wrongo.service.TbBrandService;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wronggo.model.TbBrand;
import com.wronggo.util.R;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TbBrandController {
    @Reference
    private TbBrandService tbBrandService;
    @RequestMapping("/seller_1")
    public ModelAndView seller_1() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("seller_1");
        return modelAndView;
    }
    @RequestMapping("/content")
    public ModelAndView content() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("content");
        return modelAndView;
    }
    @RequestMapping("/content_category")
    public ModelAndView content_category() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("content_category");
        return modelAndView;
    }
    @RequestMapping("/goods")
    public ModelAndView goods() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("goods");
        return modelAndView;
    }
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
    @RequestMapping("/brand")
    public ModelAndView brand() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("brand");
        return modelAndView;
    }
    @RequestMapping("specification")
    public ModelAndView specification() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("specification");
        return modelAndView;
    }
    @RequestMapping("typetemplate")
    public ModelAndView typetemplate() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("type_template");
        return modelAndView;
    }
    @RequestMapping("/item_cat")
    public ModelAndView item_cat(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("item_cat");
        return modelAndView;
    }

    @RequestMapping("selectall")
    public R selectall() {
        List<TbBrand> selectall = tbBrandService.selectall();
        if (selectall!=null){
            return R.ok().data("selectall",selectall);
        }else {
            return  R.error().message("查询失败");
        }

    }
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return tbBrandService.findPage(page, rows);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand){
        try {
            tbBrandService.add(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            tbBrandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return tbBrandService.findOne(id);
    }
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            tbBrandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand, int page, int rows  ){
        return tbBrandService.findPage1(brand, page, rows);
    }

//    @RequestMapping("/selectbylike")
//    public List<TbBrand> selectbylike(@RequestBody TbBrand brand){
//        return tbBrandService.selectbylike(brand);
//    }
@RequestMapping("/selectOptionList")
public List<Map> selectOptionList(){
    return tbBrandService.selectOptionList();
}
}
