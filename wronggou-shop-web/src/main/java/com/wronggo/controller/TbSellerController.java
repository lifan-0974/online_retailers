package com.wronggo.controller;

import com.wronggo.model.Result;
import com.wronggo.model.TbSeller;
import com.wrongo.service.TbSellerService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TbSellerController {

@Reference
    TbSellerService tbSellerService;
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
    @RequestMapping("/goods")
    public ModelAndView goods() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("goods");
        return modelAndView;
    }
    @RequestMapping("/goods_edit")
    public ModelAndView goods_edit() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("goods_edit");
        return modelAndView;
    }
    @RequestMapping("/seller")
    public ModelAndView seller() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("seller");
        return modelAndView;
    }
    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbSeller seller){
        //密码加密
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(seller.getPassword());//加密
        seller.setPassword(password);

        try {
            tbSellerService.insert(seller);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
}
