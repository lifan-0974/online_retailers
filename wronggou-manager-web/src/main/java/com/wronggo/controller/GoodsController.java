package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.Result;
import com.wronggo.model.goods;
import com.wrongo.service.TbGoodsService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Goods")
public class GoodsController {
    @Reference
    TbGoodsService tbGoodsService;
    @RequestMapping("/add")
    public Result add(@RequestBody goods goods){
        //获取登录名
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.getGoods().setSellerId(sellerId);//设置商家ID
        try {
            tbGoodsService.add(goods);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage( int page, int rows){

        return tbGoodsService.findPage(page, rows);
    }
    @RequestMapping("/findOne")
    public goods findOne(Long id){
        return tbGoodsService.findOne(id);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody goods goods){
        //校验是否是当前商家的id
        goods goods2 = tbGoodsService.findOne(goods.getGoods().getId());
        //获取当前登录的商家ID
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        //如果传递过来的商家ID并不是当前登录的用户的ID,则属于非法操作
        if(!goods2.getGoods().getSellerId().equals(sellerId) ||  !goods.getGoods().getSellerId().equals(sellerId) ){
            return new Result(false, "操作非法");
        }
        try {
            tbGoodsService.update(goods);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids, String status){
        try {
            tbGoodsService.updateStatus(ids, status);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }


    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            tbGoodsService.delete(ids);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }
}
