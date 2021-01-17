package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbItem;
import com.wrongo.service.TbItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("TbItem")
public class TbItemController {
    @Reference
    TbItemService tbItemService;
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return tbItemService.findPage(page, rows);
    }

    @RequestMapping("/findOne")
    public TbItem findOne(Long id){
        return tbItemService.selectByPrimaryKey(id);
    }

}
