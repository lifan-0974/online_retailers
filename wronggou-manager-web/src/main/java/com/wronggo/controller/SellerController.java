package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.Result;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbSeller;
import com.wrongo.service.TbSellerService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Reference
    TbSellerService tbSellerService;
    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId, String status){
        try {
            tbSellerService.updateStatus(sellerId, status);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return tbSellerService.findPage(page, rows);
    }

    @RequestMapping("/findOne")
    public TbSeller findOne(String id){
        return tbSellerService.selectbyname(id);
    }
}
